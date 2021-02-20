package cn.hzy.demo.collection.list;

import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class MyLinkedList<E> implements List<E> {

    private Node head;
    private Node currentNode;
    private int size;

    private class Node{
        E e;
        Node next;
        Node pre;
    }

    public MyLinkedList(){
        head = new Node();
        currentNode = head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return currentNode == head;
    }

    @Override
    public boolean contains(Object o) {
        return iterateNode(o) != null;
    }

    private Node iterateNode(Object o){
        Node temp = head.next;
        while(temp != null && temp != currentNode){
            if(temp.e.equals(o)){
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    private Node iterateNode(int index){
        if(index < 0 || index > size-1)
            throw new IndexOutOfBoundsException("index out of [0,"+(size-1)+"]");
        Node temp;
        if(index+1 > size/2){
            temp = currentNode;
            int t = size-1;
            while(temp != head){
                if(t-- == index){
                    return temp;
                }
                temp = temp.pre;
            }
        }else{
            temp = head.next;
            int i = 0;
            while(temp != currentNode){
                if(i++ == index){
                    return temp;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    private boolean isTailer(Node node){
        return node.next == null;
    }
    private boolean remove(Node node){
        if(isTailer(node)){
            node.pre.next = null;
            node.pre = null;
        }else{
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        Node temp = head.next;
        int i=0;
        while(temp != null){
            result[i++] = temp.e;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] objects = toArray();
        if(a==null || a.length < size){
            return (T[]) Arrays.copyOfRange(objects,0,size,a.getClass());
        }else{
            System.arraycopy(objects,0,a,0,size);
            return a;
        }
    }

    @Override
    public boolean add(E e) {
        Node node = new Node();
        node.e = e;
        currentNode.next = node;
        node.pre = currentNode;
        currentNode = currentNode.next;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node node = iterateNode(o);
        if(node != null){
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node = null;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            if(!contains(iterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Iterator<E> iterator = (Iterator<E>) c.iterator();
        while(iterator.hasNext()){
            Node node = new Node();
            node.e = iterator.next();
            currentNode.next = node;
            node.pre = currentNode;
            currentNode = currentNode.next;
            size++;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Node indexNode = iterateNode(index);
        if(indexNode != null){
            Node tailer = indexNode;
            Node tmp = indexNode.pre;
            Iterator<E> iterator = (Iterator<E>) c.iterator();
            while (iterator.hasNext()){
                Node node = new Node();
                node.e = iterator.next();
                tmp.next = node;
                node.pre = tmp;
                tmp = tmp.next;
                size++;
            }
            if(tailer.pre == tmp){
                return false;
            }
            tmp.next = tailer;
            tailer.pre = tmp;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        boolean flag = false;
        while(iterator.hasNext()){
            if(remove(iterator.next())) {
                flag = true;
                size--;
            }
        }
        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node temp = head.next;
        boolean isChanged = false;
        while(temp != null && temp != currentNode){
            Iterator iterator = c.iterator();
            boolean hasFound = false;
            while(iterator.hasNext()){
                if(temp.e.equals(iterator.next())){
                    hasFound = true;
                    break;
                }
            }
            if(!hasFound){
                isChanged = remove(temp);
                if(isChanged)
                    size--;
            }
        }
        return isChanged;
    }

    @Override
    public void clear() {
        Node temp = head.next;
        while(temp != null){
            temp.pre.next = null;
            temp.pre = null;
            temp = temp.next;
        }
        size = 0;
        currentNode = null;
    }

    @Override
    public E get(int index) {
        Node node = iterateNode(index);
        return node != null ? node.e : null;
    }

    @Override
    public E set(int index, E element) {
        Node node = iterateNode(index);
        E result = null;
        if(node!=null){
            result = node.e;
            node.e = element;
        }
        return result;
    }

    @Override
    public void add(int index, E element) {
        Node node = iterateNode(index);
        if(node!=null){
            Node newNode = new Node();
            newNode.e = element;
            node.pre.next = newNode;
            newNode.pre = node.pre;
            newNode.next = node;
            node.pre = newNode;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        Node node = iterateNode(index);
        E result = null;
        if(node!=null){
            result = node.e;
            if(remove(node)){
                size--;
            }
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        Node temp = head.next;
        int i=0;
        while(temp != null && i < size){
            if(o == null){
                if(temp.e == null){
                    return i;
                }
            }else{
                if(o.equals(temp.e)){
                    return i;
                }
            }
            temp = temp.next;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node temp = currentNode;
        int i=size-1;
        while(currentNode != head && i >= 0){
            if(o == null){
                if(temp.e == null){
                    return i;
                }
            }else{
                if(o.equals(temp.e)){
                    return i;
                }
            }
            temp = temp.pre;
            i--;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if(fromIndex >= 0 && fromIndex < size && toIndex >=0 && toIndex < size && toIndex >= fromIndex){
            List<E> list = new MyLinkedList();
            Node tempNode = iterateNode(fromIndex);
            while(tempNode != null && fromIndex++ <= toIndex){
                list.add(tempNode.e);
                tempNode = tempNode.next;
            }
            return list;
        }else{
            throw new IndexOutOfBoundsException("["+fromIndex+","+toIndex+"] out of bounds [0,"+(size-1)+"]");
        }
    }
}
