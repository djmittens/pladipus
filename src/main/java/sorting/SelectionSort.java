package sorting;

/**
 */
public class SelectionSort<T extends Comparable<T>> implements Sort<T> {
    private boolean sorted;

    public static <E extends Comparable<E>> Sort<E> getInstance(E[] list) {
        return new SelectionSort<>(list);
    }

    private T[] list;
    private SelectionSort(T[] list) {
        this.list = list;
        this.sorted = false;
    }

    public T[] sort() {
        if(!sorted)
            for(int i = 1; i < list.length; i ++) {
                insert(i, list[i]);
            }
        sorted = true;
        return list;
    }

    void insert(int pos, T value) {
        int i  = pos - 1;
        for( ;i >= 0 && list[i].compareTo(value) > 0; i--) {
            list[i + 1] = list[i];
        }
        list[i + 1] = value;
    }
}
