package me.ngrid.misc.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class CalculatorTest {

    @Test
    public void test1plus1 () {
        assertEquals(2, new Calculator().calculate("(1+1)"));
    }


    @Test
    public void testComplex () {
        assertEquals(6, new Calculator().calculate("(1+1) + 3 * 2 - (4/2)"));
    }
}