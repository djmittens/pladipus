package me.ngrid.misc.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class ReverseListTest {
    @Test
    public void testReverse() {
        ListNode list = new ListNode(1,
                new ListNode(2, new ListNode(3, null)));
        list = ReverseList.reverseList(list);
        assertTrue(ReverseList.toString(list), list.val == 3);
    }
}