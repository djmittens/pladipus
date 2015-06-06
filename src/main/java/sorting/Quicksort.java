package sorting;

import java.util.Arrays;
import java.util.Random;

/**
 */
public class  Quicksort <T extends Comparable<T>> {

    public static <E extends Comparable<E>> void sort(E[] list) {
        new Quicksort<E>(list).sort();
    }

    private T[] list;
    private Random random;

    Quicksort(T[] list) {
        this.list = list;
        this.random = new Random();
    }

    T[] sort() {
        sort(0, list.length - 1);
        return list;
    }

    void sort(int left, int right) {
        if(left < right) {
            int pi = partition(left, right);
            sort(left, pi - 1);
            sort(pi + 1, right);
        }
    }

    protected int partition(int left, int right) {
        int pivot = pickPivot(left, right);
        // Place the pivot all the way to the right
        swap(pivot, right);
        // Value in the store is guranteed to be < pivot
        int store = left;
        for(int i = left; i < right - 1; i ++) {
            // Comparing each value to the pivot to see if they are greater.
            if(list[i].compareTo(list[right]) >= 0  ) {
                swap(store, i);
                store ++;
            }
        }
        // In the end store points to last position in the subarray that is sorted.
        swap(store, right);
        return store;
    }


    protected  int pickPivot(int left, int right) {
       if(left >= right)
           throw new IllegalArgumentException("Partition: Left must be less than Right");
        // We pick the pivot randomly.
        return (random.nextInt(right - left)) + left;
    }

    protected void swap(int left, int right) {
        T t = list[left];
        list[left] = list[right];
        list[right] = t;
    }
}
