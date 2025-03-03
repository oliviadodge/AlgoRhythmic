package com.olivia.nov2024;

import java.util.ArrayList;

public class Trees {

    static class BinaryTreeNode {
        Integer value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    public static BinaryTreeNode build_a_bst(ArrayList<Integer> values) {
        if (values.isEmpty()) return null;

        BinaryTreeNode root = new BinaryTreeNode(values.get(0));

        for (int i = 1; i < values.size(); i++) {
            int newValue = values.get(i);
            BinaryTreeNode parent = root;
            findParentForInsertion(parent, root, newValue);

            //copy root and give it to parent
            //copy parent and give it to findParent
            if (parent.value < newValue) {
                parent.right = new BinaryTreeNode(newValue);
            } else {
                parent.left = new BinaryTreeNode(newValue);
            }
        }

        return root;
    }

    static void findParentForInsertion(BinaryTreeNode parent, BinaryTreeNode pointer, int newValue) {
        if (pointer == null) {
            return;
        } else if (newValue < pointer.value) {
            parent = pointer;
            findParentForInsertion(parent, pointer.left, newValue);
        } else {
            parent = pointer;
            findParentForInsertion(parent, pointer.right, newValue);
        }
    }
}
