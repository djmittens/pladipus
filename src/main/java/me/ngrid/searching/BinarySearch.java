package me.ngrid.searching;

import java.util.Arrays;
import java.util.List;

/**
 * Binary search operates over a sorted list of items and is by far the most efficient way one could search.
 * Theoretically lowest limit of searching is O(log n) and this is it.
 *
 * Best:    O(1)
 * Average: O(log n)
 * Worst:   O(log n)
 */
public class BinarySearch<T extends Comparable<T>>
        implements Searchable<T> {
    private List<T> list;

    private BinarySearch (List<T> list) {
        this.list = list;
    }
    @Override
    public int find(T e) {
        int low = 0;
        int high = list.size() - 1;
        //Search until the partition size is 0
        while(low <= high) {
            int mid = (low + high) / 2;
            T val = list.get(mid);

            // If the middle value is the one we are searching for return.
            if(e.equals(val))
                return mid;
            // If the value is less than the one we did find then go left
            else if (e.compareTo(val) < 0)
                high = mid - 1;
            // Else go right
            else
                low = mid + 1;
        }
        //we havent found anything
        return -1;
    }

    public static <E extends Comparable<E>> Searchable<E> getInstance(List<E> list) {
        return new BinarySearch<>(list);
    }

    public static <E extends Comparable<E>> Searchable<E> getInstance(E[] list) {
        return getInstance(Arrays.asList(list));
    }
}
