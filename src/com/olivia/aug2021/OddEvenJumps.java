package com.olivia.aug2021;

import com.olivia.LinkedListNode;
import org.w3c.dom.Node;

import java.util.Arrays;

public class OddEvenJumps {

    public static void main(String[] args) {
        OddEvenJumps frog = new OddEvenJumps();
        int[] arr = {10,13,12,14,15};
        int[] arr2 = {2,3,1,1,4};
        int[] arr3 = {5,1,3,4,2};
        System.out.println("output: " + frog.oddEvenJumps(arr));
        System.out.println("output: " + frog.oddEvenJumps(arr2));
        System.out.println("output: " + frog.oddEvenJumps(arr3));
    }

    public int oddEvenJumps(int[] arr) {

        int numGoodStarts = 0;

        LinkedListNode[] pathsStartingWithOdd = new LinkedListNode[arr.length];
        LinkedListNode[] pathsStartingWithEven = new LinkedListNode[]{};

        for (int i = 0; i < arr.length; i++) {
            int currLilyPad = i;

            //Try to jump towards the end
            int jumpNum = 1;
            while(jumpNum != -1) {
                int nextLilyPad;
                if ((jumpNum % 2) == 1) { //odd jump
                    if ((pathsStartingWithOdd.length != 0) && (pathsStartingWithOdd[currLilyPad] != null)) {
                        numGoodStarts++;
                        jumpNum = -1;
                        continue;
                    }
                    nextLilyPad = getMinThatIsLargerThanCurrentVal(arr, currLilyPad);
                } else { //even jump
                    if ((pathsStartingWithEven.length != 0) && (pathsStartingWithEven[currLilyPad] != null)) {
                        numGoodStarts++;
                        jumpNum = -1;
                        continue;
                    }
                    nextLilyPad = getMaxThatIsLessThanCurrentVal(arr, currLilyPad);
                }
                if (currLilyPad == arr.length - 1) {
                    numGoodStarts++;
                    jumpNum = -1;
                } else if (nextLilyPad != -1) {
                    currLilyPad = nextLilyPad;
                    jumpNum++;
                } else {
                    jumpNum = -1;
                }
            }
        }
        return numGoodStarts;
    }

    private int getMaxThatIsLessThanCurrentVal(int[] arr, int currLilyPad) {
        int currVal = arr[currLilyPad];
        int nextLilyPad = -1;
        int lowerMax = -1;
        for (int j = currLilyPad + 1; j < arr.length; j++) {
            //find the max that is still less than or equal to currValue
            int nextVal = arr[j];
            if ((currVal >= nextVal) && ((nextVal > lowerMax) || (lowerMax == -1))) {
                lowerMax = nextVal;
                nextLilyPad = j;
            }
        }
        return nextLilyPad;
    }

    private int getMinThatIsLargerThanCurrentVal(int[] arr, int currLilyPad) {
        int currVal = arr[currLilyPad];
        int nextLilyPad = -1;
        int upperMin = -1;
        for (int j = currLilyPad + 1; j < arr.length; j++) {
            //find the min that is still larger or equal to currValue
            int nextVal = arr[j];
            if ((currVal <= nextVal) && ((nextVal < upperMin) || (upperMin == -1))) {
                upperMin = nextVal;
                nextLilyPad = j;
            }
        }
        return nextLilyPad;
    }
}
