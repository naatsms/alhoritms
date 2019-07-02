package DataStructures.Trees;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {

    private Main testTree;

    @Before
    public void init() {
        testTree = new Main();
    }

    @After
    public void after() {
        testTree = null;
    }

    @Test
    public void addRoot() {
        testTree.add(1);
        assertEquals(1, testTree.root.value);
    }

    @Test
    public void addThree() {
        testTree.add(5);
        testTree.add(7);
        testTree.add(3);
        assertEquals(5, testTree.root.value);
        assertEquals(7, testTree.root.right.value);
        assertEquals(3, testTree.root.left.value);
    }

    @Test
    public void testRebalance() {
        testTree.add(1);
        testTree.add(2);
        testTree.add(3);
        testTree.add(4);
        assertEquals(3, testTree.root.right.value);
    }

    @Test
    public void testDeleteRightLeaf() {
        testTree.add(1);
        testTree.add(2);
        testTree.add(3);
        testTree.add(4);
        testTree.delete(4);;
        assertNull(testTree.root.right.right);
        assertEquals(3, testTree.root.right.value);
    }

    @Test
    public void testDeleteLeaf() {
        testTree.add(1);
        testTree.add(2);
        testTree.add(3);
        testTree.add(4);
        testTree.delete(3);
        assertEquals(4, testTree.root.right.value);
    }

    @Test
    public void testDeleteWithRightLeaf() {
        testTree.add(8);
        testTree.add(3);
        testTree.add(9);
        testTree.add(1);
        testTree.add(7);
        testTree.add(5);
        testTree.add(6);
        testTree.delete(3);
        assertEquals(5, testTree.root.left.value);
        assertEquals(6, testTree.root.left.right.value);
        assertEquals(1, testTree.root.left.left.value);
    }

    @Test
    public void test1() {
        ByteArrayOutputStream bf = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bf);
        System.setOut(ps);

        testTree.check(1);
        testTree.add(1);
        testTree.check(1);
        testTree.add(2);
        testTree.calc(1,2);
        testTree.add(1000000000);
        testTree.check(1000000000);
        testTree.delete(1000000000);
        testTree.check(1000000000);
        testTree.calc(999999999, 1000000000);
        testTree.delete(2);
        testTree.check(2);
        testTree.delete(0);
        testTree.add(9);
        testTree.calc(0, 9);

        String result = "Not found\n"
            + "Found\n"
            + "3\n"
            + "Found\n"
            + "Not found\n"
            + "1\n"
            + "Not found\n"
            + "10\n";
        assertEquals(result, bf.toString());
    }



}