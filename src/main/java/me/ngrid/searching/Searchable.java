package me.ngrid.searching;

/**
 * Represents a search.
 */
public interface Searchable<T extends Comparable<T>> {
    /**
     * Return the position inside of the collection that hosts this item.
     * @param e element to find.
     * @return [index | -1] iff the element was found, else returns -1.
     */
    int find(T e);
}
