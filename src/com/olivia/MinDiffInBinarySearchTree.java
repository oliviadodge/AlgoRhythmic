package com.olivia;

public class MinDiffInBinarySearchTree {

    public static void main(String[] args) {
        TreeNode leaf0 = new TreeNode(5);
        TreeNode right1 = new TreeNode(5, null, leaf0);
        TreeNode leftLeft = new TreeNode(4, leaf0, null);
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leftRight = new TreeNode(4, leaf4, null);
        TreeNode right0 = new TreeNode(5, null, right1);
        TreeNode left0 = new TreeNode(4, leftLeft, leftRight);
        TreeNode root = new TreeNode(1, left0, null);
        System.out.println("number of ways is " + minDiffInBST(root));
    }

    public static int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (isLeaf(root)) {
            return 0;
        } else if (root.right == null && isLeaf(root.left)) {
            return root.val - root.left.val;
        } else if (root.left == null && isLeaf(root.right)) {
            return root.right.val - root.val;
        } else if (isFullBranch(root) && isLeaf(root.right) && isLeaf(root.left)) {
            return Math.min(closestNodeRight(root).val - root.val, root.val - closestNodeLeft(root).val);
        } else if (isFullBranch(root) && isLeaf(root.right)) {
            return Math.min(
                    Math.min(closestNodeRight(root).val - root.val, root.val - closestNodeLeft(root).val),
                    minDiffInBST(root.left));
        } else if (isFullBranch(root) && isLeaf(root.left)) {
            return Math.min(
                    Math.min(closestNodeRight(root).val - root.val, root.val - closestNodeLeft(root).val),
                    minDiffInBST(root.right));
        } else if (root.right == null) {
            return Math.min(root.val - closestNodeLeft(root).val, minDiffInBST(root.left));
        } else if (root.left == null) {
            return Math.min(closestNodeRight(root).val - root.val, minDiffInBST(root.right));
        }else {
            return Math.min(
                    Math.min(closestNodeRight(root).val - root.val, root.val - closestNodeLeft(root).val),
                    Math.min(minDiffInBST(root.right), minDiffInBST(root.left)));

        }
    }

    private static boolean isLeaf(TreeNode node) {
        return node.right == null && node.left == null;
    }

    private static boolean isFullBranch(TreeNode root) {
        return ((root.left != null) && (root.right != null));
    }

    private static TreeNode closestNodeRight(TreeNode root) {
        if (root == null) return null;

        TreeNode pointer = root.right;
        if (pointer == null) return null;

        while (pointer.left != null) {
            pointer = pointer.left;
        }

        return pointer;
    }

    private static TreeNode closestNodeLeft(TreeNode root){
        if (root == null) return null;

        TreeNode pointer = root.left;
        if (pointer == null) return null;

        while (pointer.right != null) {
            pointer = pointer.right;
        }

        return pointer;
    }

}
