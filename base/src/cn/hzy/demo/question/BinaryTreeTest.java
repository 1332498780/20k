package cn.hzy.demo.question;

import java.util.Stack;

public class BinaryTreeTest {

    public static void main(String[] args) {
        TreeNode tn = buildTree(new int[]{10,8,3,9,12,11,13},new int[]{3,8,9,10,11,12,13});
        TreeNode.prePrint(tn);
        System.out.println();
        TreeNode.midLoop(tn);
    }

    public static TreeNode buildTree(int[] pre,int[] mid){
        return recursion(pre,mid,0,mid.length-1);
    }
    static int preIndex = 0;
    public static TreeNode recursion(int[] pre,int[] mid,int start,int end){
        int targetIndex = indexOf(mid,start,end,pre[preIndex++]);
        if(targetIndex==-1){
            return null;
        }
        TreeNode tn = new TreeNode();
        tn.val = mid[targetIndex];
        if(start>=end){
            return tn;
        }
        tn.left = recursion(pre,mid,start,targetIndex-1);
        tn.right = recursion(pre,mid,targetIndex+1,end);
        return tn;
    }

    private static int indexOf(int[] mid,int start,int end,int target){
        for(int i=start;i<=end;i++){
            if(mid[i] == target){
                return i;
            }
        }
        return -1;
    }
}

/***
 * 自定义二叉树结构
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    /***
     * 前序遍历TreeNode
     * @param tn
     */
    public static void prePrint(TreeNode tn){
        if(tn==null){
            System.out.print("# ");
            return;
        }
        System.out.print(tn.val+" ");
        prePrint(tn.left);
        prePrint(tn.right);
    }

    /***
     * 循环前序遍历TreeNode
     * @return
     */
    public static void preLoop(TreeNode node){
        Stack<TreeNode> stack = new Stack();
        if(node!=null){
            stack.push(node);
        }
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            System.out.print(temp.val+" ");
            if(temp.right != null){
                stack.push(temp.right);
            }else{
                System.out.print("# ");
            }
            if(temp.left!=null){
                stack.push(temp.left);
            }else{
                System.out.print("# ");
            }
        }
    }
    /***
     * 循环中序遍历TreeNode
     *
     * 说明：(相对于循环的前序遍历，中序遍历难度提升)
     *      1：相对于前序的循环遍历，中序遍历的难点在于，需要先遍历父节点的左孩子，然后在遍历父节点，但是实际情况又是
     * ，没有父节点，哪来的的孩子。所以无论如何我们都需要先push父节点，然后pop父节点，然后push父节点，push左孩子。
     *      2： 基于1，我们遍历过孩子后还会回到父节点，此时我们应该知道，我们已经遍历过孩子了，现在应该遍历父节点，
     *  而不是继续push孩子。我们应该对于遍历过的节点有一个标识。
     * @return
     */
    public static void midLoop(TreeNode node){
        Stack<TreeNode> stack = new Stack();
        if(node!=null){
            stack.push(node);
        }
        //表示当前节点是否已经被遍历到。如果已经被遍历到，那么下次将不再被push
        boolean flag = false;
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp.left!=null && !flag){
                stack.push(temp);
                stack.push(temp.left);
            }else{
                if(temp.left == null){
                    System.out.print("# ");
                }
                System.out.print(temp.val+" ");
                flag = true;
                if(temp.right!=null){
                    stack.push(temp.right);
                    flag = false;
                }else{
                    System.out.print("# ");
                }
            }
        }
    }
}
