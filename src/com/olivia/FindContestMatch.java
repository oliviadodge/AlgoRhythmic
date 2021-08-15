package com.olivia;

import java.util.Arrays;

public class FindContestMatch {

    public static void main(String[] args) {
        FindContestMatch matches = new FindContestMatch();
        System.out.println("number of ways is " + matches.findContestMatch(8));
    }

    public String findContestMatch(int n) {
        String[] matches = new String[n];
        for (int i = 0; i < matches.length; i++) {
            matches[i] = Integer.toString(i + 1);
        }
        return findContestMatchStringArray(matches)[0];
    }

    private String[] findContestMatchStringArray(String[] matches) {
        if (matches.length == 2) {
            return new String[]{"(" + matches[0] + "," + matches[1] + ")"};
        }
        String[] nextLevelMatches = new String[matches.length / 2];
        for (int i = 0; i < nextLevelMatches.length; i++) {
            String strongerTeam = matches[i];
            String weakerTeam = matches[matches.length - 1 - i];
            nextLevelMatches[i] = "(" + strongerTeam + "," + weakerTeam + ")";
        }
        return findContestMatchStringArray(nextLevelMatches);
    }
}
