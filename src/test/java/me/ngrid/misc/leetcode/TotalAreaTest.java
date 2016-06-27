package me.ngrid.misc.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class TotalAreaTest {

    @Test
    public void offBy1() {
       assertEquals(new TotalArea().computeArea(-2, -2, 2, 2, 3, 3, 4, 4), 17);
    }
}
