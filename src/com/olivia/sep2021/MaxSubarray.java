package com.olivia.sep2021;

public class MaxSubarray {

    public static void main(String[] args) {
        MaxSubarray thing = new MaxSubarray();

        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        int[] arr2 = {5,4,-1,7,8};
        System.out.println("output: " + thing.maxSubArray(arr));
        System.out.println("output: " + thing.maxSubArray(arr2));
    }

    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int runningSum = maxSum;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] < 0) {


                if (runningSum + nums[i] > 0) {
                    runningSum += nums[i];
                } else {
                    runningSum = nums[i];
                }
            } else if (nums[i] >= 0) {
                if (runningSum < 0) {
                    runningSum = nums[i];
                } else {
                    runningSum += nums[i];
                }
            }

            if (runningSum > maxSum) {
                maxSum = runningSum;
            }
        }

        return maxSum;
    }
}
