package cn.hzy.demo.grammer.vm;

import java.util.NoSuchElementException;

public class MyTree<E> {

    private Node root;

    public void add(E e){
        Node node = new Node(null,null,e);
        if(root== null){
            root = node;
        }else{
            ite(root,node);
        }
    }

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

    public static <E> void main(String[] args){
        String str = "731##4#5##98###";
        Node<E> node = generateTree(str);
        printPre(node);
        MyTree myTree = new MyTree();
        myTree.root = node;
        myTree.remove(7);
        printPre(myTree.root);
    }


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


    private Node midIte(Node pre,Node node,Node target){
        if(node != null){
            if(node == target){
                return pre;
            }
            pre = midIte(pre,node.left,target);
            if(pre == null){
                pre = node;
            }
            pre =  midIte(pre,node.right,target);
        }else{
            return null;
        }
        return null;
    }

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
}




