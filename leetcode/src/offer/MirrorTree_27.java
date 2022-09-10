package offer;

import offer.base.TreeNode;

/**
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 */
public class MirrorTree_27 {

    public static void main(String[] args) throws Exception {
        MirrorTree_27 main = new MirrorTree_27();
        main.testMirrorTree();
    }

    public void testMirrorTree() throws Exception {
        String[] preTreeNodes = {"8","6","5","7","10","9","11"};
        String[] midTreeNodes = {"5","6","7","8","9","10","11"};
        TreeNode root = TreeNode.buildTreeWithPreMidOrder(preTreeNodes, midTreeNodes);
        TreeNode.preRetrieval(root);
        TreeNode mirrorTree = mirrorTree(root);
        TreeNode.preRetrieval(mirrorTree);

    }

    public TreeNode mirrorTree(TreeNode root) {
        preRetrieval(root);
        return root;
    }

    private void preRetrieval(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        TreeNode tmp = treeNode.right;
        treeNode.right = treeNode.left;
        treeNode.left = tmp;
        preRetrieval(treeNode.right);
        preRetrieval(treeNode.left);
    }
}
