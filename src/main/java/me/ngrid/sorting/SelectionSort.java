package me.ngrid.sorting;

/**
 * Second worst sort ever (selection sort, and bubble sort are in the first cause they suck), in fact the only way
 * this sort can perform well at all, is if
 *  1. the size of the list is very small (like 4)
 *  2. list is already partially sorted.
 *
 * Performance:
 * Best:    O(n)
 * Worst:   O(n<sup>2</sup>)
 * Average: O(n<sup>2</sup>)
 */
public class SelectionSort<T extends Comparable<T>> implements Sort<T> {
    private boolean sorted;

    public static <E extends Comparable<E>> Sort<E> getInstance(E[] list) {
        return new SelectionSort<>(list);
    }

    private T[] list;
    private SelectionSort(T[] list) {
        this.list = list;
        this.sorted = false;
    }

    public T[] sort() {
        if(!sorted)
            for(int i = 1; i < list.length; i ++) {
                insert(i, list[i]);
            }
        sorted = true;
        return list;
    }

    /**
     * Attempt to insert the given value in the already sorted block of the array.
     * @param pos index of where the sorted block ends.
     * @param value to be inserted into the sorted block
     */
    void insert(int pos, T value) {
        // Beginning with the end of the block
        int i  = pos - 1;
        // Shift every element (already sorted) that is greater than the value
        // up by 1. ( effectively overriding our value but we already have it stored)
        for( ; i >= 0 && list[i].compareTo(value) > 0; i--) {
            list[i + 1] = list[i];
        }
        // At this point i+1 represents the free cell we made space for during the last iteration of the for loop.
        // So setting this cell to the value completes the insertion.
        list[i + 1] = value;
    }
}
