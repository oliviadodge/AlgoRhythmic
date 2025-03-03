package com.olivia;

import static com.olivia.oct2024.Sorting.testArrayList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.olivia.dynamic.DynamicProgramming;
import com.olivia.nov2024.Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AlgoTests {

    @Test
    public void findTheWinner() {
        Recursion.get_kth_permutation(4, 5);
    }
    @Test
    public void testWordWrap() {
        ArrayList<String> words = new ArrayList<>(Arrays.asList("hi", "my", "name", "is", "olivia"));
        com.olivia.dynamic.DynamicProgramming.solve_balanced_line_breaks_DP(words, 6);

        // 0-0 hi
        // 0-1 hi / hi my
        // 0-2 hi / hi my / hi my name

        // 0-0 hi
        // 0-1 my + hi / my hi
    }
    @Test
    public void testing() {
        List<List<Integer>> recurse = new ArrayList<>();
        Recursion.recursionExperiment(0, recurse);

        //System.out.println("Final Results: " + recurse);
//        Final Results: [0, 0]
//          Final Results: [1, 0]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//          Final Results: [1, 1]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//          Final Results: [1, 2]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//        Final Results: [0, 1]
//          Final Results: [1, 0]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//          Final Results: [1, 1]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//          Final Results: [1, 2]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//       Final Results: [0, 2]
//          Final Results: [1, 0]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//          Final Results: [1, 1]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//          Final Results: [1, 2]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]
//
//        Final Results: [0, 0]
//        Final Results: [0, 1]
//        Final Results: [0, 2]
//          Final Results: [1, 0]
//          Final Results: [1, 1]
//          Final Results: [1, 2]
//              Final Results: [2, 0]
//              Final Results: [2, 1]
//              Final Results: [2, 2]


        //Final Results: [0, 0]
            //Final Results: [1, 1]
                //Final Results: [2, 2]
            //Final Results: [1, 2]
        //Final Results: [0, 1]
                //Final Results: [2, 2]
        //Final Results: [0, 2]
    }

    @Test
    public void test() {
        assertTrue(testArrayList());
    }
}