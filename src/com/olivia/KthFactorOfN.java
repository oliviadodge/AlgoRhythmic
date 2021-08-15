package com.olivia;

import java.util.ArrayList;
import java.util.List;

public class KthFactorOfN {

    public static void main(String[] args) {
        KthFactorOfN kthFactorOfN = new KthFactorOfN();
        System.out.println("answer is " + kthFactorOfN.kthFactor(4, 2));
    }

    public int kthFactor(int n, int k) {
        List<Integer> factors = new ArrayList<>();
        int upperLimit = n;
        int lowerLimit = 1;
        while (lowerLimit <= upperLimit) {
            if (n % lowerLimit == 0) {
                upperLimit = n / lowerLimit;
                if (lowerLimit <= upperLimit) {
                    factors.add(lowerLimit);
                    factors.add(upperLimit);
                }
                lowerLimit++;
            }
        }
        if (k * 2 < factors.size()) {
            return factors.get(k * 2);
        } else {
            int decrement = (k * 2) - factors.size() + 1;
            return factors.get(decrement);
        }
    }
}
