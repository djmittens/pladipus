package me.ngrid.hackerrank.strings;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class CommonLetters {
    public static void main(String[] args) {
        String s1 = "abcfged";
        String s2 = "abfdzerg";

        Set<Character> set = new HashSet<>();
        for(int i = 0; i < s1.length(); i++) {
            set.add(s1.charAt(i));
        }

        Set<Character> out = new HashSet<>();
        for(int i = 0; i < s2.length(); i ++ ) {
            Character c = s2.charAt(i);
            if(set.contains(c)) {
                out.add(c);
            }
        }

        out.forEach(System.out::print);
    }
}
