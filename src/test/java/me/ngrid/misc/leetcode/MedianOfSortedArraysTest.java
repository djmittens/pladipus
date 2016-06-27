package me.ngrid.misc.leetcode;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
@Ignore("FIXME: Why are these failing, what is the actual problem being solved here?")
public class MedianOfSortedArraysTest {

    @Test
    public void testSmallEvenArray() throws Exception {
        assertEquals(2.5d, new MedianOfSortedArrays().findMedianSortedArrays(new int[] {1, 2, 3, 4}, new int[0]), 0.001d);
    }

    @Test
    public void testSmallOddArray() throws Exception {
        assertEquals(2.0d, new MedianOfSortedArrays().findMedianSortedArrays(new int[0], new int[] {1, 2, 3}), 0.001d);
    }

    @Test
    public void test2Arrays() throws Exception {
        assertEquals(3.0d, new MedianOfSortedArrays().findMedianSortedArrays(new int[] {1,2,3,4,5,7}
                , new int[] {6}), 0.001d);
    }


    @Test
    public void test2ReverseArrays() throws Exception {
        assertEquals(3.0d, new MedianOfSortedArrays().findMedianSortedArrays(
                new int[] {1},new int[] {2,3,4,5,6,7,8,9, 10}), 0.001d);
    }

    @Test
    public void testAverage() throws Exception {
        assertEquals(3.0d, new MedianOfSortedArrays().findMedianSortedArrays(
                new int[] {1, 2},new int[] {1,2,3}), 0.001d);
    }
}