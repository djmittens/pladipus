package me.ngrid.hackerrank.strings;

import java.util.Scanner;

/**
 *
 */
public class FunnyStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        while (n > 0) {
            System.out.println(isFunny(sc.nextLine()));
            n --;
        }
    }

    /**
     *  ******
     *  i
     * @param s
     * @return
     */
    static String isFunny(String s) {
        int len = s.length();
        int last = len - 1;
        for(int i = 0; i < last; i ++) {
            int s0 = s.charAt(i + 1) - s.charAt(i);
            int s1 = s.charAt(last - (i+1)) - s.charAt(last - i);
            if(Math.abs(s0) != Math.abs(s1)) {
                return"Not Funny";
            }
        }
        return "Funny";
    }
}
