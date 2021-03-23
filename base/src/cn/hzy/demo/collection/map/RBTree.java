package cn.hzy.demo.collection.map;


import java.util.*;

public class RBTree<E> {

    Node root;
    int size = 0;

    static class Node<E> implements Comparable<E>{
        E data;
        Node left;
        Node right;
        Node parent;
        Color color;

        public Node(){
            //默认红色
            this.color = Color.Red;
        }

        @Override
        public int compareTo(E o) {
            if(o instanceof Comparable){
                Comparable c = (Comparable) o;
                return -c.compareTo(data);
            }
            throw new ClassCastException();
        }
    }

    enum Color{
        Red,Black
    }

    public int size(){
        return size;
    }

    @Deprecated
    private int layerCount(){
        if(size == 0){
            return 0;
        }
        double res = Math.log(size+1)/Math.log(2);
        String str = Double.toString(res);
        int layer = Integer.valueOf(str.substring(0,str.indexOf('.')));
        return (size+1)%2 == 0 ? layer : layer+1;
    }

    public void add(E a){
        Node node = new Node();
        node.data = a;
        if(root == null){
            root =  node;
            root.color = Color.Black;
            return;
        }
        Node tmp = root;
        boolean isLeft = false;
        while(tmp != null){
            if(tmp.compareTo(a) == -1){
                if(tmp.right==null){
                   tmp.right = node;
                    node.parent = tmp;
                   isLeft = false;
                   break;
                }else{
                    tmp = tmp.right;
                }
            }else{
                if(tmp.left==null){
                    tmp.left = node;
                    node.parent = tmp;
                    isLeft = true;
                    break;
                }else{
                    tmp = tmp.left;
                }
            }
        }
        //调整位置
        if(tmp == null){
            throw new NullPointerException();
        }

        size++;

        if(tmp.color == Color.Red){ //父节点是红
            if(tmp.parent.left == tmp) { //父节点是左孩子
                Node uncle = tmp.parent.right;
                if (uncle == null || uncle.color == Color.Black) {
                    if(!isLeft){
                        tmp = leftTransfer(tmp);
                    }
                    rightRotate(tmp.parent);
                    addRightChangeColor(tmp);
                } else {
                    //叔叔节点是红色
                    modifyColor(node);
                }
            }else {
                Node uncle = tmp.parent.left;
                if (uncle == null || uncle.color == Color.Black) {
                    if(isLeft){
                        tmp = rightTransfer(tmp);
                    }
                    leftRotate(tmp.parent);
                    addLeftChangeColor(tmp);
                } else {
                    //叔叔节点是红色
                    modifyColor(node);
                }
            }
        }
    }

    public void remove(E e){
        if(root == null){
            throw new NullPointerException();
        }
        Node tmp = root;
        boolean isLeft = false;
        while(tmp!=null){
            if(tmp.compareTo(e) == 0){
                break;
            }else{
                if(tmp.compareTo(e) == -1){
                    tmp = tmp.right;
                    isLeft = false;
                }else{
                    tmp = tmp.left;
                    isLeft = true;
                }
            }
        }
        if(tmp==null){
            throw new NoSuchElementException();
        }
        size--;
        if(tmp == root){
            Node succeed = getSucceed(tmp);
            if(succeed==null){
                if(tmp.left == null){
                    root = null;
                }else{
                    root = tmp.left;
                    tmp.left.parent = null;
                    tmp.left = null;
                    root.color = Color.Black;
                }
            }else {
                root.data = succeed.data;
                if(succeed.color == Color.Red){
                    //后继节点可能是红左子
                    if(succeed.parent == root){
                        succeed.parent.right = null;
                    }else{
                        succeed.parent.left = null;
                    }
                    succeed.parent = null;
                }else{
                    //只有一种情况，且黑色的叶子不可能没有兄弟
                    change(succeed);
                    deleteNode(succeed);
                }
            }
        }else {
            if (tmp.color == Color.Black) {
                if (tmp.left == null && tmp.right == null) { //叶子
                    //必然有兄弟
                    change(tmp);
                    deleteNode(tmp);
                }else{
                    Node succeed = getSucceed(tmp);
                    if(succeed == null){
                        if(isLeft){
                            //只有一种情况，有一个左红
                            tmp.parent.left = tmp.left;
                        }else{
                            tmp.parent.right = tmp.left;
                        }
                        tmp.left.parent = tmp.parent;
                        tmp.left.color = Color.Black;

                        tmp.parent = null;
                        tmp.left = null;
                    }else{
                        if(succeed.color == Color.Red){
                            tmp.data = succeed.data;
                            if(succeed.parent.right == succeed){
                                succeed.parent.right = null;
                            }else{
                                succeed.parent.left = null;
                            }
                            succeed.parent = null;
                        }else{
                            change(succeed);
                            deleteNode(succeed);
                        }
                    }
                }
            } else {
                if (tmp.left == null && tmp.right == null) { //叶子
                    if (isLeft) {
                        tmp.parent.left = null;
                    } else {
                        tmp.parent.right = null;
                    }
                    tmp.parent = null;
                } else {
                    Node succeed = getSucceed(tmp);
                    tmp.data = succeed.data;
                    if (succeed.color == Color.Red) {
                        //只有一种情况，就是后继节点是红左子
                        //直接删除
                        succeed.parent.left = null;
                        succeed.parent = null;
                    } else {
                        //只有一种情况，且黑色的叶子不可能没有兄弟
                        change(succeed);
                        deleteNode(succeed);
                    }
                }
            }
        }
    }

    // node节点是黑，node有兄弟节点
    private void change(Node node){
        if(node == root){
            return;
        }
        boolean isLeft = node == node.parent.left;
        Node parent = node.parent;
        if(isLeft){
            Node brother = parent.right;
            if(brother.color == Color.Black){
                if(
                        (brother.left == null && brother.right == null)
                        ||
                        (brother.left!=null &&
                                brother.right!=null &&
                                brother.left.color == Color.Black &&
                                brother.right.color == Color.Black)
                ){  //孩子也是黑
                    if(parent.color == Color.Red){
                        //交换父亲和兄弟的颜色
                        exchangeColor(brother,parent);
                    }else{
                        brother.color = Color.Red;
                        change(parent);
                    }
                }else if(brother.right != null && brother.right.color == Color.Red){
                    //parent左旋
                    leftRotate(parent);
                    //交换原brother与parent颜色
                    exchangeColor(brother,parent);
                    //涂黑红的子树
                    brother.right.color = Color.Black;
                }else if(brother.left != null && brother.left.color == Color.Red){
                    //旋转兄弟
                    Node newBrother = rightTransfer(brother);
                    //交换原brother与brother右孩子(原brother)的颜色
                    exchangeColor(newBrother,newBrother.right);
                    //回到右黑兄弟有个右红孩子情况
                    change(node);
                }
            }else{
                //旋转兄弟
                Node newBrother = rightTransfer(brother);
                //交换原brother与brother右孩子(原brother)的颜色
                exchangeColor(newBrother,newBrother.right);
                //回到右黑兄弟有个右红孩子情况
                change(node);
            }
        }else{
            Node brother = parent.left;
            if(brother.color == Color.Black){
                if(
                        (brother.left == null && brother.right == null)
                                ||
                                (brother.left!=null &&
                                        brother.right!=null &&
                                        brother.left.color == Color.Black &&
                                        brother.right.color == Color.Black)
                ){  //孩子也是黑
                    if(parent.color == Color.Red){
                        //交换父亲和兄弟的颜色
                        exchangeColor(brother,parent);
                    }else{
                        brother.color = Color.Red;
                        change(parent);
                    }
                }else if(brother.left != null && brother.left.color == Color.Red){
                    //parent左旋
                    rightRotate(parent);
                    //交换原brother与parent颜色
                    exchangeColor(brother,parent);
                    //涂黑红的子树
                    brother.left.color = Color.Black;
                }else if(brother.right != null && brother.right.color == Color.Red){
                    //旋转兄弟
                    Node newBrother = leftTransfer(brother);
                    //交换原brother与brother右孩子(原brother)的颜色
                    exchangeColor(newBrother,newBrother.left);
                    //回到右黑兄弟有个右红孩子情况
                    change(node);
                }
            }else{
                //旋转兄弟
                Node newBrother = leftTransfer(brother);
                //交换原brother与brother右孩子(原brother)的颜色
                exchangeColor(newBrother,newBrother.left);
                //回到右黑兄弟有个右红孩子情况
                change(node);
            }
        }
    }

    private void deleteNode(Node node){
        boolean isLeft = node.parent.left == node;
        if(isLeft){
            node.parent.left = null;
        }else{
            node.parent.right = null;
        }
        node.parent = null;
    }

    private void exchangeColor(Node node1,Node node2){
        Color tmp = node1.color;
        node1.color = node2.color;
        node2.color = tmp;
    }
    private Node getSucceed(Node node){
        if(node!=null && node.right!=null){
            Node tmp = node.right;
            while(tmp.left!=null){
                tmp = tmp.left;
            }
            return tmp;
        }
        return null;
    }

    private void modifyColor(Node node){
        Node parent = node.parent;
        if(parent == null || (parent.color == Color.Black)){
            return;
        }
        Node grandap = parent.parent;
        boolean isLeft = grandap.left == parent ? true : false;
        if(isLeft){
            Node uncle = grandap.right;
            if(uncle == null || uncle.color == Color.Black){
                rightRotate(grandap);
                addRightChangeColor(parent);
            }else {
                transferColor(parent,uncle);
            }
        }else{
            Node uncle = grandap.left;
            if(uncle == null || uncle.color == Color.Black){
                leftRotate(grandap);
                addLeftChangeColor(parent);
            }else {
                transferColor(parent,uncle);
            }
        }
        modifyColor(grandap);

//        Node grandap = node.parent.parent;
//        if(grandap != null && grandap.color == Color.Red){
//            if(isLeft){
//                rightRotate(grandap);
//            }else{
//                leftRotate(grandap);
//            }
//        }
    }
    private void transferColor(Node node,Node uncle){
        if(node.parent != root){
            node.parent.color = Color.Red;
        }
        node.color = Color.Black;
        uncle.color = Color.Black;
    }
    private void addRightChangeColor(Node node){
        //改色
        node.right.color = Color.Red;
        node.color = Color.Black;
    }
    private Node leftRotate(Node node){
        Node rChild = node.right;
        rotate(node,rChild);
        if(rChild.left!=null){
            rChild.left.parent = node;
        }
        node.right = rChild.left;
        rChild.left = node;
        node.parent = rChild;
        return rChild;
    }
    private Node rightRotate(Node node){
        Node lChild = node.left;
        rotate(node,lChild);
        if(lChild.right!=null){
            lChild.right.parent = node;
        }
        node.left = lChild.right;
        lChild.right = node;
        node.parent = lChild;
        return lChild;
    }
    private void rotate(Node node,Node newParent){
        if(node == root){
            root = newParent;
            root.parent = null;
        }else{
            Node parent = node.parent;
            if(node == parent.left){
                parent.left = newParent;
            }else{
                parent.right = newParent;
            }
            newParent.parent = parent;
        }
    }

    private void addLeftChangeColor(Node node){
        //改色
        node.left.color = Color.Red;
        node.color = Color.Black;
    }
    private Node leftTransfer(Node node){
        Node parent = node.parent;
        Node rChild = node.right;
        parent.left = rChild;
        rChild.parent = parent;
        node.parent = rChild;
        rChild.left = node;
        node.right = null;
        return rChild;
    }
    private Node rightTransfer(Node node){
        Node parent = node.parent;
        Node lChild = node.left;
        parent.right = lChild;
        lChild.parent = parent;
        node.parent = lChild;
        lChild.right = node;
        node.left = null;
        return lChild;
    }
    public void printPre(Node node){
        if(node==null){
            System.out.print("# ");
            return;
        }
        String color = node.color == Color.Black ? "b " : "r ";
        System.out.print(node.data+color);
        printPre(node.left);
        printPre(node.right);
    }
    public void printLayer(){
        class Queue<E>{
            Nd head ;
            Nd tail = head;
            class Nd<E>{
                E data;
                Nd next;
            }
            void push(E e){
                Nd<E> node = new Nd();
                node.data = e;
                if(head==null){
                    head = node;
                    tail = node;
                }else{
                    tail.next =  node;
                    tail = tail.next;
                }
            }
            E pop(){
                if(head == null){
                    return null;
                }else{
                    Nd<E> node = head;
                    head = head.next;
                    node.next = null;
                    return node.data;
                }
            }
            boolean isEmpty(){
                return head == null;
            }
        }
        Queue<Node> queue = new Queue();
        queue.push(root);
        while(!queue.isEmpty()){
            StringBuilder sb= new StringBuilder();
            List<Node> list = new ArrayList();
            while(!queue.isEmpty()){
                Node node = queue.pop();
                String colorStr = node.color == Color.Black ? blak(node.data.toString()) : red(node.data.toString());
                sb.append(colorStr).append(" ");
                list.add(node);
            }
            System.out.println(sb.toString());
            Iterator<Node> iterator = list.iterator();
            while(iterator.hasNext()){
                Node n = iterator.next();
                if(n.left!=null){
                    queue.push(n.left);
                }
                if(n.right!=null){
                    queue.push(n.right);
                }
            }
        }
    }
    public void constructTree(){
//        Scanner scanner = new Scanner(System.in);
//        int val = 0;
//        while ((val = scanner.nextInt())!=0){
//            add((E) Integer.valueOf(val));
//        }
//        int bound = 33;
//        Random random = new Random();
//        StringBuilder sb = new StringBuilder();
//        for(int i=9;i>=7;i--){
//            sb.append(i).append(",");
//            add((E) Integer.valueOf(i));
//        }

        int[] ins = new int[]{9,8,10,7,11,12,13,14,15,16,17,18};
        for(int i:ins){
            add((E) Integer.valueOf(i));
        }

//        printLayer();
//        System.out.println(sb.toString());
    }

    private String red(String str){
        return "\033[31;1;m"+str;
    }
    private String blak(String str){
        return "\033[1;97m"+str;
    }
    public static void main(String[] args){
//        RBTree<Integer> rbTree = new RBTree();
//        rbTree.constructTree();
//        rbTree.remove(18);
//        rbTree.remove(14);
//        rbTree.remove(15);
//        rbTree.remove(17);
//        rbTree.remove(12);
//        rbTree.printLayer();
//        Map map = new HashMap();

        int i = 1;
        i >>>= 4;
        System.out.println(i);
//        System.out.println(Float.isNaN(f));
    }
}
