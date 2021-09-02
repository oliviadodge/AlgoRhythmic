package com.olivia.aug2021;

import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum thing = new ThreeSum();
        int[] nums = new int[]{0, 0, 0, 0};
        System.out.println("num unique emails: " + thing.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        char c = 'l';

        List<List<Integer>> triplets = new ArrayList<>();
        Set<List<Integer>> trip = new HashSet<>();


        for (int i = 0; i < nums.length - 2; i++) {
            int lo = i + 1;
            int hi = nums.length - 1;

            while (hi > lo) {
                int sum = nums[hi] + nums[lo] + nums[i];
                if (sum == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[hi]);
                    triplet.add(nums[lo]);
                    triplet.add(nums[i]);
                    if (!trip.contains(triplet)) {
                        triplets.add(triplet);
                        trip.add(triplet);
                    }
                    break;
                } else if (sum > 0) {
                    hi--;
                } else {
                    lo++;
                }
            }
        }
        return triplets;
    }
}
