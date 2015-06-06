package util;

/**
 */
public class Array {
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
}
