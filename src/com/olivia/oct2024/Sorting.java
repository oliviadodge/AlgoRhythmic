package com.olivia.oct2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    static Integer can_attend_all_meetings(ArrayList<ArrayList<Integer>> intervals) {
        // first sort the intervals

        int pointer1 = 0, pointer2 = 0, pointer3 = 0;
        int max = Math.max(pointer2, pointer1);
        Collections.sort(intervals, (m1, m2) -> m1.get(0) - m2.get(0));
        return 0;
    }

    public static void bucketSort(ArrayList<Integer> arr, int div) {

        // Create an array of empty buckets
        List<List<Integer>> buckets = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribute the elements into the buckets based on the specified digit
        for (int i = 0; i < arr.size(); i++) {
            int bucketIndex = (arr.get(i) / div) % 10;
            buckets.get(bucketIndex).add(arr.get(i));
        }

        // Merge the elements from all the buckets back into the original array
        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < buckets.get(i).size(); j++) {
                arr.set(index++, buckets.get(i).get(j));
            }
        }
    }

    public static ArrayList<Integer> radix_sort(ArrayList<Integer> arr) {
        int n = arr.size();

        int maxElement = arr.get(0);
        for (int i = 1; i < n; i++) {
            maxElement = Math.max(maxElement, arr.get(i));
        }

        int div = 1;
        // Perform bucket sort for each digit, starting from the least significant digit
        while (true) {
            bucketSort(arr, div);

            // If the current digit is the Most Significant Digit of 'maxElement',
            // it means that the complete array has been sorted.
            if ((maxElement / div) / 10 == 0) {
                break;
            }

            div *= 10;
        }

        return arr;
    }

        /*
    Asymptotic complexity in terms of the length of input list `n`:
    * Time: O(n^3).
    * Auxiliary space: O(n^3).
    * Total space: O(n^4).
    */

    static ArrayList<ArrayList<Integer>> four_sum(ArrayList<Integer> arr, Integer target) {
        Collections.sort(arr);
        return k_sum(arr, target, 0, 4);
    }


    // This function will return all the distinct sets of size `k` in `arr[start ... arr.size() - 1]`
    // with sum of the values equal to the `current_target`.
    static ArrayList<ArrayList<Integer>> k_sum(ArrayList<Integer> arr, Integer current_target, Integer start, Integer k) {
        // Taking care of the edge cases
        // There's not even enough elements in the array to form a set that adds up to the target
        // After sorting, k of the first elements would be greater than target, meaning no solution
        // After sorting, k of the last element is less than the target, meaning no solution
        if (start + k > arr.size() || arr.get(start) * k > current_target || arr.get(arr.size() - 1) * k < current_target) {
            return new ArrayList<>();
        }

        if (k == 2) {
            return two_sum(arr, current_target, start);
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = start; i < arr.size(); i++) {
            // If we're at the start OR this one and the previous one are NOT equal
            if (i == start || !arr.get(i).equals(arr.get(i - 1))) {
                // Call this function again, with the needed value, i.e target - currentElement, starting at the next element, with k - 1
                ArrayList<ArrayList<Integer>> sub_result = k_sum(arr, current_target - arr.get(i), i + 1, k - 1);

                // For each set in the sub result, add the current element
                // Add that set to the final results list
                for (ArrayList<Integer> current : sub_result) {
                    current.add(arr.get(i));
                    result.add(current);
                }
            }
        }

        return result;
    }

    // This function will return all the distinct pairs in arr[start ... arr.size() - 1]
    // with sum of the values equal to the current_target.
    static ArrayList<ArrayList<Integer>> two_sum(ArrayList<Integer> arr, Integer current_target, Integer start) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        int low = start, high = arr.size() - 1;
        while (low < high) {
            if (arr.get(low) + arr.get(high) == current_target) {
                result.add(new ArrayList<>(Arrays.asList(arr.get(low), arr.get(high))));
                low++;
                high--;
            } else if (arr.get(low) + arr.get(high) < current_target) {
                low++;
            } else {
                high--;
            }

            if (low > start) {
                while (low <= high && arr.get(low).equals(arr.get(low - 1))) {
                    low++;
                }
            }

            if (high < arr.size() - 1) {
                while (low <= high && arr.get(high).equals(arr.get(high + 1))) {
                    high--;
                }
            }
        }

        return result;
    }


    public static boolean testArrayList() {
        ArrayList<Integer> ints = new ArrayList<>();

        ints.add(5);
        ints.add(3);
        ints.add(2);

        ints.remove(1);

        return ints.get(0) == 5 && ints.get(1) == 2;
    }
}
