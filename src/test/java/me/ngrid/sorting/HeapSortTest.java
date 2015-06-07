package me.ngrid.sorting;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeapSortTest extends SortTest {

    private Sort sort;

    public HeapSortTest(String run, Integer[] array) {
        super(run, array);
        sort = HeapSort.getInstance(array);
    }

    @Test
    public void testSort() throws Exception {
        assertSorted(sort.sort());
    }
}