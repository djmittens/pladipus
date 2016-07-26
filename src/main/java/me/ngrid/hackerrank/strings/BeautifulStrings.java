package me.ngrid.hackerrank.strings;

import java.util.Scanner;

public class BeautifulStrings {
    static int minReplaces(CharSequence data) {
        int count = 0;
        for(int i = 0; i <= data.length() - 3;) {
            CharSequence s = data.subSequence(i, i + 3);
            if(s.equals("010")) {
                count ++;
                i += 3;
            } else {
                i++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        String data = sc.nextLine();
        System.out.println(minReplaces(data));
    }
}
