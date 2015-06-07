package sorting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import util.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(Parameterized.class)
public class QuicksortTest {


    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Object[]> data() {

        return Arrays.asList(new Object[][]{
                {"Shuffled #10", Array.getArray(10)},
                {"Shuffled #100", Array.getArray(100)},
                {"Shuffled #1000", Array.getArray(1000)},
                {"Shuffled #400_000", Array.getArray(400_000)},
        });
    }

    private Quicksort s1, s2, s3;
    private Integer[] data;

    public QuicksortTest(String test, Integer[] i) {
        this.s1 = new Quicksort.NaivePivot<>(Arrays.copyOf(i, i.length));
        this.s2 = new Quicksort.RandomPivot<>(Arrays.copyOf(i, i.length));
        this.s3 = new Quicksort.RandomIterative<>(Arrays.copyOf(i, i.length));
        this.data = i;
    }

    @Test
    public void testSort() throws Exception {
        String out = "# Naive Approach";
        out += "\n" + assertSorted(s1);
        System.out.println("# Randomly Pivoted");
        out += "\n" + assertSorted(s2);
        System.out.println("# Iterative Random Pivot");
        out += "\n" + assertSorted(s3);
        out += "\n# Arrays.sort()";
        long before = System.currentTimeMillis();
        Arrays.sort(data);
        long after = System.currentTimeMillis();
        System.out.println(out + "\nTime(ms): " + (after - before));
    }

    private String assertSorted(Quicksort sort) {
        long before = System.currentTimeMillis();
        assertTrue("This sort did not sort", Array.isSorted(sort.sort()));
        long after = System.currentTimeMillis();
        return "Time(ms): " + (after - before);
    }
}
