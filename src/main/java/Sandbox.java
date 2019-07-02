import javax.management.loading.MLetContent;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @author mikhail.goncharenko@masterdata.ru on 26.06.18.
 */
public class Sandbox {

    DecimalFormat df = new DecimalFormat("#0.00");

    {
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        df.getDecimalFormatSymbols().setDecimalSeparator('.');
    }

    public static void main(String[] args) {
        new Sandbox().go();
    }

    public void go() {
        OrderModel order = new OrderModel();
        order.setTotalDiscounts(10.00);

        ArrayList<AbstractOrderEntryModel> list = new ArrayList<>();
        AbstractOrderEntryModel one = new AbstractOrderEntryModel();
        one.setProduct(new AbstractOrderEntryModel.Product("code"));
        one.setQuantity(1);
        one.setTotalPrice(90.00);
        list.add(one);

        AbstractOrderEntryModel two = new AbstractOrderEntryModel();
        two.setProduct(new AbstractOrderEntryModel.Product("code"));
        two.setQuantity(2);
        two.setTotalPrice(3.00);
        list.add(two);

        AbstractOrderEntryModel three = new AbstractOrderEntryModel();
        three.setProduct(new AbstractOrderEntryModel.Product("code"));
        three.setQuantity(2);
        three.setTotalPrice(7.51);
        list.add(three);

        List<OrderLineDto> orderLines = new ArrayList<>();
        addOrderLinesWithDiscounts(orderLines, list, order);

        orderLines.forEach(line -> System.out.println(line.getPrice().getAltPrice()));
    }

    private void addOrderLinesWithDiscounts(List<OrderLineDto> orderLines, List<AbstractOrderEntryModel> entries, OrderModel order){
        final BigDecimal discount = BigDecimal.valueOf(order.getTotalDiscounts() == null ? 0d : order.getTotalDiscounts()).setScale(2, RoundingMode.HALF_EVEN);
        final BigDecimal totalWeight = BigDecimal.valueOf(entries.stream().mapToDouble(AbstractOrderEntryModel::getTotalPrice).sum()).setScale(2, RoundingMode
                .HALF_EVEN);
        BigDecimal discountChecksum = BigDecimal.ZERO;

        Iterator<AbstractOrderEntryModel> it = entries.iterator();
        while (it.hasNext()) {
            AbstractOrderEntryModel entry = it.next();

            OrderLineDto line = new OrderLineDto();
            line.setQuantity(new OrderLineDto.QuantityDto(entry.getQuantity().toString()));
            line.setDesignLink("");
            line.setProduct(new OrderLineDto.ProductDto(entry.getProduct().getCode()));

            BigDecimal discountAmount = BigDecimal.valueOf(entry.getTotalPrice()).multiply(discount).divide(totalWeight, 2, RoundingMode.HALF_EVEN);
            BigDecimal resultPrice = BigDecimal.valueOf(entry.getTotalPrice()).subtract(discountAmount);
            discountChecksum = discountChecksum.add(discountAmount);

            if (!it.hasNext()) {
                BigDecimal inaccuracy = discount.subtract(discountChecksum);
                resultPrice = resultPrice.subtract(inaccuracy);
            }

            line.setPrice(createPrice(resultPrice.doubleValue()));
            orderLines.add(line);
        }
    }

    private OrderLineDto.PriceDto createPrice(Double basePrice) {
        OrderLineDto.PriceDto price = new OrderLineDto.PriceDto();
        price.setUnitPrice("0");
        price.setUomDescription("Each");
        price.setUseAltPrice("YES");
        price.setAltPrice(df.format(basePrice));
        return price;
    }


    public static class OrderLineDto {

        @XmlElement(name="Product")
        ProductDto product;
        @XmlElement(name="Quantity")
        QuantityDto quantity;
        @XmlElement(name="Price")
        PriceDto price;
        @XmlElement(name="DesignLink")
        String designLink;

        public ProductDto getProduct() {
            return product;
        }

        public void setProduct(ProductDto product) {
            this.product = product;
        }

        public QuantityDto getQuantity() {
            return quantity;
        }

        public void setQuantity(QuantityDto quantity) {
            this.quantity = quantity;
        }

        public PriceDto getPrice() {
            return price;
        }

        public void setPrice(PriceDto price) {
            this.price = price;
        }

        public String getDesignLink() {
            return designLink;
        }

        public void setDesignLink(String designLink) {
            this.designLink = designLink;
        }

        public static class ProductDto {

            public ProductDto() {}

            public ProductDto(String suppliersProductCode) {
                this.suppliersProductCode = suppliersProductCode;
            }
            String suppliersProductCode;
            String useAltDescription;
            String description;

            public String getSuppliersProductCode() {
                return suppliersProductCode;
            }

            public void setSuppliersProductCode(String suppliersProductCode) {
                this.suppliersProductCode = suppliersProductCode;
            }

            public String getUseAltDescription() {
                return useAltDescription;
            }

            public void setUseAltDescription(String useAltDescription) {
                this.useAltDescription = useAltDescription;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        @XmlRootElement(name="Quantity")
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class QuantityDto {

            public QuantityDto() {}

            public QuantityDto(String amount) {
                this.amount = amount;
            }

            @XmlElement(name="Amount")
            String amount;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }
        }

        @XmlType(propOrder = {
                "unitPrice",
                "useAltPrice",
                "altPrice",
                "uomDescription"})
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class PriceDto {

            @XmlAttribute(name ="UOMDescription")
            String uomDescription;
            @XmlElement(name="UnitPrice")
            String unitPrice;
            @XmlElement(name="UseAltPrice")
            String useAltPrice;
            @XmlElement(name="AltPrice")
            String altPrice;

            public String getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(String unitPrice) {
                this.unitPrice = unitPrice;
            }

            public String getUseAltPrice() {
                return useAltPrice;
            }

            public void setUseAltPrice(String useAltPrice) {
                this.useAltPrice = useAltPrice;
            }

            public String getAltPrice() {
                return altPrice;
            }

            public void setAltPrice(String altPrice) {
                this.altPrice = altPrice;
            }

            public String getUomDescription() {
                return uomDescription;
            }

            public void setUomDescription(String uomDescription) {
                this.uomDescription = uomDescription;
            }
        }

    }

    private class OrderModel {
        private Double totalDiscounts;

        public Double getTotalDiscounts() {
            return totalDiscounts;
        }

        public void setTotalDiscounts(Double totalDiscounts) {
            this.totalDiscounts = totalDiscounts;
        }
    }

    private static class AbstractOrderEntryModel {
        private Integer quantity;
        private Double totalPrice;
        private Product product;

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        private static class Product {

            private String code;

            public Product(String code) {
                this.code = code;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
