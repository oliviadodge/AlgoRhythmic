package com.olivia;

public class Tribonacci {
    public static void main(String[] args) {
        System.out.println("answer is " + tribonacci(3));
    }

    public static int tribonacci(int n) {
        return tribonacciMem(n, new int[n]);
    }

    //memoized
    private static int tribonacciMem(int n, int[] mem){
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            if (mem[n] != 0) {
                return mem[n];
            }
            mem[n] = tribonacciMem(n - 1, mem) + tribonacciMem(n - 2, mem) + tribonacciMem(n - 3, mem);
            return mem[n];
        }
    }

    //iterative
    private static int tribonacciIt(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            int a = 0;
            int b = 1;
            int c = 1;
            int d = a + b + c;
            for (int i = 3; i <= n; i++) {
                d = a + b + c;
                a = b;
                b = c;
                c = d;
            }
            return d;
        }
    }
}
