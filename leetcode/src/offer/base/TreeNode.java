package offer.base;

import java.util.Arrays;
public class TreeNode {
    public String val;
    public TreeNode leftNode;
    public TreeNode rightNode;
    public TreeNode(String val) {
        this.val = val;
    }

    public void testBuildTree() throws Exception {
        String[] preNodes = "F,B,A,D,C,E,G,I,H".split(",");
        String[] midNodes = "A,B,C,D,E,F,G,H,I".split(",");
//        String[] postNodes = "A,C,E,D,B,H,I,G,F".split(",");
        TreeNode rootNode = buildTreeWithPreOrder(preNodes, midNodes);
        postRetrieval(rootNode);
    }

    public static TreeNode buildTreeWithPreOrder(String[] preOrderNodes, String[] midOrderNodes) throws Exception {
        if (preOrderNodes == null || midOrderNodes == null || preOrderNodes.length < 1) {
            return null;
        }
        // 前序、中序节点值是否相等
        boolean allMatch = validateArrayValueEquals(preOrderNodes, midOrderNodes);
        if (!allMatch) {
            throw new Exception("two param value not equals");
        }

        // 根节点
        String rootVal = preOrderNodes[0];
        TreeNode rootNode = new TreeNode(rootVal);

        if (preOrderNodes.length == 1) {
            return rootNode;
        }

        // 中序的左右子节点的拆分 [0~rootIndex-1], [rootIndex+1~length-1]
        int rootIndexInMid = Arrays.asList(midOrderNodes).indexOf(rootVal);
        int leftEndIndexInMid = Math.max(0, rootIndexInMid - 1);
        String[] leftNodesInMid = Arrays.copyOfRange(midOrderNodes, 0, leftEndIndexInMid + 1);

        int rightStartIndexInMid = Math.min(rootIndexInMid + 1, midOrderNodes.length - 1);
        String[] rightNodesInMid = Arrays.copyOfRange(midOrderNodes, rightStartIndexInMid, midOrderNodes.length);

        // 前序左右子节点的拆分 [1~preIndex(midVal[rootIndex-1])], [preLeftEndIndex+1~length-1]
        int leftEndIndexInPre = Arrays.asList(preOrderNodes).indexOf(midOrderNodes[leftEndIndexInMid]);
        String[] leftNodesInPre = Arrays.copyOfRange(preOrderNodes, 1, leftEndIndexInPre + 1);
        String[] rightNodesInPre = Arrays.copyOfRange(preOrderNodes, leftEndIndexInPre+1, preOrderNodes.length);

        // 赋值
        rootNode.leftNode = buildTreeWithPreOrder(leftNodesInPre, leftNodesInMid);
        rootNode.rightNode = buildTreeWithPreOrder(rightNodesInPre, rightNodesInMid);

        return rootNode;
    }

    public static boolean validateArrayValueEquals(String[] preOrderNodes, String[] midOrderNodes) {
        for (int i = 0; i < preOrderNodes.length; i++) {
            boolean findFlag = false;
            for (int j = 0; j < midOrderNodes.length; j++) {
                if (preOrderNodes[i].equals(midOrderNodes[j])) {
                    findFlag = true;
                    break;
                }
            }
            if (!findFlag) {
                return false;
            }
        }
        return true;
    }

    /**
     * 前序遍历
     * 根左右
     */
    public static void preRetrieval(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        preRetrieval(node.leftNode);
        preRetrieval(node.rightNode);
    }

    /**
     * 中序遍历
     * 左根右
     */
    public static void midRetrieval(TreeNode node) {
        if (node == null) {
            return;
        }
        midRetrieval(node.leftNode);
        System.out.println(node.val);
        midRetrieval(node.rightNode);
    }

    /**
     * 后序遍历
     * 左右根
     */
    public static void postRetrieval(TreeNode node) {
        if (node == null) {
            return;
        }
        postRetrieval(node.leftNode);
        postRetrieval(node.rightNode);
        System.out.println(node.val);
    }

}
