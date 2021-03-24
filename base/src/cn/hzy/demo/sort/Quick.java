package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Quick extends Sort<Integer> {


    public Quick(Integer[] array) {
        super("快速排序", array);
    }

    @Override
    public void asc() {
        recursionAsc(0,array.length-1);
    }

    public static void main(String[] args) throws InterruptedException {
        Integer[] array = GenerateData.ad(20);
//        Integer[] array = new Integer[]{10,9,8,7,6,5,4,3,2,1};
        Quick quick = new Quick(array);
        quick.printPre(20);
        quick.sortAsc();
        quick.printPre(20);
//        System.out.println(quick.pivot(0,9));
    }

    private int pivot(int start,int end){
        int mid = (end + start) >> 1;
        //三数取中
        if(compareTo(start,mid)==1){
            if(compareTo(start,end) == 1){
                if(compareTo(mid,end) == 1){
                    return mid;
                }else{
                    return end;
                }
            }else{
                return start;
            }
        }else{
            if(compareTo(start,end) == 1){
                return start;
            }else{
                if(compareTo(mid,end) == 1){
                    return end;
                }else{
                    return mid;
                }
            }
        }
    }

    private void recursionAsc(int start,int end){
        int mid = pivot(start,end);
        int midVal = array[mid];
        boolean fromRight = true;
        int i = start,j = end;
        while(i<j){
            if(fromRight){
                if(j==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[j],midVal)==-1){
                    array[mid] = array[j];
                    mid = j;
                    fromRight = !fromRight;
                }
                j--;
            }else{
                if(i==mid){
                    fromRight = !fromRight;
                    continue;
                }
                if(compareValTo(array[i],midVal)==1){
                    array[mid] = array[i];
                    mid = i;
                    fromRight = !fromRight;
                }
                i++;
            }
        }
        //i==j时，上边没有做比较操作来确定mid值和i==j值的位置关系，所以下面补充了判断
        if(i<mid && compareTo(i,mid) ==  1){
            array[mid] = array[i];
            mid = i;
        }else if(j>mid && compareTo(j,mid) == -1){
            array[mid] = array[j];
            mid = j;
        }
        array[mid] = midVal;
        if((mid-1) - start > 0){
            recursionAsc(start,mid-1);
        }
        if(end - (mid+1) > 0){
            recursionAsc(mid+1,end);
        }
    }

    private void findBaseIndex(LinkedList<Node> list,Node node){
        int start = node.start,end = node.end;
        int mid = pivot(start,end);
        int midVal = array[mid];
        boolean fromRight = true;
        int i = start,j = end;
        while(i<j){
            if(fromRight){
                if(compareValTo(array[j],midVal)==-1){
                    array[mid] = array[j];
                    mid = j;
                    fromRight = !fromRight;
                }
                j--;
            }else{
                if(compareValTo(array[i],midVal)==1){
                    array[mid] = array[i];
                    mid = i;
                    fromRight = !fromRight;
                }
                i++;
            }
        }
        //i==j时，上边没有做比较操作来确定mid值和i==j值的位置关系，所以下面补充了判断
        if(i<mid && compareTo(i,mid) ==  1){
            array[mid] = array[i];
            mid = i;
        }else if(j>mid && compareTo(j,mid) == -1){
            array[mid] = array[j];
            mid = j;
        }
        array[mid] = midVal;
        if((mid-1) - start > 0){
            list.offer(new Node(start,mid-1));
//            recursionAsc(start,mid-1);
        }
        if(end - (mid+1) > 0){
            list.offer(new Node(mid+1,end));
//            recursionAsc(mid+1,end);
        }
    }

    class Node{
        int start;
        int end;
        public Node(int start,int end){
            this.start = start;
            this.end = end;
        }
    }

    private void ascNormal(int start,int end){
        LinkedList<Node> list = new LinkedList<>();
        list.offer(new Node(start,end));
        while(!list.isEmpty()){
            findBaseIndex(list,list.poll());
        }
    }


    @Override
    public void desc() {

    }

    @Override
    public void upgrade() {

    }
}
