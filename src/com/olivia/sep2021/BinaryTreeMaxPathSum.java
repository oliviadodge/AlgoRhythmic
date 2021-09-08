package com.olivia.sep2021;

import com.olivia.TreeNode;

/*A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any path.
*/

class BinaryTreeMaxPathSum {

    public static void main(String[] args) {
        BinaryTreeMaxPathSum thing = new BinaryTreeMaxPathSum();
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode root1 = new TreeNode(3, node1, node2);

        Answer answer = new Answer(Integer.MIN_VALUE, Integer.MIN_VALUE);
        System.out.println("answer: " + thing.maxPathSumAnswer(root1, answer).currentMaxPathSum);
    }

    public Answer maxPathSumAnswer(TreeNode node, Answer answer) {
        if (node == null) {
            answer.maxPathUpToAndInclThisNode = 0;
            return answer;
        }

        int maxPathLeadingToLeft = Math.max(maxPathSumAnswer(node.left, answer).maxPathUpToAndInclThisNode, 0);

        int maxPathLeadingToRight = Math.max(maxPathSumAnswer(node.right, answer).maxPathUpToAndInclThisNode, 0);

        int pathThroughRoot = node.val + maxPathLeadingToLeft + maxPathLeadingToRight;

        answer.currentMaxPathSum = Math.max(answer.currentMaxPathSum, pathThroughRoot);

        answer.maxPathUpToAndInclThisNode = node.val + Math.max(maxPathLeadingToLeft, maxPathLeadingToRight);

        return answer;
    }

    static class Answer {

        int currentMaxPathSum;
        int maxPathUpToAndInclThisNode;

        Answer(int currentMaxPathSum, int  maxLegUpToAndIncludingThisNode) {
            this.currentMaxPathSum = currentMaxPathSum;
            this.maxPathUpToAndInclThisNode = maxLegUpToAndIncludingThisNode;
        }
    }
}
