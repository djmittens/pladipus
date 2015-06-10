package me.ngrid.misc;

import me.ngrid.util.Array;

import java.util.*;

/**
 * A palindrome is a string that is read the same backwards and forwards.
 * Palindromes have two interesting properties, and that is every character can only occur even amount of times.
 * An exception to this rule is that only 1 character may appear an odd amount of times. Therefore palindrome can have:
 *
 *  1. Even number of each character.
 *  2. Only one character with an odd amount.
 *
 * By relaxing the rule on the ordering of characters we can tell if a string could potentially be a palindrome by
 * checking those two properties.
 */
public class Palindrome {
    private static final Random r = new Random();
    /**
     * Check whether this string is a palindrome.
     * @return {true | false}
     */
    public static boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }

    public static String getPalindrome(int size) {
        StringBuilder sb = new StringBuilder(getRandomString(size / 2));
        if(size % 2 > 0) {
            sb.append(getRandomChar());
        }
        sb.append(new StringBuilder(sb).reverse());
        return sb.toString();
    }

    /**
     * Generate an unordered string, which if rearranged could form palindrome.
     *
     * This string will contain even amounts of each of the characters except for
     * a single odd set if the size is odd.
     *
     * @param size amount of characters that will be generated.
     * @return a random generated palindromic string.
     */
    public static String getPalindromic(int size) {
        return new String(Array.shuffle(getPalindrome(size).toCharArray()));
    }

    /**
     * Generate a random string.
     * @param size amount of characters to place into the string
     * @return generated string with garbage in it.
     */
    public static String getRandomString(int size) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            // 24 because of our alphabet size, i dont want to get crazy
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    private static final char[] dictionary = new char[]{'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'v',
            'u', 'w', 'x', 'y', 'z'};

    public static char getRandomChar () {
        return dictionary[r.nextInt(dictionary.length)];
    }

    /**
     * Array based implementation.
     * Check if the string can be rearranged to form a palindrome.
     * @param str Awesome and stuff.
     * @return the answer
     */
    public static boolean isPalindromicArray (String str) {

        char[] chars = str.toCharArray();

        // In order to use an array we are going to have to sort each string.
        // The only other way of doing this is  O(n<sup>2</sup>) meaning sorting properly
        // would on average be faster.
        Arrays.sort(chars);
        int count = 1;
        int unique = 0;

        // Iterate every character by checking the previous one for equality.
        for(int i = 1; i < chars.length; i++) {
            if(chars[i - 1] == chars[i]) {
                // Because of the sort the same character will only appear in sequence.
                ++count;
            }
            else {
                // Iff this character is out of sequence reset the counter.
                // And tally up the results.
                unique += (count) % 2;
                count = 1;
            }
        }

        // Finally tally up the results because we ended up on the last letter.
        unique += count % 2;

        // Only one unique(eg. odd amount) is allowed.
        return unique < 2;
    }

    /**
     * Hashmap implementation
     * Check if the string can be rearranged to form a palindrome.
     * @param str Awesome and stuff.
     * @return the answer
     */
    public static boolean isPalindromicHash (String str) {
        // A HashMap provides a data structure to uniquely map each character to a bucket.
        // This bucket will store the counts of each character as they appear in the string.
        HashMap<Character, Integer> map = new HashMap<>();
        map.entrySet();
        Integer count;

        // Iterate over each character in the string.
        for(Character c : str.toCharArray()) {
            count = map.get(c);

            // check if we have seen it before, if we haven't its ok we just initialize the count to 0
            if (count == null) count = 0;

            //  Place the count for the character back into the hash map.
            map.put(c, ++count );
        }

        int unique = 0;
        // Iterate over all of the values inside the HashMap and tally up results for odd counts.
        for(Integer i: map.values()) {
            // Check if the count is odd.
            if(i % 2 != 0)
                unique++;
        }

        // Up to a single Odd will signify a palindrome.
        return unique < 2;
    }

}
