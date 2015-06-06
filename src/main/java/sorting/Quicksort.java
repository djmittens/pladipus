package sorting;

import util.Array;

import java.awt.geom.QuadCurve2D;
import java.util.Arrays;
import java.util.Random;

/**
 */
public abstract class  Quicksort <T extends Comparable<T>> {

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
        System.out.println("Sorting " + Arrays.toString(list));
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
        System.out.println("Pivot " + list[pivot]);
        // Value in the store is guranteed to be < pivot
        int store = left;
        for(int i = left; i < right; i ++) {
            // Comparing each value to the pivot to see if they are greater.
            if(list[i].compareTo(list[right]) <= 0  ) {
                swap(store, i);
                store ++;
            }
        }
        System.out.println(Arrays.toString(list));
        // In the end store points to last position in the subarray that is sorted.
        swap(store, right);
        return store;
    }


    /**
     * Select a pivot point within this range.
     * @param left
     * @param right
     * @return
     */
    protected abstract int pickPivot(int left, int right);

    protected final void swap(int left, int right) {
        T t = list[left];
        list[left] = list[right];
        list[right] = t;
    }

    public class RandomPivot extends Quicksort<T>{

        RandomPivot(T[] list) {
            super(list);
        }

        protected  int pickPivot(int left, int right) {
            if(left >= right)
                throw new IllegalArgumentException("Partition: Left must be less than Right");
            // We pick the pivot randomly.
            int pivot = (random.nextInt(right - left)) + left;
            assert(pivot >= left && pivot <= right);
            return pivot;
        }
    }


    /**
     * This implementation uses right most
     */
    public class NaivePivot extends Quicksort<T>{

        NaivePivot(T[] list) {
            super(list);
        }

        protected  int pickPivot(int left, int right) {
            return right;
        }
    }

    public class RandomIterative extends Quicksort<T> {
        
    }
}
