package com.olivia.sep2021;

import com.olivia.aug2021.OddEvenJumps;

import java.util.Stack;

class JumpGame {

    public static void main(String[] args) {
        JumpGame frog = new JumpGame();

        int[] arr = {2,3,1,1,4};
        int[] arr2 = {3,2,1,0,4};
        int[] arr3 = {5,1,3,4,2};

        System.out.println("output: " + frog.canJump(arr));
        System.out.println("output: " + frog.canJump(arr2));
        System.out.println("output: " + frog.canJump(arr3));
    }

    public boolean canJump(int[] nums) {
        int currentPos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            int distToGo = currentPos - i;

            if (nums[i] >= distToGo) {
                currentPos = i;
            }
        }
        return (currentPos == 0);
    }
}
