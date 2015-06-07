package sorting;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Random;

/**
 */
public abstract class  Quicksort <T extends Comparable<T>> {

    /**
     * Uses the best implementation of Quicksort to sort the given list
     * @param list List of given objects to sort with some stuff.
     * @param <E> Object type to sort
     */
    public static <E extends Comparable<E>> void sort(E[] list) {
        new RandomIterative<E>(list).sort();
    }

    protected T[] list;

    Quicksort(T[] list) {
        this.list = list;
    }

    T[] sort() {
//        System.out.println("Sorting " + Arrays.toString(list));
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
//        System.out.println("Pivot " + list[pivot]);
        // Value in the store is guranteed to be < pivot
        int store = left;
        for(int i = left; i < right; i ++) {
            // Comparing each value to the pivot to see if they are greater.
            if(list[i].compareTo(list[right]) <= 0  ) {
                swap(store, i);
                store ++;
            }
        }
        // In the end store points to last position in the subarray that is sorted.
        swap(store, right);
//        System.out.println(Arrays.toString(list));
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

    public static class RandomPivot<T extends Comparable<T>> extends Quicksort<T>{

        private Random random;

        RandomPivot(T[] list) {
            super(list);
            this.random = new Random();
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
    public static class NaivePivot<T extends Comparable<T>> extends Quicksort<T>{

        NaivePivot(T[] list) {
            super(list);
        }

        protected  int pickPivot(int left, int right) {
            return right;
        }
    }

    public static class RandomIterative<T extends Comparable<T>> extends RandomPivot<T> {

        Deque<int[]> ops;


        RandomIterative(T[] list) {
            super(list);
            ops = new ArrayDeque<>();
        }

        @Override
        T[] sort() {
//            System.out.println("Sorting: " + Arrays.toString(list));
            ops.add(new int[] {0, list.length - 1} );
            while(!ops.isEmpty()) {
                int [] c = ops.pop();
                sort(c[0], c[1]);
            }
            return list;
        }

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
