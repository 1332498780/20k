package cn.hzy.demo.question;


public class BinaryTreeTest {

    public static void main(String[] args) {
        TreeNode tn = buildTree(new int[]{10,8,3,9,12,11,13},new int[]{3,8,9,10,11,12,13});
        TreeNode.PrePrint(tn);
    }

    public static TreeNode buildTree(int[] pre,int[] mid){
        return recursion(pre,mid,0,mid.length-1);
    }
    static int preIndex = 0;
    public static TreeNode recursion(int[] pre,int[] mid,int start,int end){
        int targetIndex = indexOf(mid,start,end,pre[preIndex++]);
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

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;

    /***
     * 前序遍历TreeNode
     * @param tn
     */
    public static void PrePrint(TreeNode tn){
        if(tn==null){
            System.out.print("# ");
            return;
        }
        System.out.print(tn.val+" ");
        PrePrint(tn.left);
        PrePrint(tn.right);
    }
}
