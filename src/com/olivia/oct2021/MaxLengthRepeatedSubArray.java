package com.olivia.oct2021;

public class MaxLengthRepeatedSubArray {

    public static void main(String[] args) {
        MaxLengthRepeatedSubArray object = new MaxLengthRepeatedSubArray();

        System.out.println("answer: " + object.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}));
    }

    public int findLength(int[] nums1, int[] nums2) {
        int answer = 0;

        int[][] memo = new int[nums1.length + 1][nums2.length + 1];

        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    memo[i][j] = memo[i + 1][j + 1] + 1;
                    if (answer < memo[i][j]) answer = memo[i][j];
                }
            }
        }

        return answer;
    }
}

// i = 4, j = 4 nothing happens
//i = 4, j = 2 --> memo[4][2] = memo[5][3] + 1 = 1 --> answer = 1
//i = 3, j = 1 --> memo[3][1] = memo[4][2] + 1 = 2 --> answer = 2
//i = 2, j = 0 --> memo[2][0] = memo[3][1] + 1 = 3 --> answer = 3
//i = 1, j = 1 --> memo[1][1] = memo[2][2] + 1 = 1
//i = 0, j = 2 --> memo[0][2] = memo[1][3] + 1 = 1
