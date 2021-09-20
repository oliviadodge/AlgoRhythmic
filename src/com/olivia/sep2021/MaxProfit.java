package com.olivia.sep2021;

class MaxProfit {

    public static void main(String[] args) {
        MaxProfit frog = new MaxProfit();

        int[] arr = {7,1,5,3,6,4};
        int[] arr2 = {7,6,4,3,1};
        System.out.println("output: " + frog.maxProfit(arr));
        System.out.println("output: " + frog.maxProfit(arr2));
    }

    public int maxProfit(int[] prices) {

        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;

        for (int price : prices) {
            if (price < minprice)
                minprice = price;
            else if (price - minprice > maxprofit)
                maxprofit = price - minprice;
        }
        return maxprofit;
    }
}
