package datastructures.trees;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {

    private SummarableTree testTree;

    @Before
    public void init() {
        testTree = new SummarableTree();
    }

    @After
    public void after() {
        testTree = null;
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