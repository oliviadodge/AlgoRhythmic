package com.olivia.sep2021;

import com.olivia.TreeNode;
import com.olivia.TrieNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSquares {

    public static void main(String[] args) {
        WordSquares thing = new WordSquares();

        String[] arr = {"area","lead","wall","lady","ball"};
        String[] arr2 = {"abat","baba","atan","atal"};

        System.out.println("output: " + thing.wordSquares(arr));
        System.out.println("output: " + thing.wordSquares(arr2));
    }

    public List<List<String>> wordSquares(String[] words) {

        //pre-processing
        Map<Character, TrieNode> map = createTrieMap(words);

        for (String word : words) {
            String needed = "d";
        }


        return null;
    }

    private Map<Character, TrieNode> createTrieMap(String[] words) {
        HashMap<Character, TrieNode> map = new HashMap<>();

        return map;
    }
}
