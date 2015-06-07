package me.ngrid.sorting;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import me.ngrid.util.Array;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 */
@RunWith(Parameterized.class)
public abstract class SortTest {

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        Integer[] sorted = Array.getArray(1000);
        Arrays.sort(sorted);

        return Arrays.asList(new Object[][]{
                {"Shuffled #10", Array.getArray(10)},
                {"Shuffled #100", Array.getArray(100)},
                {"Shuffled #1000", Array.getArray(1000)},
                {"Sorted #1000", sorted},
                {"Shuffled #400_000", Array.getArray(400_000)},
        });
    }
    private Integer[] array;

    public SortTest(String run, Integer[] array) {
        this.array = array;
    }

    public Integer[] getArray() {
        return array;
    }

    protected void assertSorted(Comparable[] data) {
        assertTrue("This sort did not sort right " + Arrays.toString(data), Array.isSorted(data));
    }
}
