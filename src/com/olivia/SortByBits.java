package com.olivia;

public class SortByBits {

    public static void main(String[] args) {
        SortByBits sortByBits = new SortByBits();
        int[] array = new int[]{4, 1, 77, 81, 3, 22};
        System.out.println(sortByBits.sortByBits(array));
    }

    public int[] sortByBits(int[] arr) {
        //1. iterate through the array
        //2. at each integer, convert to binary
        //3. count the 1's in the binary
        //4. if the number of 1's is less than the one before it, swap it
        //5. If the number of 1's is greater than the one before it, do nothing,
        //6. if the number of 1's is equal to the one before it, check the integer value, and swap if the one after is less than the one before

        for (int i = 0; i < arr.length; i++) {

        }
        return arr;
    }
}
