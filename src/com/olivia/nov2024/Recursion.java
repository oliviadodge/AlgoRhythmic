package com.olivia.nov2024;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Recursion {
    private static final String TAG = Recursion.class.getSimpleName();

    private static Integer callCount = 0;

    static void combinations_recursive(int current_number, int n, int k, ArrayList<Integer> current,
                                       ArrayList<ArrayList<Integer>> result) {
        callCount += 1;
        System.out.println("Call " + callCount + ": current number: " + current_number + " " +
                "current combo: " + current + " results: " + result);

        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (current_number == n + 1) {
            return;
        }

        //1: 1, [], [] (Stack = 1)
        //2: 2, [1], [] (Stack = 1, 2)
        //3: 3, [1,2], [] (Stack = 1, 2, 3)
        //Pop 3: result = [[1,2]] (Stack = 1, 2)
        //4: 3, [1], [[1,2]] (Stack = 1, 2, 4)
        //5: 4, [1, 3], [[1,2]] (Stack = 1, 2, 4, 5)
        //Pop 5: result = [[1,2], [1,3]] (Stack = 1, 2, 4)
        //6: 4, [1], [[1,2], [1,3]] (Stack = 1, 2, 4, 6)
        //7: 5, [1,4], [[1,2], [1,3]] (Stack = 1, 2, 4, 6, 7)
        //Pop 7: result = [[1,2], [1,3], [1,4]] (Stack = 1, 2, 4, 6)
        //8: 5, [1], [[1,2], [1,3], [1,4]] (Stack = 1, 2, 4, 6, 8)
        //9: 6, [1, 5], [[1,2], [1,3], [1,4]] (Stack = 1, 2, 4, 6, 8, 9)
        //Pop 9: result = [[1,2], [1,3], [1,4], [1,5]] (Stack = 1, 2, 4, 6, 8)
        //10: 6, [1], [[1,2], [1,3], [1,4], [1,5]] (Stack = 1, 2, 4, 6, 8, 10)
        //Pop 10 --> return to call 1??? (Stack = 1, 2, 4, 6, 8), why not 8
        //11: 2, [], [[1,2], [1,3], [1,4], [1,5]]
        //12: 3, [2], [[1,2], [1,3], [1,4], [1,5]]
        //13: 4, [2, 3], [[1,2], [1,3], [1,4], [1,5]]

        current.add(current_number);
        combinations_recursive(current_number + 1, n, k, current, result);
        current.remove(current.size() - 1);
        combinations_recursive(current_number + 1, n, k, current, result);
    }

    public static ArrayList<ArrayList<Integer>> find_combinations(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> current = new ArrayList<>();

        combinations_recursive(1, n, k, current, result);
        return result;
    }


    public static ArrayList<ArrayList<Integer>> findCombinations(Integer n, Integer k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        comboHelper(n, 1, k, new ArrayList(), ans);
        return ans;
    }

    private static void comboHelper(Integer n, Integer index, Integer k, ArrayList<Integer> current,
                             ArrayList<ArrayList<Integer>> ans) {
        if (current.size() == k) {
            ans.add(new ArrayList(current));
        } else {
            for (int i = index; i <= n; i++) {
                current.add(i);
                comboHelper(n, i + 1, k, current, ans);
                current.remove(current.size() - 1);
            }
        }
    }




    /*
     * Asymptotic complexity in terms of length of `s` `n`:
     * Time: O(2^n).
     * Auxiliary space: O(n).
     * Total space: O(n * 2^n).
     */

    static void get_subsets(String str, int index, String current, ArrayList<String> subsets) {
        if (index == str.length()) {
            subsets.add(current);
            return;
        }

        int end = index + 1;
        while (end < str.length() && str.charAt(end) == str.charAt(index)) {
            end++;
        }

        // str[index] can be inserted 0 times or 1 times or 2 times ... or (end - index) number of times.
        int count = 0;
        String add = "";

        get_subsets(str, end, current + add, subsets);
        while (count < end - index) {
            add += str.charAt(index);

            // Recurring for index = end so as to skip all the occurrences of str[index].
            get_subsets(str, end, current + add, subsets);
            count++;
        }
    }
    //s = aaabbc
    //s.length() = 6
    //subsets = "", "c", "b", "bc", "bb", "a", "aa"

    //s(0, "") -> end = 3 -> count = 0, add = "" -> call s(3, ""), add = "a", s(3, "a")
        //s(3, "") -> end = 5 -> count = 0, add = "" -> call s(5, ""), add = "b" call s(5, "b"),
    // count = 1, add = "bb", s(5, "bb"), count = 2, return to s(0, "")
            //s(5, "") end = 6, count = 0, add = "", call s(6, ""), add = "c", s(6, "c") returns to s(3, "")
                //s(6, "") returns to s(5, "")
                //s(6, "c") returns to s(5, "")
            //s(5, "b"), end = 6, count = "0", add = "", s(6, "b"), s(6, "bc") returns to s(3, "")
                //s(6, "b") returns to s(5, "b")
                //s(6, "bc") returns to s(5, "b")
            //s(5, "bb"), end = 6, count = "0", add ="", s(6, "bb"), count = 1 return to s(3, "")
                //s(6, "bb") returns to s(5, "bb")
        //s(3, "a"), end = 5, count = 0, add = "", s(5, "a"), add = "a", s(5, "ab")
            //s(5, "a"), end = 6, count = 0, add = "", s(6, "a"), add = "aa", s(6, "aa"), count =
    // 1, return to s(3, "a")
                //s(6, "a") return to s(5, "a")
                //s(6, "aa") return to s(5, "a")
            //s(5, "ab") end = 6, count = 0, add = "", s(6, "ab")


    public static ArrayList<String> get_distinct_subsets(String s) {
        ArrayList<String> subsets = new ArrayList<>();

        // We need to make sure that all the subsets are individually sorted.
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String sortedStr = new String(chars);

        get_subsets(sortedStr, 0, "", subsets);
        return subsets;
    }

    public static void recursionExperiment(int index, List<List<Integer>> thisList) {
        if (index == 3) return;
        for (int i = index; i < 3; i++) {
            ArrayList<Integer> current = new ArrayList<>();
            current.add(index);
            current.add(i);
            System.out.println("Final Results: " + current);
            thisList.add(current);
            recursionExperiment(i + 1, thisList);
        }
    }

    public static void iterativeExperiment(int index, List<List<Integer>> thisList) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ArrayList<Integer> current = new ArrayList<>();
                current.add(i);
                current.add(j);
                System.out.println("Final Results: " + current);
                thisList.add(current);
            }
        }

    }

    /*
     * Asymptotic complexity in terms of the given length ( = `n`):
     * Time: O(log(n)).
     * Auxiliary space: O(1).
     * Total space: O(1).
     */
    // Function to calculate (x^y) % p.
    static long modular_exponentiation(long x, long y, long mod) {
        long result = 1;
        x = x % mod;
        if (x == 0) {
            return 0;
        }
        while (y > 0) {
            if ((y & 1) == 1) {
                result = (result * x) % mod;
            }
            y = y >> 1;
            x = (x * x) % mod;
        }
        return result;
    }

    static int count_good_digit_strings(long n) {
        int mod = 1_000_000_007;
        long odd_indices = n / 2;
        long even_indices = (n / 2) + (n % 2);
        // There can be 5 digits at even indices (0, 2, 4, 6, 8) and 4 digits at odd indices (2, 3, 5, 7).
        // Hence, the total number of good digit strings will be (5 ^ even indices) * (4 ^ odd indices).
        return (int) ((modular_exponentiation(5, even_indices, mod) * modular_exponentiation(4, odd_indices, mod)) % mod);
    }

    /*
     * Asymptotic complexity in terms of the given length ( = `n`):
     * Time: O(log(n)).
     * Auxiliary space: O(1).
     * Total space: O(1).
     */
    // Function to calculate (numOptions^numIndices) % p.
    static long modular_exponentiation_recurs(long numOptions, long numIndices, long mod) {
        if (numIndices == 0) {
            return 1;
        }
        long ans = modular_exponentiation_recurs(numOptions, numIndices / 2, mod);
        ans *= ans;
        ans %= mod;
        if (numIndices % 2 == 1) {
            ans *= numOptions;
            ans %= mod;
        }
        return ans;
    }

    static int count_good_digit_strings_recurse(long n) {
        int mod = 1_000_000_007;
        long numOdds = n / 2;
        long numEvens = (n / 2) + (n % 2);
        return (int) ((modular_exponentiation_recurs(5, numEvens, mod) * modular_exponentiation_recurs(4, numOdds, mod)) % mod);
    }

        /*
    Asymptotic complexity in terms of number of teams `n`:
    * Time: O(n*log(n)).
    * Auxiliary space: O(n).
    * Total space: O(n).
    */

    private static String generate_fixture_recursive(ArrayList<String> rounds, int n) {
        if (n == 1) {
            return rounds.get(0);
        }
        for (int i = 0; i < (n / 2); i++) {
            rounds.set(i, "(" + rounds.get(i) + "," + rounds.get(n - i - 1) + ")");
        }
        return generate_fixture_recursive(rounds, n / 2);
    }

    static String generate_fixture(int n) {
        ArrayList<String> rounds = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            rounds.add(Integer.toString(i));
        }
        return generate_fixture_recursive(rounds, n);
    }


    public static Integer find_the_winner(Integer n, Integer k) {
        ArrayList<Integer> friends = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            friends.add(i);
        }

        Integer i = ((k) - 1) % friends.size();
        return helper(friends, i, k);
    }

    static Integer helper(ArrayList<Integer> friends, int i, Integer k) {
        if (friends.size() == 1) return friends.get(0);

        int friend = friends.get(i);
        friends.remove(i);

        int newIndex = ((i + k) - 1) % friends.size();

        return helper(friends, newIndex, k);
    }

    /*
Asymptotic complexity in terms of length of each word `l` and length of the set of words `n`:
    * Time: O(n * l * l).
    * Auxiliary space: O(n * n).
    * Total space: O(n * n).
*/

    // Function to check if the current arrangement forms a valid word square
    private static boolean is_valid(ArrayList<String> square) {
        int n = square.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (square.get(i).charAt(j) != square.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Backtracking function to find all word squares
    private static void backtrack(ArrayList<String> words, ArrayList<String> square, ArrayList<ArrayList<String>> squares, int row) {
        int n = words.get(0).length();

        // Base case: if we have filled all rows and formed a valid square, add to results
        if (row == n) {
            if (is_valid(square)) {
                squares.add(new ArrayList<>(square));
            }
            return;
        }

        // Recursively try placing each word in the current row
        for (String word : words) {
            // Check if the current prefix matches the required prefix for this column
            boolean validPrefix = true;
            for (int k = 0; k < row; ++k) {
                if (square.get(k).charAt(row) != word.charAt(k)) {
                    validPrefix = false;
                    break;
                }
            }

            if (validPrefix) {
                square.set(row, word);
                backtrack(words, square, squares, row + 1);
            }
        }
    }

    public static String get_kth_permutation(Integer n, Integer k) {
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        ArrayList<String> results = new ArrayList<>();
        helper("", results, nums, n, k);
        return results.get(k - 1);
    }

    static String kthSmallest = "";

    static ArrayList<String> helper(String current, ArrayList<String> results, ArrayList<Integer> remainingNums, int n, int k) {
        System.out.println(current);
        if (results.size() == k) {
            return results;
        }
        if (current.length() == n) {
            results.add(current);
            current = "";
        }

        for (int i = 0; i < remainingNums.size(); i++) {
            current += remainingNums.get(i);

            ArrayList<Integer> newList = new ArrayList(remainingNums);
            newList.remove(i);

            helper(current, results, newList, n, k);
        }

        return results;
    }

    //["area", "card", "cart", "dart", "rear", "tart"]

    // 0 to 3
    // card -> d
    // does d = word.charAt(0)?
    // 1, 3
    // area -> a
    // does a = word.charAt(1)?
    // 2, 3
    // rear -> r
    // does r = word.charAt(2)

    // Function to find all word squares
    public static ArrayList<ArrayList<String>> find_word_squares(ArrayList<String> words) {
        int n = words.get(0).length(); // length of each word

        ArrayList<ArrayList<String>> squares = new ArrayList<>();
        ArrayList<String> square = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            square.add("");
        }

        ArrayList<String> wordList = new ArrayList<>();
        for (String word : words) {
            wordList.add(word);
        }

        backtrack(wordList, square, squares, 0);

        return squares;
    }

    /*
Asymptotic complexity in terms of `n`:
    * Time: O(n! * n + n! * log(n!)).
    * Auxiliary space: O(n).
    * Total space: O(n).
*/

    // recursive function to generate all
    // possible permutations of a string
    static void generate_permutations(String str, int i,
                                      ArrayList<String> result)
    {
        // base case
        if (i == str.length()) {
            System.out.println("Results += " + str);
            result.add(str);
            return;
        }

        // traverse string from i to end
        for (int j = i; j < str.length(); j++) {
            if (i != j) {
                char c1 = str.charAt(i);
                char c2 = str.charAt(j);
//                System.out.println("Swapping " + i + " and " + j + " = " + swap(str, j, i));
            }

            System.out.println("Recursive call: " + i + "." + j);

            str = swap(str, j, i);

            int newIdx = i + 1;
//            System.out.println("RECURSE with i = " + newIdx);
            generate_permutations(str, i + 1, result);

            char c1 = str.charAt(i);
            char c2 = str.charAt(j);
//            if (i != j) {
//                System.out.println("Swapping " + c1 + " and " + c2 + " = " + swap(str, j, i) + " " +
//                        "RE-SWAP");
//            }

            str = swap(str, j, i);

        }
    }

    // Function to find the
    // kth permutation of n numbers
    public static String get_kth_permutation(int n, int k)
    {
        String str = "";
        ArrayList<String> result = new ArrayList<>();

        // Insert all natural number
        // upto n in string
        for (int i = 1; i <= n; i++) {
            str += i;
        }
        generate_permutations(str, 0, result);

        // sort the generated permutations
        Collections.sort(result);

        // make k 0-based indexed to point to kth sequence
        return result.get(k - 1);
    }

    // Function to swap characters at position i and j in string str
    static String swap(String str, int i, int j) {
        char[] charArray = str.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return new String(charArray);
    }

    /*
Asymptotic complexity in terms of `n`:
* Time: O(n * n).
* Auxiliary space: O(n).
* Total space: O(n).
*/
/*
Asymptotic complexity in terms of `n`:
    * Time: O(n * n).
    * Auxiliary space: O(n).
    * Total space: O(n).
*/

    public static String get_kth_permutation2(int n, int k) {
        List<Integer> factorial = new ArrayList<>(n + 1);
        List<Integer> digits = new ArrayList<>(n);
        StringBuilder kthPerm = new StringBuilder();

        for (int i = 0; i <= n; i++) {
            factorial.add(1);
        }

        for (int i = 0; i < n; i++) {
            digits.add(i + 1);
            factorial.set(i + 1, (i + 1) * factorial.get(i));
        }

        k--;

        for (int i = 1; i <= n && k >= 0; i++) {
            int index = k / factorial.get(n - i);
            kthPerm.append(digits.get(index));
            digits.remove(index);
            k -= index * factorial.get(n - i);
        }

        return kthPerm.toString();
    }


/*
Asymptotic complexity in terms of the number of the parentheses in the expression `n`
and the length of the input expression `l`:
    * Time: O(2^n * l).
    * Auxiliary space: O(l).
    * Total space: O(2^n * l).
*/

    // Maximum number of parentheses in any input expression(which is 20) + 1.
    private static int current_minimum_removed = 21;
    private static HashSet<String> valid_expressions = new HashSet<>();

    private static void helper(int id, String expression, StringBuilder current_expression, int no_of_left_parentheses, int no_of_right_parentheses, int removal_count) {
        if (removal_count > current_minimum_removed) return;
        if (id == expression.length()) {
            // Condition checks the validity of the expression.
            if (no_of_left_parentheses == no_of_right_parentheses) {
                // Found a better solution, hence remove previous solutions and only keep the current one.
                if (current_minimum_removed > removal_count) {
                    valid_expressions.clear();
                    current_minimum_removed = removal_count;
                    valid_expressions.add(current_expression.toString());
                } else if (current_minimum_removed == removal_count) {
                    valid_expressions.add(current_expression.toString());
                }
            }
        } else {
            // The current character is a lowercase English letter.
            if (expression.charAt(id) != ')' && expression.charAt(id) != '(') {
                // Add this alphabet to the current expression.
                current_expression.append(expression.charAt(id));
                helper(id + 1, expression, current_expression,
                        no_of_left_parentheses, no_of_right_parentheses, removal_count);
                // Remove this added alphabet to try out other possibilities.
                current_expression.deleteCharAt(current_expression.length() - 1);
            } else {
                // Not taking the current parenthesis.
                helper(id + 1, expression, current_expression,
                        no_of_left_parentheses, no_of_right_parentheses, removal_count + 1);

                // Now, trying to add the current parenthesis.
                current_expression.append(expression.charAt(id));
                if (expression.charAt(id) == '(') {
                    helper(id + 1, expression, current_expression,
                            no_of_left_parentheses + 1, no_of_right_parentheses, removal_count);
                }
                // Checking the validity of 'current_expression' on the go.
                else if (no_of_left_parentheses > no_of_right_parentheses) {
                    helper(id + 1, expression, current_expression,
                            no_of_left_parentheses, no_of_right_parentheses + 1, removal_count);
                }
                // Remove the added parenthesis to try out other possibilities.
                current_expression.deleteCharAt(current_expression.length() - 1);
            }
        }
    }

    public static ArrayList<String> remove_invalid_parentheses(String expression) {
        StringBuilder current_expression = new StringBuilder();
        helper(0, expression, current_expression, 0, 0, 0);
        return new ArrayList<>(valid_expressions);
    }

        /*
    Asymptotic complexity in terms of the length of `s` `n`:
    * Time: O(2^(n-1) * n).
    * Auxiliary space: O(2^(n-1) * n).
    * Total space: O(2^(n-1) * n).
    */

    // Check if s[l, r] is a palindrome or not.
    static boolean is_palindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    static void generate_palindromic_decompositions_util(ArrayList<String> decompositions_container, String s,
                                                         int pos, String cur_decomposition) {
        int n = s.length();
        // If we have reached the end, add the string.
        if (pos == n) {
            decompositions_container.add(cur_decomposition);
            return;
        }
        // Try to add s[pos, i] if it a palindrome.
        for (int i = pos; i < n; i++) {
            if (is_palindrome(s, pos, i)) {
                if (pos == 0) {
                    // We are adding s[0, i] so do not put '|' before it.
                    generate_palindromic_decompositions_util(decompositions_container, s, i + 1,
                            s.substring(pos, i + 1));
                } else {
                    generate_palindromic_decompositions_util(decompositions_container, s, i + 1,
                            cur_decomposition + "|" + s.substring(pos, i + 1));
                }
            }
        }
    }

    static ArrayList<String> generate_palindromic_decompositions(String s) {
        ArrayList<String> decompositions_container = new ArrayList<>();
        generate_palindromic_decompositions_util(decompositions_container, s, 0, "");
        return decompositions_container;
    }

        /*
    Asymptotic complexity in terms of the number of words `no_of_words` and the length of any word `length`:
    * Time: O(no_of_words * length * minimum(no_of_words, 26 * length)).
    * Auxiliary space: O(no_of_words * length).
    * Total space: O(no_of_words * length).
    */

    static int length, no_of_words;
    static Queue<Integer> bfs_q;
    static ArrayList<Boolean> visited;
    static HashMap<String, Integer> position;
    static HashMap<Integer, Integer> parent;

    // Check if str1 and str2 differ by exactly one character.
    static boolean only_one_char_difference(int length, String str1, String str2)
    {
        int difference = 0;
        for (int i = 0; i < length; i++)
        {
            if (str1.charAt(i) != str2.charAt(i))
            {
                if (difference == 1)
                {
                    return false;
                }
                difference++;
            }
        }
        return difference == 1;
    }

    // Update neighbors using O(length * length * 26) method.
    static void add_neighbours_with_method2(String current_word, int idx)
    {
        StringBuilder temp_str = new StringBuilder(current_word);
        for (int i = 0; i < length; i++)
        {
            for (char ch = 'a'; ch <= 'z'; ch++)
            {
                if (ch == current_word.charAt(i))
                {
                    continue;
                }
                // Store the original character, this will help to reverse the change.
                char original_character = temp_str.charAt(i);
                temp_str.setCharAt(i, ch);
                // Check if new string is in words array or not.
                int it = position.getOrDefault(temp_str.toString(), -1);
                if (it != -1)
                {
                    int position_of_current_word_in_words_array = it;
                    if (!visited.get(position_of_current_word_in_words_array))
                    {
                        visited.set(position_of_current_word_in_words_array, true);

                        // This string is visited by idx-th string.
                        // Insert this parent - child pair to construct the answer later.
                        parent.put(position_of_current_word_in_words_array, idx);

                        // This condition is used to check if the `stop` word is reached.
                        // The `stop` word was pushed at the end of the `words` array.
                        if (position_of_current_word_in_words_array == no_of_words - 1)
                        {
                            return;
                        }
                        bfs_q.add(position_of_current_word_in_words_array);
                    }
                }
                temp_str.setCharAt(i, original_character);
            }
        }
    }

    // Update neighbors using O(no_of_words * length) method.
    static void add_neighbours_with_method1(ArrayList<String> words, String current_word, int idx)
    {
        for (int i = 0; i < no_of_words; i++)
        {
            // If neighbor is not visited and has only one character different from current string.
            if (!visited.get(i) && only_one_char_difference(length, current_word, words.get(i)))
            {
                visited.set(i, true);

                // This string is visited by idx-th string.
                // Insert this parent - child pair to construct the answer later.
                parent.put(i, idx);

                // This condition is used to check if the `stop` word is reached.
                // The `stop` word was pushed at the end of the `words` array.
                if (i == no_of_words - 1)
                {
                    return;
                }
                bfs_q.add(i);
            }
        }
    }

    static void bfs(ArrayList<String> words, String start, String stop)
    {
        // When no_of_words > length * 26, we are going to use method2.
        // So, we need to use hash map for faster search.
        // For search if string is present in words array or not in O(length) time.
        if (no_of_words > length * 26)
        {
            for (int i = 0; i < no_of_words; i++)
            {
                position.put(words.get(i), i);
            }
        }
        // -1 means start string.
        bfs_q.add(-1);

        visited = new ArrayList<>(Collections.nCopies(no_of_words, false));

        //https://teams.microsoft.com/l/meetup-join/19%3ameeting_MmMzNjY1NWQtZWZiOS00Mjg1LTkxMzAtNTkyYTg0N2VjOGI4%40thread.v2/0?context=%7b%22Tid%22%3a%22fb007914-6020-4374-977e-21bac5f3f4c8%22%2c%22Oid%22%3a%226f9c3015-3e9b-4185-a6e2-4b7ee9bf9d69%22%7d
        //https://teams.microsoft.com/l/meetup-join/19%3ameeting_NTdjYmJlMTktYTgwOC00MzIzLTk3OGUtZmUwNDM2NmRmN2Zk%40thread.v2/0?context=%7b%22Tid%22%3a%22fb007914-6020-4374-977e-21bac5f3f4c8%22%2c%22Oid%22%3a%226f9c3015-3e9b-4185-a6e2-4b7ee9bf9d69%22%7d
        while (!bfs_q.isEmpty())
        {
            int idx = bfs_q.poll();
            // Stores string that is at the front of queue.
            String current_word;
            if (idx == -1)
            {
                current_word = start;
            }
            else
            {
                current_word = words.get(idx);
            }
            // Update the neighbors.
            if (no_of_words <= length * 26)
            {
                add_neighbours_with_method1(words, current_word, idx);
            }
            else
            {
                add_neighbours_with_method2(current_word, idx);
            }
        }
    }

    static ArrayList<String> string_transformation(ArrayList<String> words, String start, String stop)
    {
        no_of_words = 0;
        bfs_q = new LinkedList<>();
        visited = new ArrayList<>();
        position = new HashMap<String, Integer>();
        parent = new HashMap<Integer, Integer>();
        length = start.length();

        // stop word needs to be added at the end of the dictionary words.
        if (words.contains(stop)) {
            words.remove(stop);
        }
        words.add(stop);

        no_of_words = words.size();

        bfs(words, start, stop);

        // From parent information gathered from BFS, construct the actual string transformation.
        ArrayList<String> answer = new ArrayList<>();

        int stop_index = no_of_words - 1;
        // If stop string is not reached in BFS.
        if (!parent.containsKey(stop_index))
        {
            answer.add("-1");
            return answer;
        }

        // Start from stop string. Go to its parent, then its parent's parent... till we reach start string.
        while (stop_index != -1)
        {
            answer.add(words.get(stop_index));
            stop_index = parent.get(stop_index);
        }

        answer.add(start);
        // Reverse the `answer` array since it contains strings in the reverse order.
        Collections.reverse(answer);
        return answer;
    }

}



// 8
//      8 -> rounds(0) = (1,8) rounds(1) = (2,7) rounds(2) = (3,6) rounds(3) = (4,5)
//          4 -> rounds(0) = ((1,8),(4,5)) rounds(1) = ((2,7),(3,6))
//              2 -> rounds(0) = (((1,8),(4,5)),((2,7),(3,6)))



//5, 2
// 0: current = [1], currentNum = 1
// 1: current = [1,2], currentNum = 2
// 2: return to 1, currentNum = 3
// 1: current.remove(1) --> current = [1], currentNum = 2
// 3: current = [1,3] currentNum = 3
// 4: currentNum = 5, return to 3
// 3: currentNum = 3, current.remove(1) --> current = [1]
// 5: currentNum = 4, current = [1,4]
// 6: currentNum = 5, return to 5
// 5: currentNum = 4, current.remove(1) --> current = [1]
// 7: currentNum = 5, current = [1,5]
// 8: currentNum = 6, return to 7
// 7: currentNum = 5, current.remove(1) --> current = [1]
// 9: currentNum = 6, return to 7
// 7: currentNum = 5,

