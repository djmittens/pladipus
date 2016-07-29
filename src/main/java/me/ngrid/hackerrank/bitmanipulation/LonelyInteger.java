package me.ngrid.hackerrank.bitmanipulation;

import java.util.Scanner;

/**
 *
 */
public class LonelyInteger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        int tmp = 0;
        for(int i = 0; i < n; i ++ ) {
            tmp ^= sc.nextInt();
        }

        System.out.println(tmp);
    }
}
