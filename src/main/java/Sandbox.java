import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
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
    }

    public static double calcBox(int k, int n) {
        return 1.0 / (k * Math.pow(n + 1, 2 * k));
    }

    public double calcRow(int k, int n) {
        return IntStream.
            iterate(1, a -> a + 1).limit(n)
            .mapToDouble(num -> calcBox(k, num))
            .sum();
    }

    public double calcFull(int k, int n) {
        return IntStream.iterate(1, a -> a + 1).limit(k)
            .mapToDouble(num -> calcRow(num, n))
            .sum();
    }

    public static String sumOfDivided(int[] l) {
        int limit = getMaxAbs(l);
        return buildPrimes(limit)
            .filter(prime -> IntStream.of(l).filter(num -> num % prime == 0).count() > 0)
            .mapToObj(prime -> getElement(prime, l))
            .collect(Collectors.joining());
    }

    private static int getMaxAbs(int[] l) {
        IntSummaryStatistics stats = IntStream.of(l).summaryStatistics();
        return Math.max(Math.abs(stats.getMin()), stats.getMax());
    }

    private static String getElement(int prime, int[] l) {
        IntSummaryStatistics statistics = IntStream.of(l).filter(num -> num % prime == 0).summaryStatistics();
        if (statistics.getCount() > 0) {
            return "(" + prime + " " + statistics.getSum() + ")";
        }
        return "";
    }

    private static IntStream buildPrimes(int limit) {
        int[] subResult = IntStream.range(0, limit + 1).toArray();
        for (int i = 2; i < subResult.length; i++) {
            if (subResult[i] != 0) {
                int num = subResult[i] * 2;
                while (num <= limit) {
                    subResult[num] = 0;
                    num += subResult[i];
                }
            }
        }
        return Arrays.stream(subResult).filter(num -> num > 1);
    }
}
