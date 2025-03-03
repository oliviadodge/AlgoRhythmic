package com.olivia;

import java.util.ArrayList;
import java.util.Collections;

public class Sorting {


    /*
    Asymptotic complexity in terms of length of the input array ( = `n`):
    * Time: O(n * log(n)).
    * Auxiliary space: O(n).
    * Total space: O(n).
    */

    static long merge(ArrayList<Integer> nums, int left, int mid, int right) {
        // `i` denotes the index for left subarray.
        // `j` denotes the index for right subarray.
        // `k` denotes the index for resultant merged subarray.
        int i = left, j = mid + 1, k = 0;
        long inversion_count = 0;
        ArrayList<Integer> temporary_list = new ArrayList<>(Collections.nCopies(right - left + 1, 0));

        while ((i <= mid) && (j <= right)) {
            if (nums.get(i) <= nums.get(j)) {
                temporary_list.set(k++, nums.get(i++));
            }
            else {
                temporary_list.set(k++, nums.get(j++));
                inversion_count += (mid + 1 - i);
            }
        }

        while (i <= mid) {
            temporary_list.set(k++, nums.get(i++));
        }

        while (j <= right) {
            temporary_list.set(k++, nums.get(j++));
        }

        for (i = left, k = 0; i <= right; i++, k++) {
            nums.set(i, temporary_list.get(k));
        }

        return inversion_count;
    }

    static long merge_sort(ArrayList<Integer> nums, int left, int right) {
        long inversion_count = 0;

        if (left < right) {
            int mid = left + (right - left) / 2;
            // The total number of inversions will be the sum of inversions in the left subarray, the right
            // subarray and the inversions found while merging the two subarrays.
            inversion_count = merge_sort(nums, left, mid);
            inversion_count += merge_sort(nums, mid + 1, right);
            inversion_count += merge(nums, left, mid, right);
        }

        return inversion_count;
    }

    static long count_inversions(ArrayList<Integer> nums) {
        return merge_sort(nums, 0, nums.size() - 1);
    }

}
