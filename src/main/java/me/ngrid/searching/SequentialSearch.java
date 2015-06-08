package me.ngrid.searching;

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

    public SequentialSearch (List<T> list)  {
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
}
