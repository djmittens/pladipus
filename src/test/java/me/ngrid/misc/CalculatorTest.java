package me.ngrid.misc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class CalculatorTest {

    @Test
    public void testAdditionExpression() throws Exception {
        assertEquals(10d, Calculator.evaluateExpression("1 + 2 + 3 + 4"), .001d);
    }

    @Test
    public void testComplicatedExpression() throws Exception {
        assertEquals(92.3333333333d, Calculator.evaluateExpression("(1 + 4) * 3 * 6 + (4 - 5 / 3)"),  .001d);
    }

    @Test
    public void testRightAssociativity() throws Exception {
        assertEquals(3.0001220703125d, Calculator.evaluateExpression(" 3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"), .0000001d);
    }

    @Test
    public void testComplex () {
        assertEquals(6d, Calculator.evaluateExpression("(1+1) + 3 * 2 - (4/2)"), .0001d);
    }
}