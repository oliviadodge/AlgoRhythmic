package com.olivia;

public class Main {

    public static void main(String[] args) {
        System.out.println("number of ways is " + numWaysBottomUpDynamic(4));
    }

    public static int numWays(int steps) {
        return numWays(steps, new int[steps + 1]);
    }

    private static int numWays(int steps, int[] memo) {
        if (steps < 0) return 0;
        if (steps == 0) return 1;
        if (memo[steps] == 0)
            memo[steps] = numWays(steps - 1, memo) + numWays(steps - 2, memo) + numWays(steps - 3, memo);
        return memo[steps];
    }

    private static int numWaysBottomUpDynamic(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        int a = 1;
        int b = 1;
        int c = 2;
        for (int i = 3; i < n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return a + b + c;
    }
}
