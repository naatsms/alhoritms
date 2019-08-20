import java.util.Arrays;

/**
 * @author Created by Mikhail Goncharenko on 20.08.19.
 */
public class Utils {

  static void print(char[][] maze) {
    for (char[] chars : maze) {
      System.out.println(Arrays.toString(chars));
    }
    System.out.println("\n");
  }

}

