package test;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class ArrayTest {
//    public static void main(String[] args) {
//        MetaJavaQuestionSheet.TreeNode root = new MetaJavaQuestionSheet.TreeNode(1);
//        MetaJavaQuestionSheet.TreeNode left = new MetaJavaQuestionSheet.TreeNode(2);
//        MetaJavaQuestionSheet.TreeNode right = new MetaJavaQuestionSheet.TreeNode(2);
//        MetaJavaQuestionSheet.TreeNode lleft =new MetaJavaQuestionSheet.TreeNode(3);
//        MetaJavaQuestionSheet.TreeNode lright =new MetaJavaQuestionSheet.TreeNode(4);
//        MetaJavaQuestionSheet.TreeNode rleft =new MetaJavaQuestionSheet.TreeNode(4);
//        MetaJavaQuestionSheet.TreeNode rright =new MetaJavaQuestionSheet.TreeNode(3);
//        root.left = left;
//        root.right = right;
//        left.left = lleft;
//        left.right = lright;
//        right.left = rleft;
//        right.right = rright;
//        boolean treeSymmetric = isTreeSymmetric(root);
//        System.out.println("treeSymmetric = " + treeSymmetric);
//    }

//    public static boolean isTreeSymmetric(MetaJavaQuestionSheet.TreeNode root) {
//        //TODO your code goes here...
//        if (root == null) {
//            return false;
//        }
//        return handle(root.left, root.right);
//    }
//    public static boolean handle(MetaJavaQuestionSheet.TreeNode left, MetaJavaQuestionSheet.TreeNode right) {
//        if (left != null && right != null) {
//            if (left.val != right.val) {
//                return false;
//            }
//            if (handle(left.left, right.right) == false) {
//                return false;
//            }
//            if (handle(left.right, right.left) == false) {
//                return false;
//            }
//        } else if (left != right) {
//            return false;
//        }
//        return true;
//    }
}
