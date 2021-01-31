package offer;

/**
 * 二叉树的下一个节点
 * 思想：
 * 具体看findNextNode方法的注释
 */
public class FindBinaryTreeNextNode_08 {
    public static void main(String[] args) {
        TreeNode<String> head = new TreeNode<>("a");
        head.left = new TreeNode<>("b");
        head.right = new TreeNode<>("c");
        head.left.parent = head;
        head.right.parent = head;

        TreeNode<String> left1 = head.left;
        TreeNode<String> right1 = head.right;

        // 左子树
        left1.left = new TreeNode<>("d");
        left1.right = new TreeNode<>("e");
        left1.left.parent = left1;
        left1.right.parent = left1;

        TreeNode<String> left1right1 = left1.right;
        left1right1.left = new TreeNode<>("h");
        left1right1.right = new TreeNode<>("i");
        left1right1.left.parent = left1right1;
        left1right1.right.parent = left1right1;

        // 右子树
        right1.left = new TreeNode<>("f");
        right1.right = new TreeNode<>("g");
        right1.left.parent = right1;
        right1.right.parent = right1;

        String result = new FindBinaryTreeNextNode_08().findNextNode(head.right.right);
        System.out.println(result);
    }

    public String findNextNode(TreeNode<String> head) {
        if (head == null) {
            return  "";
        }
        // 判断是否右子树, 是->下一节点：右子树的最左子节点
        if (head.right != null) {
            TreeNode<String> t = head.right;
            while (t.left != null) {
                t = t.left;
            }
            return t.val;
        } else {
            // 判断是否是根节点，是->已是最后一个节点
            if (head.parent == null) {
                return "";
            }
            // 判断是否是左子树，是->下一节点：父节点
            if (head.parent.left == head) {
                return head.parent.val;
            } else {
                // 下一节点：第一个为左子树的父节点
                TreeNode<String> t = head.parent;
                while (t.parent != null && t.parent.left != t) {
                    t = t.parent;
                }
                if (t.parent == null) {
                    return "";
                } else {
                    return t.parent.val;
                }
            }
        }
    }

    // 分类计算
    public String findNext(TreeNode<String> head) {
        if (head == null) {
            return "";
        }
        // 父节点有孩子
        if (head.left != null || head.right != null) {
            // 有右子树, 下一节点：右子树的最左节点
            if (head.right != null) {
                TreeNode<String> t = head.right;
                while (t.left != null) {
                    t = t.left;
                }
                return t.val;
            } else { // 无右子树
                // 根节点返回
                if (head.parent == null) {
                    return "";
                } else {
                    // 父左节点
                    if (head.parent.left == head) {
                        return  head.parent.val;
                    } else { // 父右节点
                        TreeNode<String> t = head.parent;
                        while (t.parent != null && t.parent.left != t) {
                            t = t.parent;
                        }
                        if (t.parent == null) {
                            return "";
                        } else {
                            return t.parent.val;
                        }
                    }
                }
            }
        } else {  // 叶子节点
            // 左叶子节点
            if (head.parent.left == head) {
                return  head.parent.val;
            } else {  // 右叶子节点
                TreeNode<String> t = head.parent;
                while (t.parent != null && t.parent.left != t) {
                    t = t.parent;
                }
                if (t.parent == null) {
                    return "";
                } else {
                    return t.parent.val;
                }
            }

        }
    }

    public static class TreeNode<T> {
        public T val;
        public TreeNode<T> left;
        public TreeNode<T> right;
        public TreeNode<T> parent;

        public TreeNode(T val) {
            this.val = val;
        }
    }
}
