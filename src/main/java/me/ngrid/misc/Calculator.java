package me.ngrid.misc;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * A very simple calculator class that evaluates a mathematical expression such as
 * {@code (-1 + 4) * 3 * -6 + (4 - 5 / 3)}
 * Should be no problem right?
 *
 * This problem is a common interview question that should be answered in 1 hour.
 */
public class Calculator {
    private Deque<Object> rpn;

    private Calculator (String expr) {
        /// Sanitize the input for space to increase readability.
        expr = expr.replaceAll(" ", "");
        this.rpn = new ArrayDeque<>();
        Deque<Operator> ops = new ArrayDeque<>();
        char[] buf = expr.toCharArray();

        StringBuilder sb = new StringBuilder();

        for (char c : buf) {
            /// lets see if its a number
            Operator op = Operator.getOperator(c);
            // If the buffer contains numbers then lets try and parse a double out.
            // but only if we are done parsing;

            if ((c == ')' || op != null) && sb.length() > 0) {
                this.rpn.add(Double.valueOf(sb.toString()));
                sb = new StringBuilder();
            }


            if (op == null) {
                /// If this is a closing paren then find an opening one while popping.
                if (c == ')') {
                    while (!ops.isEmpty() && !((op = ops.pop()) instanceof Paren)) {
                        rpn.add(op);
                    }
                }

                /// Otherwise this is totally a number
                else {
                    sb.append(c);
                }

            }
            // Iff we see a straight up paren we just push it as is.
            else if(op instanceof Paren) {
                ops.push(op);
            }
            /// This is totally an operator
            else {
                /// while its precedence is greatest keeping popping

                do {
                    Operator tmp = ops.peekFirst();
                    if (tmp != null && op.getPrecedence() <= tmp.getPrecedence()) {
                        this.rpn.add(ops.pop());
                    }
                    /// hey thats it we are the top dog.
                    else {
                        break;
                    }
                } while (!ops.isEmpty());
                ops.push(op);
            }
        }

        while(!ops.isEmpty()) {
            rpn.add(ops.pop());
        }
        System.out.println(Arrays.toString(rpn.toArray()));
    }

    public double evaluate() {
        return Double.NaN;
    }

    private interface Operator{
        int getPrecedence();
        double apply(double op1, double op2);

        static Operator getOperator(char c) {
            switch(c) {
                case '+':
                    return new Plus();
                case '-':
                    return new Minus();
                case '*':
                    return new Multiply();
                case '/':
                    return new Divide();
                case '(':
                    return new Paren();
                default:
                    return null;
            }
        }
    }

    private static class Paren implements Operator{

        @Override
        public int getPrecedence() {
            return 0;
        }

        @Override
        public double apply(double op1, double op2) {
            return 0;
        }

        @Override
        public String toString() {
            return "(";
        }
    }
    private static class Multiply implements Operator{

        @Override
        public int getPrecedence() {
            return 3;
        }

        @Override
        public double apply(double op1, double op2) {
            return op1 * op2;
        }

        @Override
        public String toString() {
            return "*";
        }
    }
    private static class Divide implements Operator{

        @Override
        public int getPrecedence() {
            return 3;
        }

        @Override
        public double apply(double op1, double op2) {
            return op1 / op2;
        }

        @Override
        public String toString() {
            return "/";
        }
    }
    private static class Plus implements Operator{

        @Override
        public int getPrecedence() {
            return 2;
        }

        @Override
        public double apply(double op1, double op2) {
            return op1 + op2;
        }

        @Override
        public String toString() {
            return "+";
        }
    }

    private static class Minus implements Operator{

        @Override
        public int getPrecedence() {
            return 2;
        }

        @Override
        public double apply(double op1, double op2) {
            return op1 - op2;
        }

        @Override
        public String toString() {
            return "-";
        }
    }

    public static double evaluateExpression(String expr) {
        return new Calculator(expr).evaluate();
    }


}
