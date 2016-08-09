package me.ngrid.hackerrank.strings;

import java.util.Scanner;

/**
 *
 */
public class RichieRich {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        char[] number = in.next().toCharArray();

        for (int i = 0; k >= 0 && i < number.length / 2; i++) {
            if (k == 0 && number[i] != number[number.length - (i + 1)]) {
                number = new char[]{'-', '1'};
                break;
            } else if (number[i] > number[number.length - (i + 1)]) {
                number[number.length - (i + 1)] = number[i];
                k--;
            } else if (number[i] < number[number.length - (i + 1)]) {
                number[i] = number[number.length - (i + 1)];
                k--;
            }
        }

        System.out.println(new String(number));
    }
}
