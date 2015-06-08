package me.ngrid.searching;

import me.ngrid.util.Array;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Parametrized harness to test various searching algorithms.
 */
@RunWith(Parameterized.class)
public abstract class SearchTest {
    private static final Random random = new Random();
    private Integer searchTerm;
    private Integer position;

    @Parameterized.Parameters(name="{0}")
    public static Iterable<Object[]> data(){
        Integer[] a;
        Integer pos;
        return Arrays.asList(new Object[][]{
                {"10 elements [match]",
                        a = Array.getIntegerArray(10), a[pos = random.nextInt(10)], pos},
                {"100 elements [match]",
                        a = Array.getIntegerArray(100), a[pos = random. nextInt(100)], pos},
                {"1000 elements [no match]",
                        a = Array.getIntegerArray(1_000), getElementNotInList(a), -1},
                {"400 000 elements [match] ",
                        a = Array.getIntegerArray(400_000), a[pos = random.nextInt(400_000)], pos},
                {"400 000 elements [no match]",
                        a = Array.getIntegerArray(400_000), getElementNotInList(a), -1},
                {"10 sorted elements [match]",
                        a = Array.getSortedIntegerArray(10), a[pos = random.nextInt(10)], pos},
                {"100 sorted elements [match]",
                        a = Array.getSortedIntegerArray(100), a[pos = random.nextInt(100)], pos},
                {"1000 sorted elements [no match]",
                        a = Array.getSortedIntegerArray(1000), getElementNotInList(a), -1},
                {"400 000 sorted elements [no match]",
                        a = Array.getSortedIntegerArray(400_000), getElementNotInList(a), -1},
                {"400 000 sorted elements [match]",
                        a = Array.getSortedIntegerArray(400_000), a[pos = random.nextInt(400_000)], pos},
        });
    }

    private static Integer getElementNotInList (Integer[] array) {
        List list = Arrays.asList(array);
        Integer out = random.nextInt();
        while(list.contains(out)) {
            out = random.nextInt();
        }

        return out;
    }

    public SearchTest(String trial, Integer[] data, Integer searchTerm, Integer position) {
        this.searchTerm = searchTerm;
        this.position = position;
    }

    /**
     * Assert that the right element was found.
     * @param idx index inside of the array that was found.
     */
    public void assertFound(Integer idx) {
        assertEquals("Wrong element was found " + idx, position, idx);
    }

    public Integer getSearchTerm() {
        return searchTerm;
    }
}
