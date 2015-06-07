package me.ngrid.util;

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

    /**
     * Swap two elements inplace inside of an array
     * @param list array to perform the action on
     * @param left index inside of array
     * @param right index inside of array
     * @param <T> the type of the array elements must be Comparable
     */
    public static<T extends Comparable<T>> void swap(T[] list, int left, int right) {
        T t = list[left];
        list[left] = list[right];
        list[right] = t;
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
