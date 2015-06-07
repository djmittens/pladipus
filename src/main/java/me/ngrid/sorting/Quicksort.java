package me.ngrid.sorting;

import me.ngrid.util.Array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
 * Quicksort is a simplified variation of the median sort, yet it provides a much better average case performance.
 * Worst:    O(n<sup>2</sup>)
 * Best:     O(nlog(n))
 * Average:  O(nlog(n))
 *
 * Worst case scenario appears when using an already sorted list which creates an empty partition
 * on the right every time.
 *
 * Provided are 3 variations on the algorithm, however please note, recursive implementations are faster, than queue
 * based one, but will most definately have stack overflow problems for large enough lists with deep recursions.
 */
public class  Quicksort <T extends Comparable<T>> implements Sort<T> {

    public static <E extends Comparable<E>> Sort<E> createRecursive(E[] list) {
        return new Quicksort<>(list, new RandomPivot());
    }

    public static <E extends Comparable<E>> Sort<E> createNaiveRecursive(E[] list) {
        return new Quicksort<>(list, new NaivePivot());
    }

    public static <E extends Comparable<E>> Sort<E> createIterative(E[] list) {
        return new Iterative<>(list, new RandomPivot());
    }



    protected T[] list;
    private PivotPicker picker;

    Quicksort(T[] list, PivotPicker picker) {
        this.list = list;
        this.picker = picker;

    }

    public T[] sort() {
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

    /**
     * Pick a pivot then,
     * Run through all elements from left to right building a store on the left side corresponding to all
     * the elements that are smaller than pivot.
     * @param left index of the left bound
     * @param right index of the right bound
     * @return index of the new pivot location
     */
    protected int partition(int left, int right) {
        int pivot = picker.pickPivot(left, right);
        // Place the pivot all the way to the right
        Array.swap(list, pivot, right);
        // Value in the store is guranteed to be < pivot
        int store = left;
        for(int i = left; i < right; i ++) {
            // Comparing each value to the pivot to see if they are greater.
            if(list[i].compareTo(list[right]) <= 0  ) {
                Array.swap(list, store, i);
                store ++;
            }
        }
        // In the end store points to last position in the subarray that is sorted.
        Array.swap(list, store, right);
        return store;
    }


    private interface PivotPicker {
        /**
         * Pick a pivot element within two bounds
         *
         * @param left index of the left bound(inclusive)
         * @param right index of the right bound(inclusive)
         * @return [pivot] index
         */
        int pickPivot(int left, int right);
    }

    private static class RandomPivot implements PivotPicker {

        private static final Random random = new Random();

        /**
         * Using a random generator, pick a random element from the given range.
         * @param left index of the left bound(inclusive)
         * @param right index of the right bound(inclusive)
         * @return index of the pivot that we have selected
         * @throws IllegalArgumentException when the range defined is empty or reversed.
         */
        @Override
        public  int pickPivot(int left, int right) {
            if(left >= right)
                throw new IllegalArgumentException("Partition: Left must be less than Right");
            // We pick the pivot randomly.
            int pivot = (random.nextInt(right - left)) + left;
            assert(pivot >= left && pivot <= right);
            return pivot;
        }
    }

    private static class NaivePivot implements PivotPicker {

        @Override
        public int pickPivot(int left, int right) {
            return right;
        }
    }


    private static class Iterative<T extends Comparable<T>> extends Quicksort<T> {

        Deque<int[]> ops;


        Iterative(T[] list, PivotPicker picker) {
            super(list, picker);
            ops = new ArrayDeque<>((int)Math.log(list.length));
        }

        @Override
        public T[] sort() {
            ops.add(new int[] {0, list.length - 1} );
            while(!ops.isEmpty()) {
                int [] c = ops.pop();
                sort(c[0], c[1]);
            }
            return list;
        }

        /**
         * Variation on the sorting to add tasks to a queue, instead of recursing.
         */
        @Override
        void sort(int left, int right) {
            if(left < right) {
                int pi = partition(left, right);
                // Left partition
                ops.add(new int[] {left, pi - 1});
                // Right partition
                ops.add(new int[] {pi + 1, right});
            }
        }
    }
}
