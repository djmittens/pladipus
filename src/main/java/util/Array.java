package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 */
public class Array {
    private static Random random = new Random();

    public static <T extends Comparable<T>> boolean isSorted( T[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array to be sorted must not be empty");
        }
        for(int i = 1; i < array.length; i++) {
            if(array[i].compareTo(array[i-1]) < 0 ) {
                return false;
            }
        }
        return true;
    }


    public static Integer[] getArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
            array[i] =  random.nextInt();
        }
        Collections.shuffle(Arrays.asList(array));
        return array;
    }
}
