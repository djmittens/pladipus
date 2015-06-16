package me.ngrid.misc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class ReverseListTest {
    @Test
    public void testReverse() {
        ReverseList.ListNode list = new ReverseList.ListNode(1,
                new ReverseList.ListNode(2, new ReverseList.ListNode(3, null)));
        list = ReverseList.reverseList(list);
        assertTrue(ReverseList.toString(list), list.val == 3);
    }
}