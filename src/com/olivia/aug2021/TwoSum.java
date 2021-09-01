package com.olivia.aug2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        TwoSum thing = new TwoSum();
        int[] nums = new int[]{2,7,11,15};
        System.out.println("num unique emails: " + Arrays.toString(thing.twoSum(nums, 9)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{ map.get(complement), i};
            }

            map.put(nums[i], i);
        }
        return null;
    }
}
