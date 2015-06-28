package me.ngrid.misc.leetcode;

import java.util.Arrays;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * @see <a heft="https://leetcode.com/problems/kth-largest-element-in-an-array/">l33tC0d3</a>
 * @see me.ngrid.sorting.HeapSort
 */
public class KthLargest {
    /**
     * My strategy is to build a max heap.
     * Then perform heap sort until we got k top elements.
     */
    public int findKthLargest(int[] nums, int k) {
        nums = Arrays.copyOf(nums, nums.length);
        if(k > nums.length) {
            return -1;
        }

        // Initially build a max heap
        for(int i = nums.length / 2 - 1; i >= 0; i --)  {
            heapify(nums, i, nums.length);
        }

        for(int i = nums.length - 1; i > nums.length - k; i --) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
        return nums[0];
    }

    private static void swap(int[] arr, int i, int k) {
        int tmp = arr[i];
        arr[i] = arr[k];
        arr[k] = tmp;
    }

    private static void heapify(int[] nums, int root,  int max) {
        int largest = root;
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if(left < max && nums[largest] < nums[left]) {
            largest = left;
        }

        if(right < max && nums[largest] < nums[right]) {
            largest = right;
        }

        if(largest != root) {
            swap(nums, root, largest);
            // Well clearly this guy was the largest, ensure the shape of its children.
            heapify(nums, largest, max);
        }
    }
}
