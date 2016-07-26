package me.ngrid.hackerrank.strings;

import java.util.Scanner;

public class AlternatingCharacters {
    static int minDeletions(CharSequence cs) {
        int out = 0;
        int repeatCount = 0;

        for (int i = 1; i < cs.length(); i ++) {
            if(cs.charAt(i - 1) == cs.charAt(i)) {
                repeatCount ++;
            } else {
                out += repeatCount;
                repeatCount = 0;
            }
        }

        return out + repeatCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++) {
            System.out.println(minDeletions(sc.nextLine()));
        }
    }
}
