package me.ngrid.sorting;

import org.junit.Test;

public class InsertionSortTest extends SortTest {
    private Sort<Integer> sort;

    public InsertionSortTest(String run, Integer[] array) {
        super(run, array);
        sort = InsertionSort.getInstance(array);
    }

    @Test(timeout=10_000)
    public void testSelectionSort() throws Exception{
        if(getArray().length <= 10_000)
            assertSorted(sort.sort());
    }
}
