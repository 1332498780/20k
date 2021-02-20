package cn.hzy.demo.collection.list;

import java.util.*;

public class MyArrayList<E> implements List<E> {

//    private final int initSize = 2 << 3;
    private Object[] container;
    private int currentIndex = -1;

    private int expansionCount = 0;


    private class InnerIterator<E> implements ListIterator<E>{


        public boolean hasNext() {
            return false;
        }

        public E next() {
            return null;
        }

        public boolean hasPrevious() {
            return false;
        }

        public E previous() {
            return null;
        }

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return 0;
        }

        public void remove() {

        }

        public void set(E e) {

        }

        public void add(E e) {

        }
    }


    public MyArrayList(){
        this(2 << 3);
    }
    public MyArrayList(int initialSize){
        if(initialSize > -1){
            container = new Object[initialSize];
        }else{
            throw new IllegalArgumentException(initialSize+" in not a valid capacity!");
        }
    }

    public int size() {
        return this.currentIndex+1;
    }

    public boolean isEmpty() {
        return this.currentIndex == -1;
    }

    public boolean contains(Object o) {
        for(int i=0;i<=currentIndex;i++){
            if(o.equals(container[currentIndex]))
                return true;
        }
        return false;
    }

    private class Ite implements Iterator<E>{

        int nextIndex = 0;
        int lastIndex = -1;
        @Override
        public boolean hasNext() {
            return nextIndex <= currentIndex;
        }

        @Override
        public E next() {
            if(nextIndex <= currentIndex){
                lastIndex = nextIndex;
                return (E)container[nextIndex++];
            }else{
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if(lastIndex == -1){
                throw new NoSuchElementException();
            }
            MyArrayList.this.remove(lastIndex);
            nextIndex = lastIndex;
            lastIndex = -1;
        }
    }

    public Iterator<E> iterator() {
//        Iterator<E> iterator = new Iterator<E>() {
//            int currIndex = 0;
//
//            public boolean hasNext() {
//                return this.currIndex <= currentIndex;
//            }
//
//            public E next() {
//                return (E) container[this.currIndex++];
//            }
//
//            public void remove() {
//                MyArrayList.this.remove(this.currIndex);
//            }
//        };
        return new Ite();
    }

    public Object[] toArray() {
        return Arrays.copyOfRange(this.container,0,this.size());
    }

    public <T> T[] toArray(T[] a) {
        if(a.length<this.size()){
            return (T[])Arrays.copyOfRange(this.container,0,this.size(),a.getClass());
        }else{
            System.arraycopy(this.container,0,a,0,this.currentIndex+1);
            return a;
        }
    }

    public boolean add(E e) {
        if(isNeedExpansion()){
            expansion();
        }
        this.container[++currentIndex] = e;
        return true;
    }

    private boolean isNeedExpansion(){
        return this.currentIndex+1 > this.container.length;
    }
    private boolean isNeedExpansion(int count){
        return this.currentIndex+count > this.container.length;
    }
    private void expansion(){
        this.expansion(1);
    }
    private void expansion(int count){
        int tempExpansionCount,tempLen;
        do{
            tempExpansionCount = 2 << this.expansionCount++;
            tempLen = tempExpansionCount + this.container.length;
        }while(tempLen < this.currentIndex+count);
        Object[] newArr = new Object[tempLen];
        System.arraycopy(this.container,0,newArr,0,this.currentIndex+1);
        this.container = null;
        this.container = newArr;
    }
    private String outOfBoundTip(int index){
        return index +" out of [0, "+this.currentIndex+"]";
    }
    private void checkBounds(int index){
        if(index>this.currentIndex){
            throw new IndexOutOfBoundsException(outOfBoundTip(index));
        }
    }
    private void moveTo(int index,int to){
        int len = Math.abs(to - index);
        if(index < to){
            for(int i=this.currentIndex;i>=index;i--){
                this.container[len+i] = this.container[i];
            }
            this.currentIndex += len;
        }else{
            for(int i=index;i<=this.currentIndex;i++){
                this.container[i-len] = this.container[i];
            }
            this.currentIndex -= len;
        }
    }

    public boolean remove(Object o) {
        Iterator<E> iterator = this.iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(o)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            if(!this.contains(iterator.next())){
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        if(this.isNeedExpansion(c.size())){
            this.expansion(c.size());
        }
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            this.add((E)iterator.next());
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        checkBounds(index);
        if(this.isNeedExpansion(c.size())){
            this.expansion(c.size());
        }
        moveTo(index,index+c.size());
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            this.container[index++] = iterator.next();
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        Iterator iterator = c.iterator();
        while(iterator.hasNext()){
            this.remove(iterator.next());
        }
        return true;
    }

    public boolean retainAll(Collection<?> c) {
        Iterator iterator = this.iterator();
        while(iterator.hasNext()){
            if(!c.contains(iterator.next())){
                iterator.remove();
            }
        }
        return true;
    }

    public void clear() {
        this.container = new Object[0];
        this.currentIndex = -1;
        this.expansionCount = 0;
    }

    public E get(int index) {
        checkBounds(index);
        return (E)this.container[index];
    }

    public E set(int index, E element) {
        checkBounds(index);
        E previousElement = (E)this.container[index];
        this.container[index] = element;
        return previousElement;
    }

    public void add(int index, E element) {
        checkBounds(index);
        if(isNeedExpansion()){
            expansion();
        }
        moveTo(index,index+1);
        this.container[index] = element;
    }

    public E remove(int index) {
        checkBounds(index);
        E removeElement = (E) this.container[index];
        if(index == this.currentIndex){
            this.currentIndex--;
        }else{
            moveTo(index+1,index);
        }
        return removeElement;
    }

    public int indexOf(Object o) {
        for(int i=0;i<=this.currentIndex;i++){
            if(this.container[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        for(int i=this.currentIndex;i>=0;i++){
            if(this.container[i].equals(o)){
                return i;
            }
        }
        return -1;
    }

    public ListIterator<E> listIterator() {
        return null;
    }

    public ListIterator<E> listIterator(int index) {
        return null;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        checkBounds(fromIndex);
        checkBounds(toIndex);
        int len = toIndex - fromIndex;
        List<E> subList = new MyArrayList<E>(len);
        for(int i=fromIndex;i<=toIndex;i++){
            subList.add((E)this.container[i]);
        }
        return subList;
    }
}
