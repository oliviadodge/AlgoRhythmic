package com.olivia.sep2021;

public class MedianOf2SortedArrays {

    public static void main(String[] args) {
        MedianOf2SortedArrays thing = new MedianOf2SortedArrays();
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{1, 2};
        System.out.println("median: " + thing.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];
        if (nums1.length == 0 && nums2.length == 0) return 0d;
        else if (nums1.length == 0) {
            result = nums2;
        } else if (nums2.length == 0) {
            result = nums1;
        } else {
            merge(nums1, nums2, result, 0, 0, 0);
        }
        int mid = result.length / 2;
        if (result.length % 2 == 0) {
            return (result[mid - 1] + result[mid]) / 2d;
        } else {
            return result[mid];
        }
    }

    private void merge(int[] a1, int[] a2, int[] result, int i1, int i2, int r) {
        if (a1[i1] <= a2[i2]) {
            result[r] = a1[i1];
            i1++;
        } else {
            result[r] = a2[i2];
            i2++;
        }
        r++;
        if (i1 == a1.length) {
            for (int i = i2; i < a2.length; i++) {
                result[r++] = a2[i];
            }
        } else if (i2 == a2.length) {
            for (int i = i1; i < a1.length; i++) {
                result[r++] = a1[i];
            }
        } else {
            merge(a1, a2, result, i1, i2, r);
        }
    }
}
