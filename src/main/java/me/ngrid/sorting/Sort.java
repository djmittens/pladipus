package me.ngrid.sorting;

/**
 * This interface specifies that a given collections is sortable.
 */
public interface Sort <T extends Comparable<T>> {
    /**
     * Perform the sort.
     *
     * @return sorted array
     */
    T[] sort();
}
