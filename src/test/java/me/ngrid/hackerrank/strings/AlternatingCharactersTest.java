package me.ngrid.hackerrank.strings;

import org.junit.Test;

import static org.junit.Assert.*;
import static me.ngrid.hackerrank.strings.AlternatingCharacters.minDeletions;

/**
 * Created by grimm on 7/23/16.
 */
public class AlternatingCharactersTest {
    @Test
    public void test_average_case () {
        assertEquals("expected a different number of deletions",3, minDeletions("AAAA"));
        assertEquals("expected a different number of deletions",4, minDeletions("BBBBB"));
        assertEquals("expected a different number of deletions",0, minDeletions("ABABABAB"));
        assertEquals("expected a different number of deletions",4, minDeletions("AAABBB"));
    }
}