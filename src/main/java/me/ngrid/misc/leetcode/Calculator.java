package me.ngrid.misc.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Alternative implementation of the calculator algorithm.
 * @see <a href="https://leetcode.com/problems/basic-calculator/">l33tc0d3</a>
 */
public class Calculator {
     public int calculate(String s) {
         s = s.replaceAll(" ", "");
         Deque<Character> ops = new ArrayDeque<>();
         Deque<Object> rpn = new ArrayDeque<>();
         StringBuilder sb = new StringBuilder();

         for(char c : s.toCharArray()) {
             int prec = getPrecedence(c);

             if(prec > 0 && sb.length() > 0) {
                 rpn.add(Integer.valueOf(sb.toString()));
                 sb = new StringBuilder();
             }

             if( c == '(') {
                 ops.push(c);
             }

             else if(c == ')') {
                 // lets find us some booteh
                 Character tmp;
                 if(sb.length() > 0 ) {
                     rpn.add(Integer.valueOf(sb.toString()));
                     sb = new StringBuilder();
                 }

                 while(!ops.isEmpty() && !(tmp = ops.pop()).equals('(') ) {
                     rpn.add(tmp);
                 }
             }

             // we have ourselves an operator
             else if(prec > 0) {

                 while(!ops.isEmpty()){
                     char tmp = ops.peek();
                     if(tmp == '('){
                         break;
                     }
                     //pop if left associative eg. not ^
                     if(getAssociativity(tmp) == 0 && getPrecedence(tmp) >= prec) {
                         rpn.add(ops.pop());
                     }
                     //pop if right associative eg. ^
                     else if(getAssociativity(tmp) == 1 && getPrecedence(tmp) < prec) {
                         rpn.add(ops.pop());
                     }
                     // Well if we arent popping anymore we are done.
                     else {
                         break;
                     }
                 }
                 //finally push this shit
                 ops.push(c);
             }

             //totally a number
             else {
                 sb.append(c);
             }
         }
         if(sb.length() > 0)
             rpn.add(Integer.valueOf(sb.toString()));
         while(!ops.isEmpty()) {
             rpn.add(ops.pop());
         }

         Deque<Integer> nums = new ArrayDeque<>();
         while(!rpn.isEmpty()) {
             Object tmp = rpn.poll();
             if(tmp instanceof Integer)
                 nums.push((Integer)tmp);
             else if(tmp instanceof Character) {
                 nums.push(apply((Character)tmp, nums.pop(), nums.pop()));
             }
         }
         return nums.pop();
     }

     private int apply(Character op, int i2, int i1) {
         switch(op) {
             case '+':
                 return i1 + i2;
             case '-':
                 return i1 -i2;
             case '*':
                 return i1 * i2;
             case '/':
                 return i1 / i2;
             case '^':
                 return (int)Math.pow(i1, i2);
             default:
                 return 0;
         }
     }

     private int getPrecedence(char c) {
         switch(c) {
             case '+':
             case '-':
                 return 2;
             case '/':
             case '*':
                 return 3;
             case '^':
                 return 4;
             case '(':
                 return 1;
             default:
                 return 0;
         }
     }

     private int getAssociativity(char c) {
         switch(c) {
             case('^'):
                 return 1;
             default:
                 return 0;
         }
     }

}
