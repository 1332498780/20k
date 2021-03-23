package cn.hzy.demo.collection.map;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class MyTree<E> {

    private Node root;

    /**
     * 插入
     * @param e
     */
    public void add(E e){
        Node node = new Node();
        node.data = e;
        if(root== null){
            root = node;
        }else{
            ite(root,node);
        }
    }

    /**
     * 递归在以tmp为根节点
     * @param tmp
     * @param node
     */
    public void ite(Node tmp,Node node){
        if(node.compareTo(tmp) <= 0){
            if(tmp.left != null){
                ite(tmp.left,node);
            }else{
                tmp.left = node;
            }
        }else{
            if(tmp.right != null){
                ite(tmp.right,node);
            }else{
                tmp.right = node;
            }
        }
    }


    /**
     * 删除指定值
     * @param e
     * @return
     */
    public boolean remove(E e){
        Node currentNode = root;
        Node parentNode = null;
        if(currentNode == null){
            throw new NoSuchElementException();
        }
        if(e == null){
            throw new NullPointerException();
        }
        boolean isLeftChild = false;
        int tmp = -1;
        while (currentNode != null && (tmp=currentNode.compareTo(e)) != 0){
            parentNode = currentNode;
            if(tmp == -1){
                currentNode = currentNode.right;
                isLeftChild = false;
            }else{
                currentNode = currentNode.left;
                isLeftChild = true;
            }
            if(currentNode == null){
                throw new NoSuchElementException();
            }
        }
        if(currentNode.left == null && currentNode.right == null){
            if(isLeftChild){
                parentNode.left = null;
            }else{
                parentNode.right = null;
            }
        }else if(currentNode.left == null){
            if(isLeftChild){
                parentNode.left = currentNode.right;
            }else {
                parentNode.right = currentNode.right;
            }
        }else if(currentNode.right == null){
            if(isLeftChild){
                parentNode.left = currentNode.left;
            }else {
                parentNode.right = currentNode.left;
            }
        }else{
            //左右子树都有值，可以找前驱也可以找后继
            Node preCursor = getPrecursor(currentNode);
            if(parentNode != null){
                if(isLeftChild){
                    parentNode.left = preCursor;
                    currentNode.left = null;
                    currentNode.right = null;
                }else {
                    parentNode.right = preCursor;
                    currentNode.left = null;
                    currentNode.right = null;
                }
            }else {
                root = preCursor;
            }
        }
        return true;
    }

    /**
     * 根据给定的字符串构造二叉树，字符串是前序
     */
    static int index = -1;
    private static <E> Node<E> generateTree(String str){
        index++;
        if(index>=str.length() || str.charAt(index) == '#'){
            return null;
        }
        Node node = new Node();
        node.data = Integer.valueOf(str.charAt(index))-48;
        node.left = generateTree(str);
        node.right = generateTree(str);
        return node;
    }

    private static void printPre(Node node){
        if(node == null){
            System.out.print("#");
            return ;
        }
        System.out.print(node.data);
        printPre(node.left);
        printPre(node.right);
    }

    private static void printMid(Node node){
        if(node == null){
            System.out.print("#");
            return ;
        }
        printPre(node.left);
        System.out.print(node.data);
        printPre(node.right);
    }

    /**
     * 非递归前序遍历打印
     * @param node
     */
    private static void printNormal(Node node){

        class Snode<T>{
            Snode pre;
            Snode next;
            T data;
            public Snode(T data){
                this.data = data;
            }
        }
        class Stack<T>{
            Snode<T> header;
            Snode<T> top; //栈顶
            boolean isEmpty(){
                return header == null;
            }

            void push(T data){
                Snode<T> sNode = new Snode<>(data);
                if(isEmpty()){
                    header = sNode;
                    top = sNode;
                }else{
                    top.next = sNode;
                    sNode.pre = top;
                    top = top.next;
                }
            }
            T pop(){
                T data;
                if(isEmpty()){
                    return null;
                }else {
                    if(top == header){
                       data = header.data;
                       header = null;
                       top = null;
                    }else{
                        data = top.data;
                        Snode delNode = top;
                        top = delNode.pre;
                        top.next = null;
                        //delNode.next.pre = top;
                        delNode.pre = null;
                        delNode.next = null;
                    }
                    return data;
                }
            }
        }
        //根左右
        Stack<Node> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()){
            Node tmp = stack.pop();
            System.out.print(tmp.data+",");
            if(tmp.right!=null){
                stack.push(tmp.right);
            }
            if(tmp.left!=null){
                stack.push(tmp.left);
            }
        }
    }

//    public static <E> void main(String[] args){
//        MyTree<Integer> myTree = new MyTree();
//        myTree.insertTree();
//    }

    /**
     * 交互输入非0数字构造二叉树
     */
    public void insertTree(){
        Scanner scanner = new Scanner(System.in);
        int val = 0;
        while((val = scanner.nextInt())!=0){
            add((E)Integer.valueOf(val));
        }
        printPre(root);
    }

    /**
     * 得到待删除节点的前序遍历的前驱节点
     * @param delNode
     * @return
     */
    private Node getPrecursor(Node delNode){
        Node curr = delNode.left;
        Node proCursor = curr;
        Node parent = null;

        while(curr!=null){
            parent = proCursor;
            proCursor = curr;
            curr = curr.right;
        }
        if(parent==proCursor){
            proCursor.right = delNode.right;
        }else {
            parent.right = proCursor.left;
            proCursor.right = delNode.right;
            proCursor.left = delNode.left;
        }
        return proCursor;
    }

    /**
     * 节点类
     * @param <E>
     */
    static class Node<E> implements Comparable<E>{
        Node left;
        Node right;
        E data;
        Node(){}
        Node(Node left,Node right,E data){
            this.left = left;
            this.right = right;
            this.data = data;
        }

        @Override
        public int compareTo(E o) {
            if(o instanceof Comparable){
                Comparable<E> comparable = (Comparable<E>) o;
                return -comparable.compareTo(this.data);
            }else{
                throw new ClassCastException();
            }
        }
    }

    public static void main(String[] args){
        Node root = generateTree("753##6##98###");
        printPre(root);
        printNormal(root);
    }
}




