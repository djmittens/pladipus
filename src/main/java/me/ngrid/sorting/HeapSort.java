package me.ngrid.sorting;
import static me.ngrid.util.Array.swap;

/**
 * Default HeapSort implementation using recursion.
 * This algorithm is not stable and will in fact yield average case performance slightly worse than quicksort
 * for smaller lists, however it will quickly gain advantage for particularly crazy cases that will make quicksort
 * behave shitty.
 *
 * Worst:   O(n log(n))
 * Best:    O(n log(n))
 * Average: O(n log(n))
 *
 * @param <T>
 */
public class HeapSort<T extends Comparable<T>> implements Sort<T> {

    public static <E extends Comparable<E>> Sort<E> getInstance(E[] list) {
        return new HeapSort<>(list);
    }

    private T[] list;

    private HeapSort(T[] list) {
        this.list = list;
    }

    @Override
    public T[] sort() {
        // Build our initial heap, by finding the root node
        // The largest element will bubble up to index 0.
        for(int i = list.length / 2 - 1; i >= 0; i --) {
            heapify(i, list.length);
        }
        // Build the complete heap
       for(int i = list.length - 1; i > 0; i--)  {
           // Store this root for safe keeping.
           // It is guranteed that everything above i is larger than it.
           // and everything below is smaller.
           swap(list, 0, i);
           heapify(0, i);
       }
        return list;
    }


    /**
     * Bubbles the largest element to the front of the list.
     * @param root the root of the heap to be built.
     * @param max maximum size of the array in order to not overflow.
     */
    private void heapify(int root, int max) {
        int left = 2 * root + 1;
        int right = 2 * root + 2;
        int largest = root;

        if(left < max && list[left].compareTo(list[largest]) > 0) {
            largest = left;
        }

        if (right < max && list[right].compareTo(list[largest]) > 0) {
            largest = right;
        }

        // If we found that the root was not the largest, we have to make sure that after swapping,
        // it remains the largest;
        if(largest != root) {
            swap(list, root, largest);
            heapify(largest, max);
        }
    }
}
