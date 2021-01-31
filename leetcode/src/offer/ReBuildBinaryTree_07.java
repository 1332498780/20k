package offer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 根据先序和中序，假设无重复，来重建二叉树
 * 思想：
 * 递归构建左右子树
 */
public class ReBuildBinaryTree_07 {
    public static void main(String[] args) {
        int[] preOrder = new int[]{1,2,4,7,3,5,6,8};
        int[] middleOrder = new int[]{4,7,2,1,5,3,8,6};
        ReBuildBinaryTree_07 rebuild = new ReBuildBinaryTree_07();
        TreeNode head = rebuild.rebuildBinaryTree(preOrder,middleOrder);
        rebuild.prePrint(head);
        System.out.println();
        rebuild.middlePrint(head);
        System.out.println();
        rebuild.afterPrint(head);
        int[] result = Arrays.copyOfRange(middleOrder,0,middleOrder.length);
        System.out.println(result);
    }

    // 执行用时：13 ms, 在所有 Java 提交中击败了 18.27% 的用户
    // 内存消耗：88.2 MB, 在所有 Java 提交中击败了 10.15% 的用户
    public TreeNode rebuildBinaryTree(int[] preorder, int[] inorder) {
        TreeNode head = null;
        if (preorder != null && preorder.length != 0) {
            head = new TreeNode(preorder[0]);
            if (inorder != null && inorder.length != 0) {
                int rootIndex = 0;
                while (rootIndex < inorder.length && inorder[rootIndex] != preorder[0]) {
                    rootIndex++;
                }
                head.left =  rootIndex == 0 ? null : rebuildBinaryTree(
                        Arrays.copyOfRange(preorder,1, rootIndex+1),
                        Arrays.copyOfRange(inorder, 0, rootIndex));
                head.right =  rootIndex == inorder.length-1 ? null :rebuildBinaryTree(
                        Arrays.copyOfRange(preorder, rootIndex+1, preorder.length),
                        Arrays.copyOfRange(inorder, rootIndex+1, inorder.length));
            }
        }
        return head;
    }

    public void prePrint(TreeNode head) {
        if (head == null) {
            return;
        }
        System.out.print(head.val);
        prePrint(head.left);
        prePrint(head.right);
    }

    public void middlePrint(TreeNode head) {
        if (head == null) {
            return;
        }
        middlePrint(head.left);
        System.out.print(head.val);
        middlePrint(head.right);
    }

    public void afterPrint(TreeNode head) {
        if (head == null) {
            return;
        }
        afterPrint(head.left);
        afterPrint(head.right);
        System.out.print(head.val);
    }

    public static class TreeNode{
        public Integer val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(Integer val) {
            this.val = val;
        }
        public TreeNode(Integer val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
