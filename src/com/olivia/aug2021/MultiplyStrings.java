package com.olivia.aug2021;

public class MultiplyStrings {
    public static void main(String[] args) {

        MultiplyStrings calculator = new MultiplyStrings();

//        System.out.println("output: " + calculator.multiply("2", "3"));
        System.out.println("output: " + calculator.multiply("123", "456"));
    }


    public String multiply(String num1, String num2) {
        long res = 0;

        for (int i = num1.length() - 1; i >= 0; i--) {
            int carryover = 0;

            for (int j = num2.length() - 1; j >= 0; j--) {
                int digit1 = Character.getNumericValue(num1.charAt(i));
                int digit2 = Character.getNumericValue(num2.charAt(j));

                int product = digit1 * digit2 + carryover;

                int mod;
                if (j == 0) {
                    mod = product;
                    carryover = 0;
                } else {
                    mod = product % 10;
                    carryover = product / 10;
                }

                int powerOf10 = (num1.length() - 1 - i) + (num2.length() - 1 - j);
                long toAdd = (long) mod * (long)Math.pow(10, powerOf10);

                res = res + toAdd;

            }
        }
        return Long.toString(res);
    }
}
