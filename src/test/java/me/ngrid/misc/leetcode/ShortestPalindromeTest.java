package me.ngrid.misc.leetcode;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class ShortestPalindromeTest {

    @Test
    public void testShortestPalindrome() throws Exception {
        new ShortestPalindrome().shortestPalindrome("abbacd");
    }

    @Test
    public void testSomeMore() throws Exception {
        new ShortestPalindrome().shortestPalindrome("aabbaad");
    }


    @Test
    public void dudeThisSucks() throws Exception {
        assertEquals("ababbabbbababbbabbaba", new ShortestPalindrome().shortestPalindrome("ababbbabbaba"));
    }

    @Test
    public void wellThisIsJustBad() throws Exception {
        new ShortestPalindrome().shortestPalindrome("ba");
    }

    @Test
    public void yetAnotherOffBy1() throws Exception {
        assertEquals("daabbaad", new ShortestPalindrome().shortestPalindrome("aabbaad"));
    }

}