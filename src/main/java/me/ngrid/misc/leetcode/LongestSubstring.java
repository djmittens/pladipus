package me.ngrid.misc.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters. For example,
 * the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb"
 * the longest substring is "b", with the length of 1.
 *
 * @see <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">l33tc0d3</a>
 */

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if ( s == null || s.isEmpty())
            return 0;
        if(s.length() == 1)
            return 1;

        int largest = 1, left = 0;
        HashMap<Character, Integer> buf = new HashMap<>();
        Integer tmp;

        for(int right = 0; right < s.length(); right ++) {

            // Oh noes we found a dupe.
            if( (tmp = buf.put(s.charAt(right), right)) != null && tmp >= left){
                // reset our currying position.
                largest =  Math.max(right - left, largest);
                left = ++ tmp;
            }
            else {
                largest =  Math.max(right - left + 1, largest);
            }

        }

        return largest;
    }
}
