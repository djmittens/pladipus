package me.ngrid.hackerrank.strings;

import org.junit.Test;

import static org.junit.Assert.*;
import static me.ngrid.hackerrank.strings.BeautifulStrings.minReplaces;

public class BeautifulStringsTest {
    @Test
    public void test_an_average_case() {
        assertEquals("Minimum replaces not reached", 3, minReplaces("0100101010"));
    }

    @Test
    public void test_skipped_one () {
        assertEquals("Minimum replaces not reached", 2, minReplaces("0101010"));
    }

    @Test
    public void test_empty_string () {
        assertEquals("Empty string should be valid", 0, minReplaces(""));
    }

    @Test
    public void test_short_string () {
        assertEquals("Short s hell string", 0, minReplaces("01"));
    }
}