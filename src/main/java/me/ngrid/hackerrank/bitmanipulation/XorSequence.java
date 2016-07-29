package me.ngrid.hackerrank.bitmanipulation;

import java.util.Scanner;

/**
 * incomplete https://www.hackerrank.com/challenges/xor-se
 */
public class XorSequence {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int Q = in.nextInt();
        for (int a0 = 0; a0 < Q; a0++) {
            long L = in.nextLong();
            long R = in.nextLong();
            long ans = 0;
            if ((L - R) % 2 == 0) {
//                for (long i = 0; i <= L; i++) {
//                    ans ^= i;
//                }
                for(long i = R; i > 0; i -= 2 ) {
                    ans ^= i;
                }
            } else {
                for(long i = R; i > L; i -= 2 ) {
                    ans ^= i;
                }

            }
//            while (R > L) {
//                ans ^= R;
//                R -= 2;
//            }

            System.out.println(ans);
        }
    }
}
