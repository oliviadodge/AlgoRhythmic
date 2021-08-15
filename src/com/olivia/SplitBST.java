package com.olivia;

import java.util.Arrays;

public class SplitBST {

    public static void main(String[] args) {
        SplitBST splitBST = new SplitBST();

        TreeNode leaf0 = new TreeNode(5);
        TreeNode right1 = new TreeNode(5, null, leaf0);
        TreeNode leftLeft = new TreeNode(4, leaf0, null);
        TreeNode leaf4 = new TreeNode(4);
        TreeNode leftRight = new TreeNode(4, leaf4, null);
        TreeNode right0 = new TreeNode(5, null, right1);
        TreeNode left0 = new TreeNode(4, leftLeft, leftRight);
        TreeNode root = new TreeNode(1, left0, null);
//        System.out.println("number of ways is " + Arrays.toString(splitBST.splitBST(root, 4)));
    }

//    public TreeNode[] splitBST(TreeNode root, int V) {
//        //Step 1: find node with value equal to V
//        //Step 2: Smaller/equal to tree is the left child tree of the node with value V
//        //Step 3: Move reference of right child tree of node of value V to parent of the same
//        TreeNode parentOfClosestNode = parentOfClosestNode(root, V);
//        if (parentOfClosestNode.val == V) {
//            TreeNode[] subtrees = new TreeNode[2];
//            subtrees[0] = parentOfClosestNode;
//            subtrees[1] = parentOfClosestNode.right;
//            subtrees[0].right = null;
//        } else if (V > parentOfClosestNode.val) { //
//
//        } else if (parentOfClosestNode.left.val == V) {
//
//        }
//    }
//
//    private TreeNode parentOfClosestNode(TreeNode root, int V) {
//
//    }
}
