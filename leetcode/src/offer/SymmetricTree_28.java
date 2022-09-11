package offer;

import offer.base.TreeNode;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 */
public class SymmetricTree_28 {

    public static void main(String[] args) throws Exception {
        SymmetricTree_28 main = new SymmetricTree_28();
        main.testIsSymmetric();
//        main.testNotSymmetric();
    }

    public void testIsSymmetric() throws Exception {
        // 对称树
        String[] preNodes = {"8","6","5","7","6","7","5"};
        String[] midNodes = {"5","6","7","8","7","6","5"};
        TreeNode symmetricTree = TreeNode.buildTreeWithPreMidOrder(preNodes, midNodes);
        TreeNode.preRetrieval(symmetricTree);
        System.out.println(isSymmetric(symmetricTree));
    }

    public void testNotSymmetric() throws Exception {
        String[] preNodes = {"8","6","5","7","9","7","5"};
        String[] midNodes = {"5","6","7","8","7","9","5"};
        TreeNode notSymmetricTree = TreeNode.buildTreeWithPreMidOrder(preNodes, midNodes);
        TreeNode.preRetrieval(notSymmetricTree);
        System.out.println(isSymmetric(notSymmetricTree));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricTree(root, root);
    }

    private boolean isSymmetricTree(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left != null && right != null) {
            return (left.val == right.val) && isSymmetricTree(left.left, right.right) && isSymmetricTree(left.right, right.left);
        }
        return false;
    }
}
