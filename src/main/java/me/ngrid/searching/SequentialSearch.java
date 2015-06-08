package me.ngrid.searching;

import java.util.Arrays;
import java.util.List;

/**
 * Brute force implementation of the searching algorithm.
 * Performed by sequentially iterating over a List .
 * Best:    O(1)
 * Average: O(n)
 * Worst:   O(n)
 *
 * This sort provides the worst performance possible because it has to look at every element
 * even in the average case.
 */
public class SequentialSearch <T extends Comparable<T>>
        implements Searchable<T> {
    List<T> list;

    private SequentialSearch (List<T> list)  {
        this.list = list;
    }
    @Override
    public int find(T e) {
        for(int i = 0; i < list.size(); i++) {
            if(e.equals(list.get(i))) {
                return i;
            }
        }
        return -1;
    }

    public static <E extends Comparable<E>> Searchable<E> getInstance(List<E> list) {
        return new SequentialSearch<>(list);
    }

    public static <E extends Comparable<E>> Searchable<E> getInstance(E[] list) {
        return getInstance(Arrays.asList(list));
    }
}
