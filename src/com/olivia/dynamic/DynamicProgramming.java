package com.olivia.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {

        /*
    Asymptotic complexity in terms of the size of the grid `n` * `m`:
    * Time: O(n * m).
    * Auxiliary space: O(n * m).
    * Total space: O(n * m).
    */

    final static int mod = 1000000007;

    static int unique_paths(int n, int m) {
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] += dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] += dp[i - 1][j];
                } else {
                    dp[i][j] += dp[i][j - 1] + dp[i - 1][j];
                }
                dp[i][j] %= mod;
            }
        }
        return dp[n - 1][m - 1];
    }

        /*
    Asymptotic complexity in terms of the size of the grid `n` * `m`:
    * Time: O(n * m).
    * Auxiliary space: O(n * m).
    * Total space: O(n * m).
    */

    static int get_number_of_paths(int n, int m, int[][] dp) {
        if (dp[n][m] != -1) return dp[n][m];
        int result;
        if (n == 0 && m == 0) {
            result = 1;
        } else if (n == 0) {
            result = get_number_of_paths(n, m - 1, dp);
        } else if (m == 0) {
            result = get_number_of_paths(n - 1, m, dp);
        } else {
            result = (get_number_of_paths(n, m - 1, dp) % mod + get_number_of_paths(n - 1, m, dp) % mod) % mod;
        }
        return dp[n][m] = (result % mod);
    }

    private static int uniquePathsRecursive(int n, int m) {
        int[][] dp = new int[n][m];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return get_number_of_paths(n - 1, m - 1, dp);
    }

        /*
    Asymptotic complexity in terms of `n` and `r`:
    * Time: O(n * r).
    * Auxiliary space: O(n * r).
    * Total space: O(n * r).
    */

    static final int P = 1000000007;

    static int[][] dp;

    static int ncr(int n, int r){
        if(r > n){
            return 0;
        }

        // Here dp[n][r] = nCr.
        dp = new int[n][r];

        for(int i = 0; i <= n; ++i){ // 0 -> 5
            for(int j = 0; j <= r && j <= i; ++j){ // 0 -> 3
                if(i == j || j == 0){
                    dp[i][j] = 1;
                }
                else{
                    dp[i][j] = (dp[i - 1][j] + dp[i - 1][j - 1]) % P;
                }
            }
        }
        return dp[n][r];
    }

        /*
    Asymptotic complexity in terms of `n`:
    * Time: O(2^n).
    * Auxiliary space: O(n).
    * Total space: O(n).
    */

    // This function returns number of different ways you can choose r elements from n distinct elements.
    static int bruteForceNcr(int n, int r){
        if(r > n){
            return 0;
        }
        if(n == r || r == 0){
            return 1;
        }

        return (ncr(n - 1, r) + ncr(n - 1, r - 1)) % P;
    }

    static int[][] dpNcr;

    // This function returns number of different ways you can choose r elements from n distinct elements.
    static int count_ways(int n, int r) {
        if (r > n) {
            return 0;
        }
        if (n == r || r == 0) {
            return 1;
        }

        if (dp[n][r] != -1) {
            return dp[n][r];
        }

        return dp[n][r] = (count_ways(n - 1, r) + count_ways(n - 1, r - 1)) % P;
    }

    static int dpNcrRecurseive(int n, int r) {
        dp = new int[n + 1][r + 1];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= r; j++) {
                dp[i][j] = -1;
            }
        }
        return count_ways(n, r);
    }

    /*
     * Asymptotic complexity in terms of the number of rows `n` and the number of columns `m`:
     * Time: O(n * m).
     * Auxiliary space: O(n * m).
     * Total space: O(n * m).
     */

    //space could be optimized since we only need current row and previous row
    static int maximum_path_sum(ArrayList<ArrayList<Integer>> grid) {
        int n = grid.size();
        int m = grid.get(0).size();
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid.get(i).get(j);
                } else if (j == 0) {
                    // First column.
                    dp[i][j] = dp[i - 1][j] + grid.get(i).get(j);
                } else if (i == 0) {
                    // First row.
                    dp[i][j] = dp[i][j - 1] + grid.get(i).get(j);
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + grid.get(i).get(j);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    /*
     * Asymptotic complexity in terms of the number of rows `n` and the number of columns `m`:
     * Time: O(n * m).
     * Auxiliary space: O(n * m).
     * Total space: O(n * m).
     */

    static int get_max_sum(ArrayList<ArrayList<Integer>> grid , int i, int j, int[][] memo) {
        if (i == 0 && j == 0) {
            return grid.get(i).get(j);
        }
        // If answer is already calculated then return the answer.
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int answer;
        if (j == 0) {
            answer = get_max_sum(grid, i - 1, j, memo) + grid.get(i).get(j);
        } else if (i == 0) {
            answer = get_max_sum(grid, i, j - 1, memo) + grid.get(i).get(j);
        } else {
            answer = Math.max(get_max_sum(grid, i - 1, j, memo), get_max_sum(grid, i, j - 1, memo)) + grid.get(i).get(j);
        }
        return memo[i][j] = answer;
    }

    static int maximumPathSumRecursive(ArrayList<ArrayList<Integer>> grid ) {
        int n = grid.size();
        int m = grid.get(0).size();

        int[][] memo = new int[n][m];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        int answer = get_max_sum(grid, n - 1, m - 1, memo);
        return answer;
    }

       /*
    Asymptotic complexity in terms of lengths of the strings `a` and `b`:
    * Time: O(2 ^ (len(a) * len(b))).
    * Auxiliary space: O(len(a) * len(b)).
    * Total space: O(len(a) * len(b)).
    */

    static int len_s1;
    static int len_s2;


    static boolean _string_interleave(String s1, String s2, String s3, int i, int j) {
        // base case
        if (i == len_s1 && j == len_s2) {
            return true;
        }

        int k = i + j;

        if (i < len_s1 && j < len_s2 && s1.charAt(i) == s2.charAt(j) && s2.charAt(j) == s3.charAt(k)) {
            // if all 3 are the same, take from either string
            return _string_interleave(s1, s2, s3, i + 1, j) || _string_interleave(s1, s2, s3, i, j + 1);
        } else if (i < len_s1 && s1.charAt(i) == s3.charAt(k)) {
            return _string_interleave(s1, s2, s3, i + 1, j);
        } else if (j < len_s2 && s2.charAt(j) == s3.charAt(k)) {
            return _string_interleave(s1, s2, s3, i, j + 1);
        } else {
            return false;
        }
    }

    static boolean string_interleave_rec(String s1, String s2, String s3) {
        len_s1 = s1.length();
        len_s2 = s2.length();
        int len_s3 = s3.length();
        if (len_s3 != len_s1 + len_s2) {
            return false;
        }

        return _string_interleave(s1, s2, s3, 0, 0);
    }

    static boolean do_strings_interleave(String a, String b, String i) {
        return string_interleave_rec(a, b, i);
    }

        /*
    Asymptotic complexity in terms of lengths of the strings `a` and `b`:
    * Time: O(len(a) * len(b)).
    * Auxiliary space: O(len(a) * len(b)).
    * Total space: O(len(a) * len(b)).
    */

    static Boolean do_strings_interleaveOptimal(String a, String b, String c) {
        int n = a.length();
        int m = b.length();
        // This cannot be an interleaving
        if (n + m != c.length()) {
            return false;
        }
        // dp[lenA][lenB] determines if lenA from string a and lenB from string b
        // makes interleaving of length lenA+lenB in c
        boolean[][] dp = new boolean[n + 1][m + 1];
        // Empty string can be formed by combining empty string from a and b.
        dp[0][0] = true;

        for (int k = 0; k < c.length(); k++) {
            for (int i = 0; i < a.length(); i++) {
                // if char at indexA in a matches char at indexI in c, check if previous
                // state was valid state
                if (a.charAt(i) == c.charAt(k)) {
                    int aLen = i;
                    int bLen = k - i;
                    // Check if bLen is in range
                    if (bLen >= 0 && bLen <= b.length()) {
                        if (bLen >= 0 && dp[aLen][bLen]) {
                            // If the previous state was an interleaving, new state will be an
                            // interleaving as characters match
                            dp[aLen + 1][bLen] = true;
                        }
                    }
                }
            }
            for (int j = 0; j < b.length(); j++) {
                // if char at j in B matches char at indexI in c, check if previous
                // state was valid state
                if (b.charAt(j) == c.charAt(k)) {
                    int aLen = k - j;
                    int bLen = j;
                    // Check if aLen is in range
                    if (aLen >= 0 && aLen <= a.length()) {
                        if (dp[aLen][bLen]) {
                            // If the previous state was an interleaving, new state will be an
                            // interleaving as characters match
                            dp[aLen][bLen + 1] = true;
                        }
                    }
                }
            }
        }
        // Return if the new string interleaving.
        return dp[n][m];
    }

    // c at i+j = i.charAt(i+j)
    // ai = a.charAt(i)
    // bj = b.charAt(j)
    // if (ai == bj == c)
    //  table[i][j] = table[i - 1][j] || table[i][j - 1]
    // else if (ai == c)
    //  table[i][j] = table[i - 1][j]
    // else if (bj == c)
    //   table[i][j] = table[i][j - 1]
    // else
    //  table[i][j] = false

    static Boolean do_strings_interleaveOPTIMAL(String s1, String s2, String testInput) {
        int s1Length = s1.length();
        int s2Length = s2.length();

        if (s1Length + s2Length != testInput.length()) {
            return false;
        }

        boolean[][] table = new boolean[s1Length][s2Length];

        for (int i = 0; i <= s1Length; i++) {
            char charS1 = s1.charAt(i);

            for (int j = 0; j <= s2Length; j++) {
                char charS2 = s2.charAt(i);

                char charTestInput = testInput.charAt(i + j);

                if (charS1 == charS2 && charS2 == charTestInput) {
                    if (i == 0 && j == 0) {
                        table[0][0] = true;
                        // abc
                        // def
                        // dabcef
                    } else if (i == 0) {
                        table[i][j] = table[i][j - 1];
                    } else if (j == 0) {
                        table[i][j] = table[i - 1][j];
                    } else {
                        table[i][j] = table[i - 1][j] || table[i][j - 1];
                    }
                } else if (charS1 == charTestInput)
                    if (i == 0) {
                        table[i][j] = true;
                    } else table[i][j] = table[i - 1][j];
                else if (charS2 == charTestInput) {
                    if (j == 0) {
                        table[i][j] = table[i][j];
                    } else table[i][j] = table[i][j - 1];
                } else
                    table[i][j] = false;
            }
        }
        return table[s2Length - 1][s2Length - 1];
    }

    static Integer largest_sub_square_matrix(Integer n, Integer m, ArrayList<ArrayList<Integer>> mat) {
        // Memoization table.
        ArrayList<Integer> dp = new ArrayList<Integer>();

        int maximum_size = 0;
        // Initializing dp array with first row of matrix mat.
        for (int i = 0; i < m; i++) {
            dp.add(mat.get(0).get(i));
            maximum_size = Math.max(maximum_size, dp.get(i));
        }
        int prev = 0;
        int diagonal = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Caching calculated answer for state (i - 1, j).
                int tmp = dp.get(j);

                // If current cell can be a bottom corner.
                if (mat.get(i).get(j) != 0) {
                    if (j != 0)
                        prev = dp.get(j - 1);
                    else
                        prev = 0;
                    // getting minimum from the below states
                    // state (i - 1, j - 1) ~ diagonal
                    // state (i, j - 1) ~ prev
                    // state (i - 1, j) ~ tmp
                    dp.set(j, Math.min(Math.min(prev, tmp), diagonal) + 1);
                } else {
                    dp.set(j, 0);
                }
                // Caching (i, j - 1) ~ tmp state as diagonal for next state.
                diagonal = tmp;

                maximum_size = Math.max(maximum_size, dp.get(j));
            }
        }
        return maximum_size;
    }

        /*
    Asymptotic complexity in terms of number of rows in `mat` `n` and number of columns in `mat` `m`:
    * Time: O(n * m).
    * Auxiliary space: O(1).
    * Total space: O(n * m).
    */

    static Integer largest_sub_square_matrixConcise(Integer n, Integer m,
                                              ArrayList<ArrayList<Integer>> mat) {
        int maximum_size = 0;

        // Initializing max sub square size "maximum_size"
        // by checking first row and first column of mat.
        for (int i = 0; i < n; i++)
            maximum_size |= mat.get(i).get(0);
        for (int j = 0; j < m; j++)
            maximum_size |= mat.get(0).get(j);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (mat.get(i).get(j) == 1) {
                    // getting minimum from the below states
                    // state (i - 1, j - 1)
                    // state (i, j - 1)
                    // state (i - 1, j)
                    int min_value = Math.min(mat.get(i - 1).get(j - 1), Math.min(mat.get(i - 1).get(j), mat.get(i).get(j - 1))) + 1;
                    mat.get(i).set(j, min_value);
                    maximum_size = Math.max(mat.get(i).get(j), maximum_size);
                }
            }
        }

        return maximum_size;
    }

    public static class WordWrap {
        public static int wordWrap(int[] words, int maxWidth, int index) {
            if (index == words.length) {
                return 0;
            }

            int minCost = Integer.MAX_VALUE;
            int currentLength = 0;

            for (int j = index; j < words.length; j++) {
                currentLength += words[j];

                if (currentLength > maxWidth) {
                    break;
                }

                int extraSpaces = maxWidth - currentLength;
                int cost = extraSpaces * extraSpaces;

                int totalCost = cost + wordWrap(words, maxWidth, j + 1);

                minCost = Math.min(minCost, totalCost);

                currentLength++;
            }
            return minCost;
        }

        // 0 -> currLength = 3, minCost = MAX, cost = 9 + wordWrap(1)
        //      1 -> currLength = 2, minCost = MAX, cost = 16 + wordWrap(2)
        //          2 -> currLength = 2, minCost = MAX, cost = 16 + wordWrap(3)
        //              3 -> currLength = 5, minCost = MAX, cost = 1 + wordWrap(4)
        //                  4 -> return 0
        //              3 -> currLength = 5, minCost = MAX, cost = 1, minCost = 1, currLength =
        //                              6, return 1
        //          2 -> currLength = 2, minCost = MAX, cost = 17, minCost = 17, currLength = 3,
        //              3 -> currLength = 8 -> break -> return 17
        //      1 -> currLength = 2, minCost = MAX, cost = 33, minCost = 33, currLength = 3
        //          2 -> currLength = 5, minCost = MAX, cost = 5 + wordWrap(3)
        //              3 -> currLength = 5, minCost = MAX, cost = 5 + wordWrap(4)
        //                  4 -> return 0
        //              3 -> currLength = 5, minCost = MAX, cost = 5, minCost = 5, currLength =
        //                              6, return 5
        //          2 -> currLength = 5, minCost = MAX, cost = 10, minCost = 10, currLength = 6,
        //                              return 5
        //      1 -> currLength = 2, minCost = MAX, cost = 33, minCost = 33, currLength = 3,
        //                              return 5
        //
        //
        //
    }

    public static Long solve_balanced_line_breaks(ArrayList<String> words, Integer limit) {
        int n = words.size();
        Long[] dp = new Long[n + 1]; // Minimum cost to arrange words from i to the end
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n] = 0L; // Base case: no cost if no words left

        // Precompute sum_length(i, j) for all pairs (i, j)
        int[][] sumLength = new int[n][n];
        for (int i = 0; i < n; i++) {
            sumLength[i][i] = words.get(i).length();
            for (int j = i + 1; j < n; j++) {
                sumLength[i][j] = sumLength[i][j - 1] + words.get(j).length() + 1; // +1 for space
            }
        }

        // Compute dp[i]
        // dp
        for (int end = 1; end <= n; end++) { // Start from the last word
            for (int start = 1; start <= end; start++) { // Consider placing words end to start on a line
                if (sumLength[start - 1][end - 1] <= limit) {
                    int spacesInLine = limit - sumLength[start - 1][end - 1];
                    int cost = spacesInLine * spacesInLine * spacesInLine;
                    dp[end] = Math.min(dp[end], cost + dp[start - 1]);
                }
            }
        }

        return dp[n]; // Minimum cost to arrange all words
    }

        /*
    Asymptotic complexity in terms of length of `words` `n` and `limit`:
    * Time: O(2^n).
    * Auxiliary space: O(n).
    * Total space: O(n * limit).
    */

    public static Long solve_balanced_line_breaks(List<String> words, int limit) {
        return helper(words, limit, 0);
    }
    static Long helper(List<String> words, int limit, int start) {
        int n = words.size();

        if (start >= words.size()){
            return 0L;
        }

        long minimumCost = Long.MAX_VALUE;

        int currentLineNonSpaceChars = 0;
        int currentLineWordCount = 0;
        int currentLineTotalChars = 0;
        Long currentLineCost;

        for (int i = start; i < n; i++) {
            // Here, current line means the first line having sequence of words [start, start+1, ..., i].

            currentLineNonSpaceChars += words.get(i).length();
            currentLineWordCount++;

            currentLineTotalChars = currentLineNonSpaceChars + currentLineWordCount - 1;
            if (currentLineTotalChars > limit){
                break;
            }

            currentLineCost = (i == n - 1) ? 0L : (limit - currentLineTotalChars); // cost of
            // last line is 0 even if there are spaces and word i = n - 1 will always be in the
            // last line

            currentLineCost = currentLineCost * currentLineCost * currentLineCost;

            minimumCost = Math.min(currentLineCost + helper(words, limit, i + 1), minimumCost);
        }

        return minimumCost;
    }

        /*
    Asymptotic complexity in terms of length of `words` `n` and `limit`:
    * Time: O(n^2).
    * Auxiliary space: O(n).
    * Total space: O(n * limit).
    */

    public static Long solve_balanced_line_breaks_DP(List<String>words, int limit) {
        long infinite = Long.MAX_VALUE;
        int n = words.size();

        // dp[i] = x denotes that the total minimized cost is x if we had sequence
        // of words [i, i+1, ..., n-1] and we put line breaks in a most balanced way.
        long[] dp = new long[n + 1];

        String[] stringsDp = new String[n + 1];

        StringBuilder current_line;
        int current_line_non_space_chars = 0;
        int no_of_words_in_current_line = 0;
        int current_line_total_chars = 0;
        long current_line_cost;

        for (int i = n - 1; i >= 0; i--) {
            current_line = new StringBuilder();
            current_line_non_space_chars = 0;
            no_of_words_in_current_line = 0;
            dp[i] = infinite;
            for (int j = i; j < n; j++) {
                // Here, current line means the first line having sequence of words
                // [i, i+1, ..., j].

                //current_line.appendwords.get(j);
                current_line_non_space_chars += words.get(j).length();
                no_of_words_in_current_line++;

                current_line_total_chars =
                        current_line_non_space_chars + no_of_words_in_current_line - 1;
                if (current_line_total_chars > limit) {
                    break;
                }

                if (!current_line.isEmpty()) {
                    current_line.append(' ').append(words.get(j));
                } else {
                    current_line.append(words.get(j));
                }

                current_line_cost = (j == n - 1) ? 0 : (limit - current_line_total_chars);
                current_line_cost = current_line_cost * current_line_cost * current_line_cost;

                stringsDp[i] = current_line + " lb " + stringsDp[j + 1];
                long previousMinCost = dp[i];
                long costForJplus1ThruN = dp[j + 1];
                // current line which is i through j + cost for j + 1 to n
                dp[i] = Math.min(current_line_cost + costForJplus1ThruN, dp[i]);

                System.out.println(
                        "Current index: " + j + "\n" +
                        "Current word: " + words.get(j) + "\n" +
                                "Current line: " + current_line + "\n" +
                                "Current line cost: " + current_line_cost + "\n" +
                                "Lines til now: " + stringsDp[i] + "\n" +
                                "Prev min cost til now: " + previousMinCost + "\n" +
                                "Minimum cost til now: " + dp[i] + "\n" +
                                "-------------------------------------------------------"
                );
            }
        }

        return dp[0];
    }

    /*
Asymptotic complexity in terms of `n`:
* Time: O(n^2).
* Auxiliary space: O(n^2).
* Total space: O(n^2).
*/
    static Integer max_win(ArrayList<Integer> v) {
        return _max_win_rec(v, 0, v.size()-1);
    }

    static Integer _max_win_rec(ArrayList<Integer> v, int i, int j) {
        if (i == j) {
            return v.get(i);
        }
        if (j == i + 1) {
            return Math.max(v.get(i), v.get(j));
        }
        if (i > j) {
            return Integer.MAX_VALUE;
        }

        Integer r2 = _max_win_rec(v, i+2, j);
        Integer rl = _max_win_rec(v, i+1, j-1);
        Integer l2 = _max_win_rec(v, i, j-2);

        int minR2RL = Math.min(r2, rl);
        int minRLL2 = Math.min(rl, l2);

        int startPlusTheRest = v.get(i) + minR2RL;
        int endPlusTheRest =  v.get(j) + minRLL2;

        return Math.max(startPlusTheRest, endPlusTheRest);
    }

    // i = 0, j = 5 -> r2 = , rl = , l2 =  -> min r2 rl = , min rl l2 = , spr =
    // , epr =
    //      i = 2, j = 5 -> minR2RL = 11, minRLL2 = 7, spr = 14, epr = 16 -> return 16
    //          i = 4, j = 5 -> return 11
    //          i = 3, j = 4 -> return 11
    //          i = 2, j = 3 -> return 7
    //
    //

        /*
    Asymptotic complexity in terms of `n`:
    * Time: O(n^2).
    * Auxiliary space: O(n^2).
    * Total space: O(n^2).
    */

    static Integer max_win_memo(ArrayList<Integer> v) {
        int n = v.size();
        // Create a table to store solutions of subproblems
        int table[][] = new int[n][n];
        recursiveSolve(v, table, 0, n - 1);
        return table[0][n - 1];
    }

    static int recursiveSolve(ArrayList<Integer> v, int table[][], int start, int end) {
        if (table[start][end] != 0) {
            return table[start][end];
        }
        if (start == end) {
            return table[start][end] = v.get(start);
        }
        if (start + 1 == end) {
            return table[start][end] = Math.max(v.get(start), v.get(end));
        }

        table[start][end] = Math.max(
                v.get(start) + Math.min(recursiveSolve(v, table, start + 2, end),
                        recursiveSolve(v, table, start + 1, end - 1)),
                v.get(end) + Math.min(recursiveSolve(v, table, start + 1, end - 1),
                        recursiveSolve(v, table, start, end - 2)));
        return table[start][end];

    }

    /*
    Asymptotic complexity in terms of `n`:
    * Time: O(n^2).
    * Auxiliary space: O(n^2).
    * Total space: O(n^2).
    */
    static Integer max_win_dp(ArrayList<Integer> v) {
        int n = v.size();

        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++){
            dp[i][i] = v.get(i);
        }

        for(int i = n-1; i >= 0; i--) {
            for(int j = i; j < n; j++){
                if(i == j){
                    dp[i][j] = v.get(i);
                    continue;
                }

                int o1 = i < n - 2 ? dp[i + 2][j] : Integer.MAX_VALUE;
                int o2 = i < n - 1 && j > 0 ? dp[i + 1][j - 1] : Integer.MAX_VALUE;
                int o3 = j > 1 ? dp[i][j - 2] : Integer.MAX_VALUE;

                dp[i][j] = Math.max(v.get(i) + Math.min(o1, o2), v.get(j) + Math.min(o2, o3));
            }
        }
        return dp[0][n-1];
    }


    static Double get_champagne_in_glass(Integer cups_poured, Integer query_row, Integer query_glass) {
        // Write your code here.
        double[][] table = new double[query_row + 1][query_glass + 1];
        return table[query_row][query_glass];
    }

    static void helper(double cupsToPour, int row, int cup, double[][] table) {
        if (cupsToPour <= 0) {
            return;
        }

        System.out.println("Row " + row + " cup " + cup + table[row][cup]);
        double remainingRoomInCup = 1.0 - table[row][cup];

        if (cupsToPour > remainingRoomInCup) {
            table[row][cup] = 1;
            cupsToPour = cupsToPour - remainingRoomInCup;
        } else {
            table[row][cup] = table[row][cup] + cupsToPour;
            return;
        }

        helper(cupsToPour / 2.0, row + 1, cup, table);
        helper(cupsToPour / 2.0, row + 1, cup + 1, table);

        // 5; 0,0; table(0,0) = 1; 4
        //      2; 1,0; table(1, 0) = 1; 1
        //          0.5; 2,0; table(2, 0) = 0.5
    }
}
