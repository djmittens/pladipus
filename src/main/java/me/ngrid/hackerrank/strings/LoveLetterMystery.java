package me.ngrid.hackerrank.strings;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by grimm on 7/23/16.
 */
public class LoveLetterMystery {
    public static void print(int[] ar) {
        String s = Arrays.stream(ar).mapToObj(Integer::toString).collect(java.util.stream.Collectors.joining(" "));
        System.out.println(s);
    }
    static int minReductions(CharSequence cs) {
        int l = cs.length() /  2;
        int count = 0;
        for(int i = 0; i < l; i ++ ) {
            int c1 = cs.charAt(i);
            int c2 = cs.charAt(cs.length() - 1 - i);

            if(c1 != c2) {
                count += Math.abs(c1 - c2);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println(minReductions(sc.nextLine()));
        }
    }
}
