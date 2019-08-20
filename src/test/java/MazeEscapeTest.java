import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class MazeEscapeTest {

  private List<char[][]> basicMazes = new ArrayList<>();

  @Test
  public void testEscape() {
    List<Character> var = MazeEscape.escape(new char[][] {
        "##########".toCharArray(),
        "#        #".toCharArray(),
        "#  ##### #".toCharArray(),
        "#  #   # #".toCharArray(),
        "#  #^# # #".toCharArray(),
        "#  ### # #".toCharArray(),
        "#      # #".toCharArray(),
        "######## #".toCharArray()
    });
    System.out.println(var);
  }

  @Test
  public void testEscape2() {
    List<Character> var = MazeEscape.escape(new char[][] {
        "##########".toCharArray(),
        "#        #".toCharArray(),
        "#  ##### #".toCharArray(),
        "#  #   # #".toCharArray(),
        "#  #^# # #".toCharArray(),
        "#  ### # #".toCharArray(),
        "#      # #".toCharArray(),
        "######## #".toCharArray(),
        "         #".toCharArray(),
        "######## #".toCharArray(),
        "######## #".toCharArray(),
        "##########".toCharArray()
    });
    System.out.println(var);
  }

  @Test
  public void testEscape3() {
    List<Character> var = MazeEscape.escape(new char[][] {
        "# #########".toCharArray(),
        "#        >#".toCharArray(),
        "###########".toCharArray()
    });
    System.out.println(var);
  }

  @Test
  public void testEscape4() {
    List<Character> var = MazeEscape.escape(new char[][] {
        "#########################################".toCharArray(),
        "#<    #       #     #         # #   #   #".toCharArray(),
        "##### # ##### # ### # # ##### # # # ### #".toCharArray(),
        "# #   #   #   #   #   # #     #   #   # #".toCharArray(),
        "# # # ### # ########### # ####### # # # #".toCharArray(),
        "#   #   # # #       #   # #   #   # #   #".toCharArray(),
        "####### # # # ##### # ### # # # #########".toCharArray(),
        "#   #     # #     # #   #   # # #       #".toCharArray(),
        "# # ####### ### ### ##### ### # ####### #".toCharArray(),
        "# #             #   #     #   #   #   # #".toCharArray(),
        "# ############### ### ##### ##### # # # #".toCharArray(),
        "#               #     #   #   #   # #   #".toCharArray(),
        "##### ####### # ######### # # # ### #####".toCharArray(),
        "#   # #   #   # #         # # # #       #".toCharArray(),
        "# # # # # # ### # # ####### # # ### ### #".toCharArray(),
        "# # #   # # #     #   #     # #     #   #".toCharArray(),
        "# # ##### # # ####### # ##### ####### # #".toCharArray(),
        "# #     # # # #   # # #     # #       # #".toCharArray(),
        "# ##### ### # ### # # ##### # # ### ### #".toCharArray(),
        "#     #     #     #   #     #   #   #    ".toCharArray(),
        "#########################################".toCharArray()
    });
    System.out.println(var);
  }

}