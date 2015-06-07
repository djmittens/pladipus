package sorting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(Parameterized.class)
public class QuicksortTest {

    private static Integer[] getArray(int size) {
        Integer[] array = new Integer[size];
        for(int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt();
        }
        Collections.shuffle(Arrays.asList(array));
        return array;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Shuffled #10", getArray(10)},
                {"Shuffled #100", getArray(100)},
                {"Shuffled #1000", getArray(1000)},
                {"Shuffled #400_000", getArray(400_000)},
        });
    }

    private Quicksort s1, s2, s3;
    private Integer[] data;

    public QuicksortTest(String test, Integer[] i) {
        System.out.println("Testing entries of size " + i.length);
        this.s1 = new Quicksort.NaivePivot<>(Arrays.copyOf(i, i.length));
        this.s2 = new Quicksort.RandomPivot<>(Arrays.copyOf(i, i.length));
        this.s3 = new Quicksort.RandomIterative<>(Arrays.copyOf(i, i.length));
        this.data = i;
    }

    @Test
    public void testSort() throws Exception {
        System.out.println("# Naive Approach");
        assertSorted(s1);
        System.out.println("# Randomly Pivoted");
        assertSorted(s2);
        System.out.println("# Iterative Random Pivot");
        assertSorted(s3);
        System.out.println("# Arrays.sort()");
        long before = System.currentTimeMillis();
        Arrays.sort(data);
        long after = System.currentTimeMillis();
        System.out.println("Time(ms): " + (after - before));
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