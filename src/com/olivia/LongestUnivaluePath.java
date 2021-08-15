package com.olivia;

public class LongestUnivaluePath {

    public static void main(String[] args) {
        TreeNode leaf0 = new TreeNode(5);
        TreeNode right1 = new TreeNode(5, null, leaf0);
        TreeNode leftLeft = new TreeNode(4, leaf0, null);
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leftRight = new TreeNode(4, leaf4, null);
        TreeNode right0 = new TreeNode(5, null, right1);
        TreeNode left0 = new TreeNode(4, leftLeft, leftRight);
        TreeNode root = new TreeNode(1, left0, null);
        System.out.println("length of longest path: " + longestOverall(root));
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int longestUnivaluePath(TreeNode root, int max) {
        if (root == null) {
            return 0;
        } else if (nodeIsLeaf(root)) {
            return 0;
        } else if ((isFullBranch(root) && (root.val == root.right.val) && (root.val == root.left.val)
                && nodeIsLeaf(root.right) && nodeIsLeaf(root.left))) {
            return 2;
        } else if (hasRightLeaf(root) && (root.val == root.right.val)
                && nodeIsLeaf(root.right)) {
            return 1;
        } else if ((root.val == root.left.val)
                && nodeIsLeaf(root.left)) {
            return 1;
        } else if (isFullBranch(root) && (root.val == root.right.val) && (root.val == root.left.val)) {
            return 2 + longestUnivaluePath(root.right, max) + longestUnivaluePath(root.left, max);
        } else if ((hasRightLeaf(root) && (root.val == root.right.val))) {
            return 1 + longestUnivaluePath(root.right, max) + longestUnivaluePath(root.left, max);
        } else if ((hasRightLeaf(root) && (root.val == root.left.val))) {
            return 1 + longestUnivaluePath(root.left, max) + longestUnivaluePath(root.left, max);
        } else if (longestUnivaluePath(root.right, max) > max) {
            return longestUnivaluePath(root.right, max);
        } else if (longestUnivaluePath(root.left, max) > max) {
            return longestUnivaluePath(root.left, max);
        } else {
            return max;
        }
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (nodeIsLeaf(root)) {
            return 0;
        } else if ((isFullBranch(root) && (root.val == root.right.val) && (root.val == root.left.val)
                && nodeIsLeaf(root.right) && nodeIsLeaf(root.left))) {
            return 2;
        } else if (hasRightLeaf(root) && (root.val == root.right.val)
                && nodeIsLeaf(root.right)) {
            return 1;
        } else if ((root.val == root.left.val)
                && nodeIsLeaf(root.left)) {
            return 1;
        }

        return Math.max(longestUnivaluePath(root.left), longestUnivaluePath(root.right));
    }

    public static int countAllEdgesInTree(TreeNode root) {
        if (nodeIsLeaf(root)) {
            return 0;
        } else if (isFullBranch(root)) {
            return 2 + countAllEdgesInTree(root.right) + countAllEdgesInTree(root.left);
        } else if (root.right != null) {
            return 1 + countAllEdgesInTree(root.right);
        } else {
            return 1 + countAllEdgesInTree(root.left);
        }
    }

    public static int longestUnivaluePath2(TreeNode root, int runningTotal, int max) {
        if (nodeIsLeaf(root)) {
            return 0;
        } else if (isFullBranch(root) && (root.val == root.right.val) && (root.val == root.left.val)) {
            runningTotal = 2 + longestUnivaluePath2(root.right, runningTotal, max) + longestUnivaluePath2(root.left, runningTotal, max);
        } else if (isFullBranch(root) && (root.val == root.right.val)) {
            runningTotal = 1 + longestUnivaluePath2(root.right, runningTotal, max);
        } else if (isFullBranch(root) && (root.val == root.left.val)) {
            runningTotal = 1 + longestUnivaluePath2(root.left, runningTotal, max);
        } else if (isFullBranch(root)) {
            if (runningTotal > max) {
                max = runningTotal;
            }
            runningTotal = 0; //chain is broken
            return Math.max(longestUnivaluePath2(root.right, runningTotal, max), longestUnivaluePath2(root.left, runningTotal, max));
        } else if ((root.right != null) && (root.val == root.right.val)) {
            runningTotal = 1 + longestUnivaluePath2(root.right, runningTotal, max);
        } else if (root.right != null) {
            if (runningTotal > max) {
                max = runningTotal;
            }
            runningTotal = 0; //chain is broken, reset runningTotal
            return Math.max(longestUnivaluePath2(root.right, runningTotal, max), longestUnivaluePath2(root.left, runningTotal, max));
        } else if (root.val == root.left.val) {
            runningTotal = 1 + longestUnivaluePath2(root.left, runningTotal, max);
        } else {
            if (runningTotal > max) {
                max = runningTotal;
            }
            runningTotal = 0; //chain is broken, reset runningTotal
            return Math.max(longestUnivaluePath2(root.right, runningTotal, max), longestUnivaluePath2(root.left, runningTotal, max));
        }
        return Math.max(runningTotal, max);
    }

    private static boolean nodeIsLeaf(TreeNode node) {
        return (node.right == null) && (node.left == null);
    }

    private static boolean isFullBranch(TreeNode node) {
        return (node.right != null) && (node.left != null);
    }

    private static boolean hasRightLeaf(TreeNode node) {
        return node.right != null;
    }

    private static boolean hasLeftLeaf(TreeNode node) {
        return node.left != null;
    }

    public static int lengthOfRight(TreeNode root) {
        if ((root == null) || (root.right == null)) {
            return 0;
        } else {
            return 1 + lengthOfRight(root.right);
        }
    }

    public static int longestLeg(TreeNode root) {
        if ((root == null) || ((root.right == null) && (root.left == null))) {
            return 0;
        } else if (root.right == null) {
            return 1 + longestLeg(root.left);
        } else if (root.left == null) {
            return 1 + longestLeg(root.right);
        } else {
            return 1 + Math.max(longestLeg(root.right), longestLeg(root.left));
        }
    }

    public static int longestPath(TreeNode root, boolean isRoot) {
        if ((root == null) || ((root.right == null) && (root.left == null))) {
            return 0;
        } else if (root.right == null) {
            return 1 + longestPath(root.left, false);
        } else if (root.left == null) {
            return 1 + longestPath(root.right, false);
        } else if (isRoot) {
            return 2 + longestPath(root.right, false) + longestPath(root.left, false);
        } else {
            return 1 + Math.max(longestPath(root.right, false), longestPath(root.left, false));
        }
    }

    public static int longestOverall(TreeNode root) {
        if ((root == null) || ((root.right == null) && (root.left == null))) {
            return 0;
        } else if (root.right == null) {
            return Math.max(1 + longestLeg(root.left), longestOverall(root.left));
        } else if (root.left == null) {
            return Math.max(1 + longestLeg(root.right), longestOverall(root.right));
        } else {
            return Math.max(2 + longestLeg(root.right) + longestLeg(root.left),
                    Math.max(longestOverall(root.right), longestOverall(root.left)));
        }
    }

    public static int longestPath3(TreeNode root, int max) {
        if ((root == null) || ((root.right == null) && (root.left == null))) {
            return Math.max(0, max);
        } else if (root.right == null) {
            return Math.max(1 + longestPath3(root.left, max), max);
        } else if (root.left == null) {
            return Math.max(1 + longestPath3(root.right, max), max);
        } else {
            return Math.max(2 + longestPath3(root.right, max) + longestPath(root.left, false), max);
        }
    }
//
//    public static int longestPathOverall(com.olivia.TreeNode root) {
//        int max = 0;
//        if (isFullBranch(root)) {
//            com.olivia.TreeNode pointer = root;
//            while (isFullBranch(pointer)) {
//                int nextLongest = longestPath(pointer, true);
//                if (nextLongest > max) {
//                    max = nextLongest;
//                }
//                pointer
//            }
//
//        } else {
//            max = longestPath(root, true);
//        }
//        return max;
//    }
//
//    public static int longestPathOverall(com.olivia.TreeNode root, int max) {
//        if ((root == null) || ((root.right == null) && (root.left == null))) {
//            return 0;
//        } else if (root.right == null) {
//            return 1 + longestPathOverall(root.left, max);
//        } else if (root.left == null) {
//            return 1 + longestPathOverall(root.right, max);
//        } else {
//            int runningTotal;
//            int newMax = 2 + longestPathOverall(root.right, max) + longestPathOverall(root.left, max);
//            if (newMax > max) {
//                max = newMax;
//                runningTotal = 1 + Math.max(longestPathOverall(root.right, newMax), longestPathOverall(root.left, newMax));
//            } else {
//                runningTotal = 1 + Math.max(longestPathOverall(root.right, max), longestPathOverall(root.left, max));
//            }
//        }
//    }

//    public static int tryAgain(com.olivia.TreeNode root, int max) {
//        if ((root == null) || ((root.right == null) && (root.left == null))) {
//            return 0;
//        } else if (root.right == null) {
//            return 1 + longestPath(root.left, false);
//        } else if (root.left == null) {
//            return 1 + longestPath(root.right, false);
//        } else {
//            int newMax = longestPath(root, true);
//            if (newMax > max)
//                return 1 + Math.max(longestPathOverall(root.right, newMax), longestPathOverall(root.left, newMax));
//            else
//                return 1 + Math.max(longestPathOverall(root.right, max), longestPathOverall(root.left, max));
//        }
//    }

//    public static int longestPath2(com.olivia.TreeNode root, int max) {
//        if ((root == null) || ((root.right == null) && (root.left == null))) {
//            return 0;
//        } else if (root.right == null) {
//            return 1 + longestPath2(root.left, max);
//        } else if (root.left == null) {
//            return 1 + longestPath2(root.right, max);
//        } else {
//            max = Math.max((2 + longestLeg(root.right) + longestLeg(root.left)), longestPath2(root.right, max), longestPath2(root.left, max));
//        }
//    }
}
