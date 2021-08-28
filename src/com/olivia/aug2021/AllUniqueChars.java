package com.olivia.aug2021;

import java.util.ArrayList;
import java.util.Arrays;

public class AllUniqueChars {
    public static void main(String[] args) {
        System.out.println("hello: " + hasAllUniqueCharacters("hello"));
        System.out.println("world: " + hasAllUniqueCharacters("world"));
    }

    public static boolean hasAllUniqueCharacters(String s) {
        boolean[] asciiChars = new boolean[128];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (asciiChars[c]) {
                System.out.println("This is a letter we've seen before: " + c);
                System.out.println("We have it in our databank at position " + (int)c);
                return false;
            } else {
                asciiChars[c] = true;
                System.out.println("We've not seen this letter before: " + c);
                System.out.println("Adding to the databank at position: " + (int)c);
            }
        }
        return true;
    }
}
