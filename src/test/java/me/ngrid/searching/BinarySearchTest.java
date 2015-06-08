package me.ngrid.searching;

import me.ngrid.util.Array;
import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTest extends SearchTest{
    Searchable<Integer> search;
    Integer[] data;

    public BinarySearchTest(String trial, Integer[] data, Integer searchTerm, Integer position) {
        super(trial, data, searchTerm, position);
        this.data = data;
    }

    /**
     * Passes without any assertions on non sorted input.
     * @throws Exception
     */
    @Test
    public void testFind() throws Exception {
        if(Array.isSorted(data)) {
            search = BinarySearch.getInstance(data);
            assertFound(search.find(getSearchTerm()));
        }
    }
}