package com.olivia.sep2021;

public class IsAnagram {

    public static void main(String[] args) {
        IsAnagram isAnagram = new IsAnagram();

        System.out.println("car and rat: " + isAnagram.isAnagram("car", "rat"));
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] chars = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);

            chars[sChar - 'a'] = chars[sChar - 'a'] + 1;


            char tChar = t.charAt(i);

            chars[tChar - 'a'] = chars[tChar - 'a'] - 1;
        }


        for (int aChar : chars) {
            if (aChar != 0) return false;
        }
        return true;
    }
}
