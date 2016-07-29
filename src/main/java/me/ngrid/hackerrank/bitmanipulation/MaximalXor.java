package me.ngrid.hackerrank.bitmanipulation;

import java.util.Scanner;

/**
 *
 */
public class MaximalXor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        sc.nextLine();
        int R = sc.nextInt();

        int max = L ^ R;

        for(int A = L; A < R; A ++) {
            for(int B = A + 1; B <= R; B ++) {
                max = Math.max(max, A ^ B);
            }
        }

        System.out.println(max);
    }
}
