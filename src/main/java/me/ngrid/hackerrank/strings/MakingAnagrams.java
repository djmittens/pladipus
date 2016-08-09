package me.ngrid.hackerrank.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 */
public class MakingAnagrams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s1 =  sc.nextLine().toCharArray();
        char[] s2 = sc.nextLine().toCharArray();

        Arrays.sort(s1);
        Arrays.sort(s2);

        int count = 0;
        int i2 = 0;
        for(int i1 = 0; i1 <= s1.length;) {
            if(i1 == s1.length || i2 == s2.length)  {
                count += Math.max(s1.length - i1, s2.length - i2);
                break;
            }
            else if(s1[i1] < s2[i2]) {
                i1 ++;
                count++;
            } else if (s1[i1] > s2[i2]) {
                i2++;
                count++;
            } else {
                ++i2;
                ++i1;
            }
        }

        System.out.println(count);
    }
}
