package com.olivia.oct2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IntersectionOfTwoArrays {

    public static void main(String[] args) {
        IntersectionOfTwoArrays object = new IntersectionOfTwoArrays();

        System.out.println("answer: " + object.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}));
    }

    public int[] intersect(int[] nums1, int[] nums2) {

        HashSet<Integer> list1 = new HashSet<>();
        HashSet<Integer> list2 = new HashSet<>();
        List<Integer> intersection = new ArrayList<>();

        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {

            int num1 = nums1[i];
            int num2 = nums2[i];

            if (num1 == num2) {
                intersection.add(num1);
            } else if (list2.contains(num1) || list1.contains(num2)) {
                if (list2.contains(num1)) {
                    list2.remove(num1);
                    intersection.add(num1);
                }
                if (list1.contains(num2)) {
                    list1.remove(num2);
                    intersection.add(num2);
                }
            } else {
                list1.add(num1);
                list2.add(num2);
            }
            i += 1;
            j += 1;
        }

        while (i < nums1.length && !list2.isEmpty()) {
            int num1 = nums1[i];

            if (list2.contains(num1)) {
                list2.remove(num1);
                intersection.add(num1);
            }
            i += 1;
        }

        while (j < nums2.length && !list1.isEmpty()) {
            int num2 = nums2[j];

            if (list1.contains(num2)) {
                list1.remove(num2);
                intersection.add(num2);
            }
            j += 1;
        }

        int[] result = new int[intersection.size()];
        for(int k = 0; k < result.length; k++) result[k] = intersection.get(k);
        return result;
    }
}