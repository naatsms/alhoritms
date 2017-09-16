import DataStructures.SimpleStructures.QuoteChecker;
import org.junit.Assert;
import org.junit.Test;

public class QuoteChekerTest extends QuoteChecker {

    @Test
    public void checkLine() {
        Assert.assertEquals(0, check("([](){([])})"));
        Assert.assertEquals(5, check("()[]}"));
        Assert.assertEquals(7, check("{{[()]]"));
        Assert.assertEquals(3, check("{{{[][][]"));
        Assert.assertEquals(2, check("{{"));
        Assert.assertEquals(0, check("{}"));
        Assert.assertEquals(0, check(""));
        Assert.assertEquals(1, check("}"));
        Assert.assertEquals(0, check("s{}"));
        Assert.assertEquals(3, check("{{{ss[][][]"));
    }

}
