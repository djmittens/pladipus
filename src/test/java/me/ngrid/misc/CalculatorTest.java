package me.ngrid.misc;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class CalculatorTest {

    @Test
    public void testAdditionExpression() throws Exception {
        assertEquals(10, Calculator.evaluateExpression("1 + 2 + 3 + 4"), .001d);
    }

    @Test
    public void testUnaryExpression() throws Exception {
        assertEquals(10, Calculator.evaluateExpression("1 + 2 + 3 * 4"), .001d);
        assertEquals(18, Calculator.evaluateExpression("2 + (1 + 3) * 4"), .001d);
    }

    @Test
    public void testComplicatedExpression() throws Exception {
        assertEquals(-51.6666666667d, Calculator.evaluateExpression("(1 + 4) * 3 * 6 + (4 - 5 / 3)"),  .001d);
    }
}