package me.ngrid.misc.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class MaximalSquareTest {

    @Test
    public void singleSimpleSquare() throws Exception {
        assertEquals(1, new MaximalSquare().maximalSquare(new char[][] {{'1'}}));
    }

    @Test
    public void singleSingleSquare() throws Exception {
        assertEquals(1, new MaximalSquare().maximalSquare(new char[][] {{'0'}, {'1'}}));
    }
}