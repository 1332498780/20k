package offer;

import offer.base.TreeNode;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 */
public class TreeIsSubStructure_26 {

    public static void main(String[] args) throws Exception {
        TreeIsSubStructure_26 main = new TreeIsSubStructure_26();
        main.testIsSubStructure();
    }

    public void testIsSubStructure() throws Exception {
        TreeNode tree4 = new TreeNode("4");
        TreeNode tree7 = new TreeNode("7");
        TreeNode tree2 = new TreeNode("2", tree4, tree7);
        TreeNode tree9 = new TreeNode("9");
        TreeNode tree8 = new TreeNode("8", tree9, tree2);
        TreeNode tree77 = new TreeNode("7");
        TreeNode treeA = new TreeNode("8", tree8, tree77);

        TreeNode treeB9 = new TreeNode("9");
        TreeNode treeB2 = new TreeNode("2");
        TreeNode treeB = new TreeNode("8", treeB9, treeB2);
        boolean subStructure = isSubStructure2(treeA, treeB);
        System.out.println(subStructure);
    }

    public boolean isSubStructure2(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return midRetrieval(A, B);
    }

    public boolean midRetrieval(TreeNode treeA, TreeNode treeB) {
        if (isSubStructure(treeA, treeB)) {
            return true;
        }
        if (treeA.leftNode != null && midRetrieval(treeA.leftNode, treeB)) {
            return true;
        }
        if (treeA.rightNode != null && midRetrieval(treeA.rightNode, treeB)) {
            return true;
        }
        return false;
    }

    public boolean isSubStructure(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null) {
            return false;
        }
        // a与b都不为null
        if (!a.val.equals(b.val)) {
            return false;
        }
        return isSubStructure(a.leftNode,b.leftNode) && isSubStructure(a.rightNode, b.rightNode);
    }
}
