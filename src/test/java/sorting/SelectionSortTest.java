package sorting;

import org.junit.Test;

public class SelectionSortTest extends SortTest {
    private Sort<Integer> sort;

    public SelectionSortTest(String run, Integer[] array) {
        super(run, array);
        sort = SelectionSort.getInstance(array);
    }

    @Test(timeout=10_000)
    public void testSelectionSort() throws Exception{
        if(getArray().length <= 10_000)
            assertSorted(sort.sort());
    }
}
