package me.ngrid.sorting;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

/**
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

    protected final void swap(int left, int right) {
        T t = list[left];
        list[left] = list[right];
        list[right] = t;
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
