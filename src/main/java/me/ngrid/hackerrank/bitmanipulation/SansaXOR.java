package me.ngrid.hackerrank.bitmanipulation;

import java.util.Scanner;

/**
 *
 */
public class SansaXOR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0; i < n; i  ++ ) {
            sc.nextLine();
            int len = sc.nextInt();
            if(len % 2 == 0) {
                sc.nextLine();
                System.out.println("0");
            } else {
                long answ = 0;
                for(int k = 0; k < len; k ++ ) {
                    long num = sc.nextLong();
                    if(k % 2 == 0) answ ^= num;
                }
                System.out.println(answ);
            }
        }
    }
}
