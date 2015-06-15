package me.ngrid.misc;

/**
 * A very simple calculator class that evaluates a mathematical expression such as
 * {@code (-1 + 4) * 3 * -6 + (4 - 5 / 3)}
 * Should be no problem right?
 *
 * This problem is a common interview question that should be answered in 1 hour.
 */
public class Calculator {
    private String expr;

    private Calculator (String expr) {
        this.expr = expr;
    }
    public double evaluate() {
        return Double.NaN;
    }

    public static double evaluateExpression(String expr) {
        return new Calculator(expr).evaluate();
    }


}
