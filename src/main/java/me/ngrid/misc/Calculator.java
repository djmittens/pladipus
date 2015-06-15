package me.ngrid.misc;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.DoubleSummaryStatistics;

/**
 * A very simple calculator class that evaluates a mathematical expression such as
 * {@code (1 + 4) * 3 * 6 + (4 - 5 / 3)}
 * Should be no problem right?
 * This uses Reverse Polish Notation to evaluate the expression, and the Shunting-Yard
 * algorithm to construct it.
 *
 * This problem is a common interview question that should be answered in 1 hour.
 * Clearly this took me way more than that soooo....
 *
 * @see <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation#Postfix_algorithm">Reverse Polish Notation</a>
 * @see <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">Shunting-Yard</a>
 *
 */
public class Calculator {
    private Deque<Object> rpn;

    private Calculator (String expr) {
        /// Sanitize the input for space to increase readability.
        expr = expr.replaceAll(" ", "");
        // Rpn expression ( Reverse Polish Notation eg. postfix)
        // will endup looking something like this [1.0, 4.0, +, 3.0, *, 6.0, *, 4.0, 5.0, 3.0, /, -, +]
        // this Deque is used as a Queue
        this.rpn = new ArrayDeque<>();
        // Operation stack.
        // This deque is used as a Stack.
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

            // That means an operator was not found that maps to this character
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

            /// We are totally sure that this is an operator.
            else {

                /// while its precedence is greatest keep on poppin'
                do {
                    Operator tmp = ops.peekFirst();

                    // If we hit a paren its over, nothing before it matters.
                    // So lets just stop looking now.
                    if(tmp instanceof Paren) {
                        break;
                    }

                    // Iff its left associative and it is greater than or equals to whats in the stack
                    // write that stuff out into the output.
                    // This is necessary because if its a higher order operation, then it takes precedence.
                    if (tmp != null &&
                            tmp.getAssociativity() == Operator.Associativity.Left &&
                            op.getPrecedence() <= tmp.getPrecedence()) {
                        this.rpn.add(ops.pop());
                    }

                    // Iff its right associative and its precedence is smaller than whats in the stack
                    // write that stuff out into the output.
                    //
                    if (tmp != null &&
                            tmp.getAssociativity() == Operator.Associativity.Right &&
                            op.getPrecedence() > tmp.getPrecedence()) {
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

        // The very last number, or maybe the only one.
        if(sb.length() > 0)
            rpn.add(Double.valueOf(sb.toString()));

        // Just throw on top all of the remaining operations.
        while(!ops.isEmpty()) {
            rpn.add(ops.pop());
        }
    }

    /**
     * Evaluate the expression, and produce the result.
     * @return result
     */
    public double evaluate() {
        Deque<Double> answ = new ArrayDeque<>();
        Object tmp;
        // Lets consume our expression and evaluate it.
        while(!rpn.isEmpty()) {
            tmp = rpn.pop();
            // This is a number~!! lets save it.
            if(tmp instanceof Double) {
                answ.push((Double)tmp);
            }
            // This is an operator, lets hope we have the correct amount of numbers.
            // And then apply it!!
            else if (tmp instanceof Operator){
                double p2 = answ.pop();
                double p1 = answ.pop();
                answ.push(((Operator) tmp).apply(p1, p2));
            }
        }
        // We should only have the answer left, if not we did something wrong.
        assert(answ.size() == 1);
        return (Double)answ.pop();
    }

    private interface Operator{
        enum Associativity {Right, Left}

        /**
         * An operators precedence implies its order of operations.
         * the higher it is the stronger it is.
         * @return 2 - 4 the precedence of this operator
         */
        int getPrecedence();

        /**
         * 3 - 2 - 1
         * Would be evaluated left to right, because - is left associative.
         * However
         * 3 - (2 - 1)
         * will be evaluated from right to left, making it right associative(because of the parens
         * which count as the highest level of precedence)
         * Now the power operator is always right associative
         * 2 + 2^3^4
         * would always be evaluated from right to left, therefore there has got to be a special rule.
         * @return
         */
        Associativity getAssociativity();

        /**
         * Perform the operation (infix style)
         * @param op1 left operand
         * @param op2 right operand
         * @return the result.
         */
        double apply(double op1, double op2);

        /**
         * Java 1.8 wooo, we can have statics in the interfaces now.
         * @param c character which represents an operator
         * @return associated operator.
         */
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
                case '^':
                    return new Power();
                case '(':
                    return new Paren();
                default:
                    return null;
            }
        }
    }

    /**
     * Only open paren is considered an operator that is able to clobber operator precedence.
     * Therefore we treat it as No-op operator.
     * This operator does not perform any actions in fact it should probably throw an exception.
     */
    private static class Paren implements Operator{

        @Override
        public int getPrecedence() {
            throw new UnsupportedOperationException("There is no precedence or hope....");
        }

        @Override
        public Associativity getAssociativity() {
            throw new UnsupportedOperationException("Opening parens have no association duh");
        }

        @Override
        public double apply(double op1, double op2) {
            throw new UnsupportedOperationException("What does applying an open paren to two operands even mean?");
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
        public Associativity getAssociativity() {
            return Associativity.Left;
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
        public Associativity getAssociativity() {
            return Associativity.Left;
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
        public Associativity getAssociativity() {
            return Associativity.Left;
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
        public Associativity getAssociativity() {
            return Associativity.Left;
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

    private static class Power implements Operator{

        @Override
        public int getPrecedence() {
            return 4;
        }

        @Override
        public Associativity getAssociativity() {
            return Associativity.Right;
        }

        @Override
        public double apply(double op1, double op2) {
            return Math.pow(op1, op2);
        }

        @Override
        public String toString() {
            return "^";
        }
    }

    public static double evaluateExpression(String expr) {
        return new Calculator(expr).evaluate();
    }


}
