package me.ngrid.hackerrank.strings;

import java.util.*;

/**
 *
 */
public class Gemstones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rocks = sc.nextInt();
        sc.nextLine();

        Map<Character, Integer> tomb = new HashMap<>();

        int gemElements = 0;

        for (int i = rocks; i > 0; i --) {
            String s = sc.nextLine();
            Set<Character> rock = new HashSet<>();

            for(int k = 0; k < s.length(); k ++) {
               rock.add(s.charAt(k));
            }

            for(Character c : rock) {
                int count = tomb.getOrDefault(c, 0) + 1;
                tomb.put(c, count);

                if(count == rocks)
                    ++ gemElements;
            }
        }

        System.out.println(gemElements);
    }
}
