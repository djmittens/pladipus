package me.ngrid.misc.leetcode;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return
 * the shortest palindrome you can find by performing this transformation.
 * <p>
 * For example:
 * <p>
 * Given "aacecaaa", return "aaacecaaa".
 * <p>
 * Given "abcd", return "dcbabcd".
 *
 * @see <a href="https://leetcode.com/problems/shortest-palindrome/">l33tc0d3</a>
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        if(s.length() <= 1)
            return s;

        char[] ca = s.toCharArray();
        StringBuilder output = new StringBuilder();
        output.append(s.substring(1)).reverse();

        // Starting with the middle, find left adjacent palindromes.
        // Once found lets return the results.

        for(int i = ca.length / 2; i > 0; i--) {

            if(palinXyX(ca, i)) {
                output = new StringBuilder(s.substring(2 * i + 1)).reverse();
                break;
            }

            if(palinXX(ca, i)) {
                output = new StringBuilder(s.substring(2 * (i - 1) + 2)).reverse();
                break;
            }
        }

        return output.append(s).toString();
    }

    private boolean palinXyX(char[] ca, int cent) {
        return palin(ca, cent -1, cent + 1);
    }

    private boolean palinXX(char[] ca, int cent) {
        return palin(ca, cent -1, cent);
    }

    private boolean palin(char[] ca, int left, int right) {
        while(left >= 0) {
            if(right >= ca.length || ca[right] != ca[left])
                return false;
            -- left;
            ++ right;
        }
        return true;
    }

}
