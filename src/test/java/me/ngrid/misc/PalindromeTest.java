package me.ngrid.misc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class PalindromeTest {
    private String str;
    private boolean palindrome, palindromic;

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Simple palindrome", "aabaa", true, true},
                {"Simple symmetric palindrome", "aaaa", true, true},
                {"Simple palindrome anagram", "bba", false, true},
                {"not a palindrome", "bbaadc", false, false},
                {"a large random palindrome", Palindrome.getPalindrome(103), true, true},
                {"a large random palindromic", Palindrome.getPalindromic(100), false, true},
                {"a large random string", Palindrome.getRandomString(100), false, false},
        });
    }

    public PalindromeTest(String name, String str, boolean palindrome, boolean palindromic) {
        this.palindrome = palindrome;
        this.palindromic = palindromic;
        this.str = str;
    }

    @Test
    public void testIsPalindrome() throws Exception {
        assertEquals("This string is not matching expectations: " + str, palindrome, Palindrome.isPalindrome(str));
    }

    @Test public void testIsPalindromicArray() throws Exception {
        assertEquals(palindromic, Palindrome.isPalindromicArray(str));
    }

    @Test
    public void testIsPalindromicHash() throws Exception {
        assertEquals(palindromic, Palindrome.isPalindromicHash(str));
    }
}