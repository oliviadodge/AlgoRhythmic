package com.olivia;

public class LongestUnivaluePathClean {

    public static void main(String[] args) {
        TreeNode leaf0 = new TreeNode(5);
        TreeNode right1 = new TreeNode(5, null, leaf0);
        TreeNode leftLeft = new TreeNode(4, leaf0, null);
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leftRight = new TreeNode(4, leaf4, null);
        TreeNode right0 = new TreeNode(5, null, right1);
        TreeNode left0 = new TreeNode(4, leftLeft, leftRight);
        TreeNode root = new TreeNode(1, left0, null);
        System.out.println("length of longest overall univalue path: " + longestOverallUnivalue(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int longestOverallUnivalue(TreeNode root) {
        if ((root == null) || (isLeaf(root))) {
            return 0;
        } else if ((root.right == null) && (leftMatches(root))) {
            return Math.max(1 + longestCurrentStreak(root.left), longestOverallUnivalue(root.left));
        } else if ((root.left == null) && (rightMatches(root))) {
            return Math.max(1 + longestCurrentStreak(root.right), longestOverallUnivalue(root.right));
        } else if (bothMatch(root)){
            return Math.max((2 + longestCurrentStreak(root.right) + longestCurrentStreak(root.left)),
                    Math.max(longestOverallUnivalue(root.right), longestOverallUnivalue(root.left)));
        } else {
            return Math.max(longestOverallUnivalue(root.right), longestOverallUnivalue(root.left));
        }
    }

    public static int longestCurrentStreak(TreeNode root) {
        if ((root == null) || noneMatch(root)) {
            return 0;
        } else if (root.right == null) {
            return 1 + longestCurrentStreak(root.left);
        } else if (root.left == null) {
            return 1 + longestCurrentStreak(root.right);
        } else {
            return 1 + Math.max(longestCurrentStreak(root.right), longestCurrentStreak(root.left));
        }
    }


    private static boolean isFullBranch(TreeNode root) {
        return ((root.left != null) && (root.right != null));
    }

    private static boolean noneMatch(TreeNode root) {
        if (root == null || isLeaf(root)) {
            return true;
        } else if (isFullBranch(root)) {
            return ((root.left.val != root.val) && (root.right.val != root.val));
        } else if (root.right != null) {
            return root.val != root.right.val;
        } else {
            return root.val != root.left.val;
        }
    }

    private static boolean bothMatch(TreeNode root) {
        return (rightMatches(root) && leftMatches(root));
    }

    private static boolean rightMatches(TreeNode root) {
        return ((root.right != null) && (root.right.val == root.val));
    }

    private static boolean leftMatches(TreeNode root) {
        return ((root.left != null) && (root.left.val == root.val));
    }

    private static boolean isLeaf(TreeNode root) {
        return ((root.right == null) && (root.left == null));
    }
}
