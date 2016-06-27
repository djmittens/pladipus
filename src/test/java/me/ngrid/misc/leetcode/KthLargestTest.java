package me.ngrid.misc.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class KthLargestTest {

    @Test
    public void testSmallArray() throws Exception {
        assertEquals(2, new KthLargest().findKthLargest(
                new int[] {2, 1}, 1));
    }

    @Test
    public void testSizeof1() throws Exception {
        assertEquals(1, new KthLargest().findKthLargest(
                new int[] {1}, 1));
    }

    @Test
    public void testWithNegative() throws Exception {
        assertEquals(2, new KthLargest().findKthLargest(
                new int[] {-1, 2, 0}, 1));
    }

    @Test
    public void testSimpleCase() throws Exception {
        assertEquals(10, new KthLargest().findKthLargest(
                new int[] {-1, 2, 0, 10, 3, 5, 7, 25, 100, -6 }, 3));
    }
}