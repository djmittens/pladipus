package me.ngrid.hackerrank.bitmanipulation;

import java.util.Scanner;

/**
 *
 */
public class CounterGame {
    static long power2(long i) {
        long out = 0;
        for(long k = 0; k < 64; k ++) {
            long d = (2L << k) & i;
            if(d != 0) {
                out = d;
            }
        }
        return out;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < n; i++) {
            Long total = Long.parseUnsignedLong(sc.nextLine());
            int answ = 0;

            while (Long.compareUnsigned(total, 1) > 0 ) {
                long k = power2(total);
                if(Long.compareUnsigned(k, total) == 0) {
                    total = total >>> 1;
                } else {
                    total = total ^ k;
                }
                answ ++;
            }

            if(answ % 2 == 0) {
                System.out.println("Richard");
            } else {
                System.out.println("Louise");
            }
        }
    }
}
