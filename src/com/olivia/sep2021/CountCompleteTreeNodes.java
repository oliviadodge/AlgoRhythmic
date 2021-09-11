package com.olivia.sep2021;

import com.olivia.TreeNode;

class CountCompleteTreeNodes {

    public static void main(String[] args) {
        CountCompleteTreeNodes thing = new CountCompleteTreeNodes();
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node5 = new TreeNode(5, null, null);
        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node3 = new TreeNode(3, node6, null);
        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node1 = new TreeNode(1, node2, node3);

        System.out.println("answer: " + thing.countNodes(node1));
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int level = 1;
        TreeNode node = root;

        while (node.right != null) {
            level++;
            node = node.right;
        }

        int count = ((int)Math.pow(2, level)) - 1;

        count = count + countNodesAtLastLevel(root, 1, level);

        return count;
    }

    private int countNodesAtLastLevel(TreeNode node, int level, int lastLevel) {
        if (node == null) return 0;
        if (level == lastLevel) {
            if (node.left == null) {
                return 0;
            } else if (node.right == null) {
                return 1;
            } else {
                return 2;
            }
        } else {
            int nextLevel = level + 1;
            return countNodesAtLastLevel(node.left, nextLevel, lastLevel) + countNodesAtLastLevel(node.right, nextLevel, lastLevel);
        }
    }
}
