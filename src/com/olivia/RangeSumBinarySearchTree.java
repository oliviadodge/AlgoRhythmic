package com.olivia;

public class RangeSumBinarySearchTree {

    public static void main(String[] args) {
        TreeNode leaf0 = new TreeNode(5);
        TreeNode right1 = new TreeNode(5, null, leaf0);
        TreeNode leftLeft = new TreeNode(4, leaf0, null);
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leftRight = new TreeNode(4, leaf4, null);
        TreeNode right0 = new TreeNode(5, null, right1);
        TreeNode left0 = new TreeNode(4, leftLeft, leftRight);
        TreeNode root = new TreeNode(1, left0, null);
        System.out.println("number of ways is " + rangeSumBST(root, 3, 4));
    }

    private static int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        } else if (root.val >= L && root.val <= R) {
            return root.val + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
        } else {
            return rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
        }
    }
}
