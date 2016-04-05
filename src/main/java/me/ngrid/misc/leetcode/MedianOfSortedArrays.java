package me.ngrid.misc.leetcode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 */
public class MedianOfSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length - 1, n0 = 0;
        int m = nums2.length - 1, m0 = 0;



        do {
            // Iff any of the arrays is empty clearly the median is in the other array.
            double med1 = getMedian(nums1, n0, n), med2 = getMedian(nums2, m0, m);
            if (n < 0)
                return med2;
            if (m < 0)
                return med1;

            // Iff the median values line up we got it, thats it.
            if (med1 == med2)
                return med1;

            if(med1 > med2) {
                //shift right
                m0 += (m - m0) / 2;
                // shift left
                n = n0 + ((n - n0) / 2);
            }
            else {
                n0 += (n - n0) / 2;
                m = m0 + ((m - m0) / 2);
            }

            // Now lets find a range we have to search
        } while(n - n0 > 2 || m - m0 > 2);

        return (Math.max(nums1[n0], nums2[m0]) + Math.min(nums1[n], nums2[m])) / 2d;
    }

    /**
     *  Find the median between the two points in an array.
     *  right inclusive
     *  length = left + right + 1
     */
    private double getMedian(int[] a, int left, int right) {
        int l = right - left + 1;
        if(l < 1)
            return -1d;
        /// Iff the length odd
        if((l & 1) > 0) {
           return a[left + (l / 2)];
        }
        else {
            return (a[left + (l / 2)] + a[left + (l/2 - 1)]) / 2d;
        }
    }
}
