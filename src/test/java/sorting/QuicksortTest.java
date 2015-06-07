package sorting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(Parameterized.class)
public class QuicksortTest {

    public static Integer[] getArray(int size) {
        Integer[] array = new Integer[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {
        Integer[] ten = getArray(10);
        Collections.shuffle(Arrays.asList(ten));

        return Arrays.asList(new Object[][]{
                {"Shuffled #10", ten}
        });
    }

    private Quicksort s1, s2, s3;

    public QuicksortTest(String test, Integer[] i) {
        System.out.println("Testing entries of size " + i.length);
        this.s1 = new Quicksort.NaivePivot<>(Arrays.copyOf(i, i.length));
        this.s2 = new Quicksort.RandomPivot<>(Arrays.copyOf(i, i.length));
        this.s3 = new Quicksort.RandomIterative<>(Arrays.copyOf(i, i.length));
    }

    @Test
    public void testSort() throws Exception {
        System.out.println("# Naive Approach");
        assertSorted(s1);
        System.out.println("# Randomly Pivoted");
        assertSorted(s2);
        System.out.println("# Iterative Random Pivot");
        assertSorted(s3);
    }

    private void assertSorted(Quicksort sort) {
        long before = System.currentTimeMillis();
        assertTrue("This sort did not sort", Array.isSorted(sort.sort()));
        long after = System.currentTimeMillis();
        System.out.println("Time(ms): " + (after - before));
    }

    @Test
    public void testOrdered () throws Exception {

    }

    @Test
    public void testReverseOrdered () throws Exception {

    }

}