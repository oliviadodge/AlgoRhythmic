package com.olivia.sep2021;

import java.util.*;

/*A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
*/

class WordLadderBidirectionalSearch {

    public static void main(String[] args) {
        WordLadderBidirectionalSearch thing = new WordLadderBidirectionalSearch();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println("answer: " + thing.ladderLength(beginWord, endWord, wordList));
    }

    private int wordLength;
    private final Map<String, List<String>> allComboDict;

    WordLadderBidirectionalSearch() {
        this.wordLength = 0;

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        this.allComboDict = new HashMap<>();
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        if (!wordList.contains(endWord)) {
            return 0;
        }

        // Since all words are of same length.
        this.wordLength = beginWord.length();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < wordLength; i++) {
                        String wordKey = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                        List<String> transformations =
                                this.allComboDict.getOrDefault(wordKey, new ArrayList<>());
                        transformations.add(word);
                        this.allComboDict.put(wordKey, transformations);
                    }
                });

        // Queues for birdirectional BFS
        Queue<Map.Entry<String, Integer>> queueFromBegin = new LinkedList<>();
        Queue<Map.Entry<String, Integer>> queueFromEnd = new LinkedList<>();

        queueFromBegin.add(new AbstractMap.SimpleEntry<>(beginWord, 1));
        queueFromEnd.add(new AbstractMap.SimpleEntry<>(endWord, 1));

        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!queueFromBegin.isEmpty() && !queueFromEnd.isEmpty()) {

            // One hop from begin word
            int ans = visitWordNode(queueFromBegin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }

            // One hop from end word
            ans = visitWordNode(queueFromEnd, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }
        return 0;
    }

    private int visitWordNode(
            Queue<Map.Entry<String, Integer>> nodeQueue,
            Map<String, Integer> visited,
            Map<String, Integer> othersVisited) {

        Map.Entry<String, Integer> node = nodeQueue.remove();
        String word = node.getKey();
        int nodeLevel = node.getValue();

        for (int i = 0; i < this.wordLength; i++) {

            // Intermediate words for current word
            String wordKey = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);

            // Next states are all the words which share the same intermediate state.
            for (String adjacentWord : this.allComboDict.getOrDefault(wordKey, new ArrayList<>())) {
                // If at any point if we find what we are looking for
                // i.e. the end word - we can return with the answer.
                if (othersVisited.containsKey(adjacentWord)) {
                    return nodeLevel + othersVisited.get(adjacentWord);
                }

                if (!visited.containsKey(adjacentWord)) {

                    // Save the level as the value of the dictionary, to save number of hops.
                    visited.put(adjacentWord, nodeLevel + 1);
                    nodeQueue.add(new AbstractMap.SimpleEntry<>(adjacentWord, nodeLevel + 1));
                }
            }
        }
        return -1;
    }
}

