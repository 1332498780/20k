package tree;

import java.util.LinkedList;

public class BinaryTree {
    public static void main(String[] args) {
        String[] array = new String[]{"A","B","C","D","E","F"};
        BinaryTree tree = new BinaryTree();
        Node node = new BinaryTree().build(array, 1);
        tree.beforePrint(node);
        System.out.println();
        tree.middlePrint(node);
        System.out.println();
        tree.afterPrint(node);
        System.out.println();
//        LinkedList
    }
    // 构建二叉树
    public Node build(String[] array, int index) {
        if (index > array.length) {
            return null;
        }
        return new Node(array[index-1],
                build(array, 2*index),
                build(array, 2*index+1));
    }

    // 先序遍历
    public void beforePrint(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data);
        beforePrint(root.left);
        beforePrint(root.right);
    }
    // 中序遍历
    public void middlePrint(Node root) {
        if (root == null) {
            return;
        }
        middlePrint(root.left);
        System.out.print(root.data);
        middlePrint(root.right);
    }
    // 后序遍历
    public void afterPrint(Node root) {
        if (root == null) {
            return;
        }
        afterPrint(root.left);
        afterPrint(root.right);
        System.out.print(root.data);
    }
    // 层序遍历

    private class Node {
        private String data;
        private Node left;
        private Node right;
        Node(String data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
