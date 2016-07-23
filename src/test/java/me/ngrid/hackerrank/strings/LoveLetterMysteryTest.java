package me.ngrid.hackerrank.strings;

import org.junit.Test;

import static org.junit.Assert.*;
import static me.ngrid.hackerrank.strings.LoveLetterMystery.minReductions;

public class LoveLetterMysteryTest {
    @Test
    public void test_simple_cases () {
        assertEquals(2, minReductions("abc"));
        assertEquals(0, minReductions("abcba"));
    }
}