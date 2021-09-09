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

class WordLadderBreadthFirstSearch {

    public static void main(String[] args) {
        WordLadderBreadthFirstSearch thing = new WordLadderBreadthFirstSearch();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        System.out.println("answer: " + thing.ladderLength(beginWord, endWord, wordList));
    }

    //breadth-first search
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int wordLength = beginWord.length();

        Map<String, List<String>> allComboDict = getWordMapping(wordList, wordLength);

        Queue<Map.Entry<String, Integer>> queue = new LinkedList<>();
        queue.add(new AbstractMap.SimpleEntry<>(beginWord, 1));

        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()) {
            Map.Entry<String, Integer> node = queue.remove();
            String word = node.getKey();
            int nodeLevel = node.getValue();
            for (int i = 0; i < wordLength; i++) {

                String wordKey = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                for (String adjacentWord : allComboDict.getOrDefault(wordKey, new ArrayList<>())) {
                    if (adjacentWord.equals(endWord)) {
                        return nodeLevel + 1;
                    }
                    if (!visited.containsKey(adjacentWord)) {
                        addAdjacentWordToQueue(queue, nodeLevel, adjacentWord);
                        visited.put(adjacentWord, true);
                    }
                }
            }
        }
        return 0;
    }

    private void addAdjacentWordToQueue(Queue<Map.Entry<String, Integer>> queue, int level, String adjacentWord) {
            queue.add(new AbstractMap.SimpleEntry<>(adjacentWord, level + 1));
    }

    private Map<String, List<String>> getWordMapping(List<String> wordList, int wordLength) {
        Map<String, List<String>> allComboDict = new HashMap<>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < wordLength; i++) {
                        String keyWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                        List<String> wordsForKey = allComboDict.getOrDefault(keyWord, new ArrayList<>());
                        wordsForKey.add(word);
                        allComboDict.put(keyWord, wordsForKey);
                    }
                });
        return allComboDict;
    }
}
