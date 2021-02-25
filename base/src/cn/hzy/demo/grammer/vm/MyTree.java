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
        Node parentNode = root;
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
                currentNode = currentNode.left;
                isLeftChild = true;
            }else{
                currentNode = currentNode.right;
                isLeftChild = false;
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

        }
    }
    private Node getPrecursor(Node head,Node pre,Node node){
        if(head.left !=null){
            getPrecursor(head.left,head)
        }
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
            if(pre){}
        }else{
            return null;
        }
    }

    static class Node<E> implements Comparable<E>{
        Node left;
        Node right;
        E data;
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




