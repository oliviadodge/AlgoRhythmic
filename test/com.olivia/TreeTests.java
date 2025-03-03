package com.olivia;

import static com.olivia.oct2024.Sorting.testArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.olivia.nov2024.Trees;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class TreeTests {

    @Test
    public void testing() {
        ArrayList<Integer> values = new ArrayList<>();
        values.add(6);
        values.add(5);
        values.add(4);
        values.add(3);
        values.add(2);
        values.add(1);

        Trees.build_a_bst(values);
    }

    @Test
    public void test() {
        assertTrue(testArrayList());
    }
}