import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Created by Mikhail Goncharenko on 13.08.19.
 */
public class MysteryFunction {

  public static long mystery(long n) {
    long arrSize = Long.highestOneBit(n);
    return mysteryInternal(n, arrSize);
  }

  public static long mysteryInternal(long n, long highest) {
    if (n <= 1) {
      return n;
    }
    if (n > highest - 1) {
      return mysteryInternal((highest - 1 - (n - highest)), highest / 2) + highest;
    }
    else {
      return mysteryInternal(n, highest / 2);
    }
  }

  public static long mysteryInv(long n) {
    long highestBit = Long.highestOneBit(n);
    return mysteryInvInternal(n, highestBit) - 1;
  }

  public static long mysteryInvInternal(long n, long highestBit) {
    if (n <= 1) {
      return n + 1;
    }
    if (n >= highestBit) {
      return 1 + highestBit * 2 - mysteryInvInternal(n - highestBit, highestBit / 2);
    }
    else {
      return mysteryInvInternal(n, highestBit / 2);
    }
  }

  public static String nameOfMystery() {
    return "";
  }

  @Test
  public void test() {
    assertEquals( "mystery(6) ", 5, MysteryFunction.mystery( 6 ) );
    assertEquals( "mystery(4829853398396043653) ", 7098971648724959559L, MysteryFunction.mystery( 4829853398396043653L ) );
    assertEquals( "mystery(9) ", 13, MysteryFunction.mystery( 9 ) );
    assertEquals( "mystery(19) ", 26, MysteryFunction.mystery( 19 ) );
  }

  @Test
  public void mysteryInv() {

    assertEquals( "mysteryInv(5)", 6, MysteryFunction.mysteryInv( 5 ) );
    assertEquals( "mysteryInv(7098971648724959559)", 4829853398396043653L, MysteryFunction.mysteryInv( 7098971648724959559L ) );
    assertEquals( "mysteryInv(13)", 9, MysteryFunction.mysteryInv( 13 ) );
    assertEquals( "mysteryInv(26)", 19, MysteryFunction.mysteryInv( 26 ) );
  }

}
