package me.ngrid.util;

import java.util.Arrays;
import java.util.Random;

/**
 */
public class Array {
    private static final Random r = new Random();

    /**
     * Check if the provided array is indeed sorted in O(n) time
     *
     * Best Worst and Average cases are all O(n).
     * @param array unit of test
     * @param <T> Naturally sorted array.
     * @return {true|false} iff the array follows a natural order.
     */
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
    public static<T> void swap(T[] list, int left, int right) {
        T t = list[left];
        list[left] = list[right];
        list[right] = t;
    }

    /**
     * Return a r integer array of a given size.
     * @param size of the array to return
     * @return a generated r array.
     */
    public static Integer[] getIntegerArray(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < array.length; i++) {
            array[i] =  r.nextInt();
        }
        return array;
    }

    /**
     * Generate a r integer array, then sort it.
     * @param size size of the array to generate
     * @return sorted r array.
     */
    public static Integer[] getSortedIntegerArray(int size) {
        Integer[] out = getIntegerArray(size);
        Arrays.sort(out);
        return  out;
    }

    /**
     * Shuffle a character array because the java api doesnt roll like that .
     * This algorithm follows the Fisher-Yates shuffle
     * http://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
     * @param array shuffle it yo.
     * @return same array as came in but you can chain it now.
     */
    public static char[] shuffle(char[] array) {
        for(int i = array.length - 1; i > 0; i--) {
            int idx = r.nextInt(i);
            char tmp = array[i];
            array[i] = array[idx];
            array[idx] = tmp;
        }
        return array;
    }


    public static void print(int[] ar) {
        String s = Arrays.stream(ar).mapToObj(Integer::toString).collect(java.util.stream.Collectors.joining(" "));
        System.out.println(s);
    }
}
