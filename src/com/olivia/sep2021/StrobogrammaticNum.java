package com.olivia.sep2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammaticNum {

    static int N = -1;
    public static void main(String[] args) {

        StrobogrammaticNum frog = new StrobogrammaticNum();

        N = 5;
        System.out.println("output: " + frog.findStrobogrammatic(5).toString());
        N = 3;
        System.out.println("output: " + frog.findStrobogrammatic(3).toString());
    }

    public List<String> findStrobogrammatic(int n) {
        //0, 1, 8 need to be palindromes to be strobogrammatic
        //6 and 9 needd to be in an even number and counteract each other so that for each position where there is a 6, there needs to be a 9 at (length - 1) - position

        //for 0, 1, and 8
        //halfPali = divide n by 2. for 0 to halfPali

        if (n == 1) {
            List<String> strobos = new ArrayList<>();
            strobos.add("0");
            strobos.add("1");
            strobos.add("8");
            return strobos;
        } else {
            List<String> list = findStrobogrammatic(n - 1);
            List<String> strobos = new ArrayList<>();

            for (String num : list) {
                if (n < N) strobos.add("0" + num);
                strobos.add("1" + num);
                strobos.add("8" + num);
            }
            return strobos;
        }
    }
}
