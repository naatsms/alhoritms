package puzzles;

import static org.junit.Assert.assertEquals;

import puzzles.Dinglemouse.Direction;
import org.junit.Test;

public class DinglemouseTest {

  @Test
  public void testTurnBack() {
    Direction dir = Direction.N;
    dir = Dinglemouse.turnBack(dir);
    assertEquals(Direction.S, dir);

    dir = Direction.W;
    dir = Dinglemouse.turnBack(dir);
    assertEquals(Direction.E, dir);

    dir = Direction.SW;
    dir = Dinglemouse.turnBack(dir);
    assertEquals(Direction.NE, dir);
  }

  @Test
  public void testExample() {
    int result = Dinglemouse.trainCrash(
        "                                /------------\\             \n"
            + "/-------------\\                /             |             \n"
            + "|             |               /              S             \n"
            + "|             |              /               |             \n"
            + "|        /----+--------------+------\\        |\n"
            + "\\       /     |              |      |        |             \n"
            + " \\      |     \\              |      |        |             \n"
            + " |      |      \\-------------+------+--------+---\\         \n"
            + " |      |                    |      |        |   |         \n"
            + " \\------+--------------------+------/        /   |         \n"
            + "        |                    |              /    |         \n"
            + "        \\------S-------------+-------------/     |         \n"
            + "                             |                   |         \n"
            + "/-------------\\              |                   |         \n"
            + "|             |              |             /-----+----\\    \n"
            + "|             |              |             |     |     \\   \n"
            + "\\-------------+--------------+-----S-------+-----/      \\  \n"
            + "              |              |             |             \\ \n"
            + "              |              |             |             | \n"
            + "              |              \\-------------+-------------/ \n"
            + "              |                            |               \n"
            + "              \\----------------------------/               ", "Aaaa", 147, "Bbbbbbbbbbb",
        288, 1000);
    assertEquals(516, result);
  }

  @Test
  public void testTwoExpresses() {
    int result = Dinglemouse.trainCrash(
        "/-----------------\\\n"
            + "|                 |\n"
            + "S                 |\n"
            + "|                 |\n"
            + "|                 |\n"
            +"\\-----------------/", "xxxxxxxxxxxxxX", 33, "xxX",
        5, 109);
    assertEquals(-1, result);
  }

}