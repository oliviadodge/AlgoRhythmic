package com.olivia;

public class DecodeString {

    public static void main(String[] args) {
        DecodeString stringDecoder = new DecodeString();
        String input = "3[a]2[bc]";
        String output = "aaabcbc";
        System.out.println("for input " + input + " the output should be: " + output + " actual output is " + stringDecoder.decodeString(input));
    }

    public String decodeString(String s) {
        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);

            if (Character.isDigit(c1)) {
                StringBuilder timesBuilder = new StringBuilder();

                int openBracketIdx = -1;
                for (int k = i; k < s.length(); k++) {
                    char c2 = s.charAt(k);
                    if (c2 != '[') {
                        timesBuilder.append(c2);
                    } else {
                        openBracketIdx = k;
                        break;
                    }
                }
                int times = Integer.parseInt(timesBuilder.toString());

                int closedBracketIdx = getClosedBracketIdx(openBracketIdx, s);
                String inBrackets = decodeString(getStringInBrackets(openBracketIdx, closedBracketIdx, s));
                sbuilder.append(String.valueOf(inBrackets).repeat(Math.max(0, times)));
                i = closedBracketIdx;

            } else {
                sbuilder.append(c1);
            }
        }

        return sbuilder.toString();

    }

    private int getClosedBracketIdx(int openBracketIdx, String s) {
        int additionalOpenBrackets = 0;
        for (int i = openBracketIdx + 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                additionalOpenBrackets++;
            } else if ((c == ']') && additionalOpenBrackets == 0) {
                return i;
            } else if (c == ']') {
                additionalOpenBrackets--;
            }
        }
        return -1;
    }

    private String getStringInBrackets(int openBracketIdx, int closedBracketIdx, String s) {
        return s.substring(openBracketIdx + 1, closedBracketIdx);
    }
}
