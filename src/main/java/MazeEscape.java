import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Created by Mikhail Goncharenko on 15.08.19.
 */
public class MazeEscape {

  static Character[] directions = new Character[]{'<','^','>','v'};

  public static List<Character> escape(char[][] maze) {
    return new Me(maze).escape();
  }

  static class Me {
    private Position pos;
    private char[][] maze;

    Me(char[][] maze) {
      this.maze = maze;
      for (int i = 0; i < maze.length; i++) {
        for (int j = 0; j < maze[i].length; j++) {
          if (Arrays.asList(directions).contains(maze[i][j])) {
            pos = new Position(i, j, maze[i][j]);
          }
        }
      }
    }

    List<Character> escape() {
        List<Character> route = new LinkedList<>();
        tryMove(route);
        return route;
    }

    private void tryMove(List<Character> path) {
      if (isExit()) {
        return;
      }
      Position current = pos.copy();
      if (!stepIn((char) 0, path)) {
        pos = current.copy();
        char step = turnLeft();

        if (!stepIn(step, path)) {
          pos = current.copy();
          step = turnRight();

          if (!stepIn(step, path)) {
            pos = current.copy();
            step = turnBack();

            if (!stepIn(step, path)) {
              pos = current.copy();
              path.clear();
            }
          }
        }
      }
    }

    private boolean stepIn(char step, List<Character> path) {
      List<Character> forward = forward();
      if (!forward.isEmpty()) {
        if (step != 0) {
          path.add(step);
        }
        path.addAll(forward);
        return true;
      }
      return false;
    }

    private List<Character> forward() {
      List<Character> step = new LinkedList<>();
      switch (pos.dir) {
        case '^':
          step = step(-1, 0);
          break;
        case '<':
          step = step(0, -1);
          break;
        case '>':
          step = step(0, 1);
          break;
        case 'v':
          step = step(1, 0);
          break;
      }
      if (!step.isEmpty() && !isExit()) {
        tryMove(step);
      }
      return step;
    }

    private boolean isExit() {
      return pos.x == 0 || pos.y == 0 || pos.x == maze.length - 1 || pos.y == maze[0].length - 1;
    }

    private boolean isCrossRoad() {
      int count = 0;
      if (maze[pos.x][pos.y + 1] == ' ') count++;
      if (maze[pos.x][pos.y - 1] == ' ') count++;
      if (maze[pos.x + 1][pos.y] == ' ') count++;
      if (maze[pos.x - 1][pos.y] == ' ') count++;
      return count > 1;
    }

    private List<Character> step(int x, int y) {
      List<Character> path = new LinkedList<>();
      while (maze[pos.x + x][pos.y + y] == ' ') {
        maze[pos.x][pos.y] = '@';
        pos.y += y;
        pos.x += x;
        path.add('F');
        if (isExit() || isCrossRoad()) {
          break;
        }
      }
      Utils.print(maze);
      return path;
    }

    char turnRight() {
      int cur;
      for (cur = 0; cur < 4; cur++) {
        if (pos.dir == directions[cur]) break;
      }
      if (cur == 3) {
        pos.dir = directions[0];
      } else {
        pos.dir = directions[cur + 1];
      }
      return 'R';
    }

    char turnLeft() {
      int cur;
      for (cur = 0; cur < 4; cur++) {
        if (pos.dir == directions[cur]) break;
      }
      if (cur == 0) {
        pos.dir = directions[3];
      } else {
        pos.dir = directions[cur - 1];
      }
      return 'L';
    }

    char turnBack() {
      turnLeft();
      turnLeft();
      return 'B';
    }

    class Position {
      private char dir;
      int x, y;

      public Position(int x, int y, char dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
      }

      public Position copy() {
        return new Position(x, y, dir);
      }
    }

  }

}
