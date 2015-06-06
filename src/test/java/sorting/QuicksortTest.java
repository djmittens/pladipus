package sorting;

import org.junit.Assert;
import org.junit.Test;
import util.Array;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;

/**
 *
 */
public class QuicksortTest {


    @Test
    public void testSort() throws Exception {
        Integer[] array = new Integer[10];
        for(int i = 0; i < array.length; i++) {
            array[i] = i;
        }

        Collections.shuffle(Arrays.asList(array));
        Quicksort.sort(array);
        assertTrue("This sort does not sort(eg. doesn't work) " + Arrays.toString(array), Array.isSorted(array));
    }
}