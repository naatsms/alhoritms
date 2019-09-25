package datastructures.trees;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Test;

public class RopeTest {

  Rope rope;

  @Test
  public void testTwoRebases() {
    rope = new Rope("abcdef");
    rope.moveSubstring(0, 1, 1);
    rope.moveSubstring(4,5,0);
    rope.print();
    assertEquals("efcabd", rope.toString());
  }

  @Test
  public void testExample() {
    rope = new Rope("hlelowrold");
    rope.moveSubstring(1, 1, 2);
    rope.moveSubstring(6,6,7);
    rope.print();
    assertEquals("helloworld", rope.toString());
  }

  @Test
  public void testOneSymbol() {
    rope = new Rope("h");
    rope.moveSubstring(0, 0, 1);
    rope.moveSubstring(0, 0, 0);
    rope.print();
    assertEquals("h", rope.toString());
  }

  @Test
  public void testLongString() {
    rope = new Rope("asldfkj;asldkfj;saldkfja;sldkfjasdlfkjsdlfkjsdlfkjasiejfaesliffvlnfkvmsdlkcmlsaidmc");
    rope.moveSubstring(0, 0, 1);
    rope.moveSubstring(0, 0, 0);
    rope.print();
    assertEquals("saldfkj;asldkfj;saldkfja;sldkfjasdlfkjsdlfkjsdlfkjasiejfaesliffvlnfkvmsdlkcmlsaidmc", rope
        .toString());
  }

  @Test
  public void testTwoSymbols() {
    rope = new Rope("hl");
    rope.moveSubstring(1, 1, 1);
    rope.moveSubstring(0, 0, 0);
    rope.moveSubstring(0, 0, 1);
    rope.moveSubstring(1, 1, 0);
    rope.print();
    assertEquals("hl", rope.toString());
  }

  @Test
  public void testThreeSymbols() {
    rope = new Rope("hld");
    rope.moveSubstring(1, 1, 0);
    rope.moveSubstring(0, 0, 0);
    rope.moveSubstring(0, 0, 1);
    rope.moveSubstring(0, 0, 2);
    rope.moveSubstring(2, 2, 0);
    rope.moveSubstring(1, 1, 0);
    rope.moveSubstring(1, 1, 0);
    rope.print();
    assertEquals("hld", rope.toString());
  }

  @Test
  public void testThreeSplits() {
    rope = new Rope("aaaabbbbcccc");
    rope.print();
    rope.moveSubstring(1, 6, 0);
    rope.print();
    rope.moveSubstring(0, 2, 5);
    rope.print();
    rope.moveSubstring(0, 7, 4);
    rope.print();
    rope.moveSubstring(7, 7, 11);
    rope.print();
    rope.moveSubstring(0, 11, 0);
    rope.print();
    rope.moveSubstring(0, 1, 6);
    rope.print();
    rope.moveSubstring(4, 5, 6);
    rope.print();
    rope.moveSubstring(4, 5, 6);
    rope.print();
    rope.moveSubstring(6, 6, 0);
    rope.print();
    rope.moveSubstring(7, 7, 0);
    rope.moveSubstring(4, 7, 0);

    rope.moveSubstring(7, 7, 11);
    rope.moveSubstring(7, 7, 11);
    rope.print();
    assertEquals("bbbbcccaaaca", rope.toString());
  }

  @Test
  public void testManySplits() {
    rope = new Rope("abcdefghijk");
    rope.moveSubstring(1, 6, 0);
    rope.moveSubstring(2, 7, 0);
    rope.moveSubstring(0, 7, 3);
    rope.moveSubstring(0, 0, 0);
    rope.moveSubstring(0, 10, 0);
    rope.moveSubstring(0, 9, 0);
    rope.moveSubstring(0, 9, 1);
    assertEquals("cijkdefgahb", rope.toString());
    rope.moveSubstring(0, 1, 0);
    assertEquals("cijkdefgahb", rope.toString());

    rope.moveSubstring(0, 1, 1);
    assertEquals("jcikdefgahb", rope.toString());
    rope.moveSubstring(0, 1, 2);
    assertEquals("ikjcdefgahb", rope.toString());
    rope.moveSubstring(0, 1, 3);
    assertEquals("jcdikefgahb", rope.toString());

    rope.moveSubstring(6, 8, 0);
    assertEquals("fgajcdikehb", rope.toString());

    rope.moveSubstring(1, 9, 2);
    assertEquals("fbgajcdikeh", rope.toString());

    rope.moveSubstring(10, 10, 10);
    rope.moveSubstring(10, 10, 0);
    rope.moveSubstring(10, 10, 0);
    rope.moveSubstring(10, 10, 0);
    rope.moveSubstring(10, 10, 0);
    rope.moveSubstring(10, 10, 0);
    rope.moveSubstring(0, 9, 0);
    rope.moveSubstring(0, 9, 1);
    rope.moveSubstring(0, 9, 0);
    rope.moveSubstring(0, 9, 1);
    rope.moveSubstring(0, 9, 0);
    rope.moveSubstring(1, 10, 0);
    rope.moveSubstring(1, 10, 1);
    rope.moveSubstring(0, 10, 0);
    rope.moveSubstring(0, 10, 0);


    rope.moveSubstring(10, 10, 1);
    rope.moveSubstring(10, 10, 1);
    rope.moveSubstring(10, 10, 1);
    assertEquals("cgajdikehfb", rope.toString());
    rope.print();
  }

  @Test
  public void testNPE() throws IOException {
    rope = new Rope("dpkvtgtras");
    Scanner in = new Scanner(new FileReader("/home/naatsms/dev/alhoritms/src/test/java/datastructures/trees/input"));
    int i = 0;
    while(in.hasNext()) {
      String[] numbers = in.nextLine().split(" ");
      rope.moveSubstring(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]),
            Integer.parseInt(numbers[2]));
      i++;
    }
    System.out.println(i);
    rope.print();
  }



}