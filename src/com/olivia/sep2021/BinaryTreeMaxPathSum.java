package com.olivia.sep2021;

import com.olivia.TreeNode;

class BinaryTreeMaxPathSum {

    public static void main(String[] args) {
        BinaryTreeMaxPathSum thing = new BinaryTreeMaxPathSum();
        TreeNode node1 = new TreeNode(1, null, null);
        TreeNode node2 = new TreeNode(2, null, null);
        TreeNode root1 = new TreeNode(3, node1, node2);

        System.out.println("median: " + thing.maxPathSum(root1));
    }

    int maxPathSum = Integer.MIN_VALUE;

    public int maxPathLeadingToNode(TreeNode node) {
        if (node == null) return 0;

        int maxPathLeadingToLeft = Math.max(maxPathLeadingToNode(node.left), 0);

        int maxPathLeadingToRight = Math.max(maxPathLeadingToNode(node.right), 0);

        int pathThroughRoot = node.val + maxPathLeadingToLeft + maxPathLeadingToRight;

        maxPathSum = Math.max(maxPathSum, pathThroughRoot);

        return node.val + Math.max(maxPathLeadingToLeft, maxPathLeadingToRight);
    }

    public int maxPathSum(TreeNode root) {
        maxPathLeadingToNode(root);
        return maxPathSum;
    }
}
