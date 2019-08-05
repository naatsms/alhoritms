import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Created by Mikhail Goncharenko on 05.08.19.
 */
public class Mixbonacci {

  public static BigInteger[] mixbonacci(String[] pattern, int length) {
    init();
    BigInteger[] result = new BigInteger[length];

    for (int i = 0; i < length;) {
        for (String pat: pattern) {
          result[i++] = sequences.get(pat).getNext();
          if (i >= length) {
            return result;
          }
        }
    }
    return result;
  }

  static HashMap<String, Sequence> sequences = new HashMap<>();

  static void init() {
    sequences.clear();
    sequences.put("fib", new Fibonacci());
    sequences.put("pad", new Padovan());
    sequences.put("jac", new Jacobsthal());
    sequences.put("pel", new Pell());
    sequences.put("tri", new Tribonacci());
    sequences.put("tet", new Tetranacci());
  }

  interface Sequence {
    BigInteger getNext();
  }

  static class Fibonacci implements Sequence {
      int n = 0;
      BigInteger a = BigInteger.ZERO;
      BigInteger b = BigInteger.ONE;

    @Override
    public BigInteger getNext() {
      BigInteger result;
      if (n < 2) {
        result = n == 0 ? a : b;
      }
      else {
        result = a.add(b);
        a = b;
        b = result;
      }
      n++;
      return result;
    }
  }

  static class Padovan implements Sequence {
    int n = 0;
    BigInteger a = BigInteger.ONE;
    BigInteger b = BigInteger.ZERO;
    BigInteger c = BigInteger.ZERO;

    @Override
    public BigInteger getNext() {
      BigInteger result;
      if (n < 3) result = n == 0 ? a : b;
      else {
        result = a.add(b);
        a = b;
        b = c;
        c = result;
      }
      n++;
      return result;
    }
  }

  static class Jacobsthal implements Sequence {
      BigInteger a = BigInteger.ZERO;
      BigInteger b = BigInteger.ONE;
      int n = 0;

    @Override
    public BigInteger getNext() {
      BigInteger result = null;

      if (n < 2) result = n == 0 ? a : b;
      else {
        result = b.add(a.multiply(BigInteger.valueOf(2)));
        a = b;
        b = result;
      }
      n++;
      return result;
    }
  }

  static class Pell implements Sequence {
      BigInteger a = BigInteger.ZERO;
      BigInteger b = BigInteger.ONE;
      int n;

    @Override
    public BigInteger getNext() {
      BigInteger result = null;

      if (n < 2) result = n == 0 ? a : b;
      else {
        result = a.add(b.multiply(BigInteger.valueOf(2)));
        a = b;
        b = result;
      }
      n++;
      return result;
    }
  }

  static class Tribonacci implements Sequence {
      BigInteger a = BigInteger.ZERO;
      BigInteger b = BigInteger.ZERO;
      BigInteger c = BigInteger.ONE;
      int n = 0;

    @Override
    public BigInteger getNext() {
      BigInteger result;
      if (n < 3) result = n == 2 ? c : b;
      else {
        result = a.add(b).add(c);
        a = b;
        b = c;
        c = result;
      }
      n++;
      return result;
    }
  }

  static class Tetranacci implements Sequence {
      BigInteger a = BigInteger.ZERO;
      BigInteger b = BigInteger.ZERO;
      BigInteger c = BigInteger.ZERO;
      BigInteger d = BigInteger.ONE;
      int n = 0;

    @Override
    public BigInteger getNext() {
      BigInteger result = null;
      if (n < 4) result = n == 3 ? d : b;
      else {
        result = a.add(b).add(c).add(d);
        a = b;
        b = c;
        c = d;
        d = result;
      }
      n++;
      return result;
    }
  }

}
