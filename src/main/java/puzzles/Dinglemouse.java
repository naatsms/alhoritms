package puzzles;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author Created by Mikhail Goncharenko on 22.09.19.
 */
public class Dinglemouse {

  public static int trainCrash(final String track, final String aTrain, final int aTrainPos,
      final String bTrain, final int bTrainPos, final int limit) {
    Dinglemouse dinglemouse = new Dinglemouse(track, aTrain, aTrainPos, bTrain, bTrainPos);
    for (int i = 0; i <= limit; i++) {
      if (dinglemouse.crash()) {
        return i;
      }
      dinglemouse.move();
    }
    return -1;
  }

  private void move() {
    trainA.move();
    trainB.move();
    printTrackWithTrains();
  }

  private boolean crash() {
    return trainA.points.stream().anyMatch(p -> trainB.points.contains(p))
        || trainA.points.stream().distinct().count() < trainA.points.size()
        || trainB.points.stream().distinct().count() < trainB.points.size();
  }

  private char[][] track;
  private Train trainA;
  private Train trainB;
  Point start;

  public Dinglemouse(String strTrack, String aTrain, int aTrainPos, String bTrain, int bTrainPos) {
    track = Arrays.stream(strTrack.split("\n")).map(s -> " " + s + " ").map(String::toCharArray)
        .toArray(char[][]::new);
    findStart();
    trainA = placeTrain(aTrain, aTrainPos);
    trainB = placeTrain(bTrain, bTrainPos);
  }

  private Train placeTrain(String train, int i) {
    Point cursor = Point.copyOf(start);
    Direction direction = Direction.NE;
    for (int j = 0; j < i; j++) {
      direction = step(cursor, direction);
    }
    return createTrain(train, cursor, direction);
  }

  private Train createTrain(String train, Point c, Direction dir) {
    boolean clockWise = train.matches("^.+[A-Z]$");
    Train t;
    if (clockWise) {
      t = new Train(train, Point.copyOf(c), dir);
      dir = turnBack(dir);
    } else {
      t = new Train(train, Point.copyOf(c), turnBack(dir));
    }
    for (int j = 1; j < train.length(); j++) {
      dir = step(c, dir);
      t.addPoint(Point.copyOf(c));
    }
    return t;
  }

  private Direction step(Point c, Direction dir) {
    switch (dir) {
      case E:
      case W:
        if (track[c.y][c.x] == '/') {
          dir = turnLeft(dir);
        } else if (track[c.y][c.x] == '\\') {
          dir = turnRight(dir);
        }
        dir = forward(c, dir);
        break;
      case N:
      case S:
        if (track[c.y][c.x] == '/') {
          dir = turnRight(dir);
        } else if (track[c.y][c.x] == '\\') {
          dir = turnLeft(dir);
        }
        dir = forward(c, dir);
        break;
      case NW:
      case SE:
      case NE:
      case SW:
        dir = forward(c, dir);
        break;
      default:
        break;
    }
    return dir;
  }

  private Direction forward(Point c, Direction dir) {
    switch (dir) {
      case E:
        c.x++;
        break;
      case W:
        c.x--;
        break;
      case N:
        c.y--;
        break;
      case S:
        c.y++;
        break;
      case NE:
        if (track[c.y][c.x + 1] == '-') {
          dir = turnRight(dir);
          c.x++;
        } else if (track[c.y - 1][c.x] == '|' || track[c.y - 1][c.x] == '+') {
          dir = turnLeft(dir);
          c.y--;
        } else {
          c.x++;
          c.y--;
        }
        break;
      case NW:
        if (track[c.y][c.x - 1] == '-') {
          dir = turnLeft(dir);
          c.x--;
        } else if (track[c.y - 1][c.x] == '|' || track[c.y - 1][c.x] == '+') {
          dir = turnRight(dir);
          c.y--;
        } else {
          c.x--;
          c.y--;
        }
        break;
      case SE:
        if (track[c.y][c.x + 1] == '-') {
          dir = turnLeft(dir);
          c.x++;
        } else if (track[c.y + 1][c.x] == '|' || track[c.y + 1][c.x] == '+') {
          dir = turnRight(dir);
          c.y++;
        } else {
          c.x++;
          c.y++;
        }
        break;
      case SW:
        if (track[c.y][c.x - 1] == '-') {
          dir = turnRight(dir);
          c.x--;
        } else if (track[c.y + 1][c.x] == '|' || track[c.y + 1][c.x] == '+') {
          dir = turnLeft(dir);
          c.y++;
        } else {
          c.x--;
          c.y++;
        }
        break;
      default:
        break;
    }
    return dir;
  }

  private void printTrackWithTrains() {
    for (int i = 0; i < track.length; i++) {
      for (int j = 0; j < track[i].length; j++) {
        if (trainA.get(j, i) != null) {
          System.out.print(trainA.get(j, i).ch);
        } else if (trainB.get(j, i) != null) {
          System.out.print(trainB.get(j, i).ch);
        } else {
          System.out.print(track[i][j]);
        }
      }
      System.out.println();
    }
  }

  private void findStart() {
    for (int i = 0; i < track.length; i++) {
      for (int j = 0; j < track[i].length; j++) {
        if (track[i][j] != ' ') {
          start = new Point(j, i, '*');
          return;
        }
      }
    }
  }

  static Direction turnLeft(Direction dir) {
    int cur;
    for (cur = 0; cur < 8; cur++) {
      if (dir == Direction.values()[cur]) {
        break;
      }
    }
    if (cur == 0) {
      return Direction.values()[7];
    } else {
      return Direction.values()[cur - 1];
    }
  }

  static Direction turnRight(Direction dir) {
    int cur;
    for (cur = 0; cur < 8; cur++) {
      if (dir == Direction.values()[cur]) {
        break;
      }
    }
    if (cur == 7) {
      return Direction.values()[0];
    } else {
      return Direction.values()[cur + 1];
    }
  }

  static Direction turnBack(Direction dir) {
    int cur;
    for (cur = 0; cur < 8; cur++) {
      if (dir == Direction.values()[cur]) {
        break;
      }
    }
    return Direction.values()[(cur + 4) % 8];
  }

  enum Direction {
    E, SE, S, SW, W, NW, N, NE;
  }

  private class Train {

    Deque<Point> points = new LinkedList<>();
    Direction direction;
    int length;
    char ch;
    int standTimer = 0;

    public Train(String tr, Point head, Direction dir) {
      ch = Character.toLowerCase(tr.charAt(0));
      this.length = (ch == 'x') ? 0 : tr.length() - 1;
      head.ch = ch;
      direction = dir;
      points.add(head);
    }

    public void addPoint(Point p) {
      p.ch = ch;
      points.add(p);
    }

    public Point get(int x, int y) {
      return points.stream().filter(p -> p.x == x && p.y == y).findFirst().orElse(null);
    }

    public void move() {
      if (standTimer != 0) {
        standTimer--;
        return;
      }
      Point newHead = Point.copyOf(points.peekFirst());
      points.removeLast();
      points.addFirst(newHead);
      direction = step(newHead, direction);
      if (track[newHead.y][newHead.x] == 'S') {
        standTimer = length;
      }
    }
  }

  static class Point {

    int x, y;
    char ch;

    public Point(int x, int y, char ch) {
      this.x = x;
      this.y = y;
      this.ch = ch;
    }

    static Point copyOf(Point src) {
      return new Point(src.x, src.y, src.ch);
    }

    @Override
    public boolean equals(Object o) {
      Point point = (Point) o;
      return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }
}