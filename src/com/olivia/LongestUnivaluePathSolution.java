package com.olivia;

public class LongestUnivaluePathSolution {

    public static void main(String[] args) {
        System.out.println("answer is " + longestUnivaluePath(new TreeNode(5)));
    }

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        int left = arrowLength(root.left);
        int right = arrowLength(root.right);
        int arrowLeft = 0, arrowRight = 0;
        if (root.left != null && root.left.val == root.val) {
            arrowLeft += left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            arrowRight += right + 1;
        }
        return Math.max(Math.max(longestUnivaluePath(root.right), longestUnivaluePath(root.left)), arrowLeft + arrowRight);
    }

    private static int arrowLength(TreeNode root) {
        if (root == null) return 0;
        int left = arrowLength(root.left);
        int right = arrowLength(root.right);
        int arrowLeft = 0, arrowRight = 0;
        if (root.left != null && root.left.val == root.val) {
            arrowLeft += left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            arrowRight += right + 1;
        }
        return Math.max(arrowLeft, arrowRight);
    }
}
