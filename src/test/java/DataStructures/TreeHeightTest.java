package DataStructures;

import DataStructures.SimpleStructures.TreeHeight;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Мишаня on 25.04.2017.
 */
public class TreeHeightTest extends TreeHeight {

    @Test
    public void findHeight() throws Exception {
        assertEquals(4, findHeight(9, 7, 5, 5, 2, 9, 9, 9, 2, -1));
    }

}