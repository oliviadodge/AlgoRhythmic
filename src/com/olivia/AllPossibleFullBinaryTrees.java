package com.olivia;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTrees {

    public static void main(String[] args) {
        System.out.println("number of ways is ");
    }

    public List<TreeNode> allPossibleFBT(int N) {

        if (N == 1) {
            return List.of(new TreeNode(0));
        } else if (N == 3) {
            return List.of(new TreeNode(0, new TreeNode(0), new TreeNode(0)));
        } else {
            List<TreeNode> finalList = new ArrayList<>();
            List<TreeNode> oneLevelDown = allPossibleFBT(N - 2);
            if (oneLevelDown.size() == 1) { // n = 5
                TreeNode root1 = new TreeNode(0, oneLevelDown.get(0), new TreeNode(0));
                TreeNode root2 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(0));
                return List.of(root1, root2);
            }
            if (oneLevelDown.size() == 2) { // n = 7
                TreeNode root1 = new TreeNode(0, oneLevelDown.get(0), new TreeNode(0));
                TreeNode root2 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(0));
                TreeNode root3 = new TreeNode(0, oneLevelDown.get(1), new TreeNode(0));
                TreeNode root4 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(1));

                List<TreeNode> twoLevelDown1 = allPossibleFBT(N - 4);
                List<TreeNode> twoLevelDown2 = allPossibleFBT(N - 4);
                TreeNode root5 = new TreeNode(0, twoLevelDown1.get(0), twoLevelDown2.get(0));

                return List.of(root1, root2, root3, root4, root5);
            }
            if (oneLevelDown.size() == 5) { // N = 9
                TreeNode root1 = new TreeNode(0, oneLevelDown.get(0), new TreeNode(0));
                TreeNode root2 = new TreeNode(0, oneLevelDown.get(1), new TreeNode(0));
                TreeNode root3 = new TreeNode(0, oneLevelDown.get(2), new TreeNode(0));
                TreeNode root4 = new TreeNode(0, oneLevelDown.get(3), new TreeNode(0));
                TreeNode root5 = new TreeNode(0, oneLevelDown.get(4), new TreeNode(0));

                TreeNode root6 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(0));
                TreeNode root7 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(1));
                TreeNode root8 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(2));
                TreeNode root9 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(3));
                TreeNode root10 = new TreeNode(0, new TreeNode(0), oneLevelDown.get(4));

                //two level down size = 2
                List<TreeNode> twoLevelDown1 = allPossibleFBT(N - 4);
                List<TreeNode> twoLevelDown2 = allPossibleFBT(N - 4);

                TreeNode root11 = new TreeNode(0, twoLevelDown1.get(0), twoLevelDown2.get(0));
                TreeNode root12 = new TreeNode(0, twoLevelDown1.get(1), twoLevelDown2.get(1));
                TreeNode root13 = new TreeNode(0, twoLevelDown1.get(0), twoLevelDown2.get(1));
                TreeNode root14 = new TreeNode(0, twoLevelDown1.get(1), twoLevelDown2.get(0));

                //three level down size = 1
                List<TreeNode> threeLevelDown1 = allPossibleFBT(N - 6);
                List<TreeNode> threeLevelDown2 = allPossibleFBT(N - 6);

                TreeNode root15 = new TreeNode(0, threeLevelDown1.get(0), threeLevelDown2.get(0));

                return List.of(root1, root2, root3, root4, root5, root6, root7, root8, root9, root10, root11, root12, root13, root14, root15);
            }
        }
        return null;
    }

//    public List<TreeNode> addTwoMore(int N) {
//        if (N == 1) {
//            return List.of(new TreeNode(0));
//        } else if (N == 3) {
//            return List.of(new TreeNode(0, new TreeNode(0), new TreeNode(0)));
//        } else {
//            List<TreeNode> currentLevel = new ArrayList<>();
//            List<TreeNode> oneLevelDown = addTwoMore(N - 2);
//            for (TreeNode root : oneLevelDown) {
//            }
//        }
//    }


        private boolean checkIfDuplicate(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 == null) {
            return false;
        } else if (root1 == null) {
            return false;
        } else {
            return checkIfDuplicate(root1.right, root2.right) && checkIfDuplicate(root1.left, root2.left);
        }
    }
}
