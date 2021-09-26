package com.olivia.sep2021;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneNumberLetterCombo {

    static int N = -1;
    public static void main(String[] args) {

        PhoneNumberLetterCombo thing = new PhoneNumberLetterCombo();

        N = 5;
        System.out.println("output: " + thing.letterCombinations("23").toString());
        N = 3;
        System.out.println("output: " + thing.letterCombinations("2").toString());
    }

    private Map<Character, String> charsForDigit = Map.of(
            '2', "abc",
            '3', "def",
            '4', "ghi",
            '5', "jkl",
            '6', "mno",
            '7', "pqrs",
            '8', "tuv",
            '9', "wxyz"
    );

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return new ArrayList<>();

        if (digits.length() == 1) {
            List<String> letterCombos = new ArrayList<>();

            String choices = charsForDigit.get(digits.charAt(0));

            for (int i = 0; i < choices.length(); i++) {
                letterCombos.add("" + choices.charAt(i));
            }
            return letterCombos;
        } else {
            List<String> prevCombos = letterCombinations(digits.substring(1));

            List<String> newCombos = new ArrayList<>();

            String choices = charsForDigit.get(digits.charAt(0));

            for (int i = 0; i < choices.length(); i++) {
                for (String word : prevCombos) {
                    newCombos.add(choices.charAt(i) + word);
                }
            }
            return newCombos;
        }
    }
}
