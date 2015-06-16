package me.ngrid.misc.leetcode;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class CountTreeNodesTest {

    @Test
    public void test2Nodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, new CountTreeNodes().countNodes(root));
    }
}