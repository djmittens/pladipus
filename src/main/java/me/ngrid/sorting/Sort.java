package me.ngrid.sorting;

/**
 * This interface specifies that a given collections is sortable.
 */
public interface Sort <T extends Comparable<T>> {
    /**
     * Perform the sorting operation.
     *
     * @return sorted array
     */
    T[] sort();
}
