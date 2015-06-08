package me.ngrid.searching;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SequentialSearchTest extends SearchTest{
    Searchable<Integer> s;

    public SequentialSearchTest(String trial, Integer[] data, Integer searchTerm, Integer position) {
        super(trial, data, searchTerm, position);
        s = SequentialSearch.getInstance(data);
    }

    @Test
    public void testContains() throws Exception {
        assertFound(s.find(getSearchTerm()));
    }
}