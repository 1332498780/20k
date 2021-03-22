package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

public class Quick extends Sort<Integer> {


    public Quick(Integer[] array) {
        super("快速排序", array);
    }

    @Override
    public void asc() {
        recursionAsc(0,array.length-1);
    }

    public static void main(String[] args){
//        Integer[] array = GenerateData.desc(10);
        Integer[] array = new Integer[]{10,9,8,7,6,5,4,3,2,1};
        Quick quick = new Quick(array);
        quick.printPre(10);
        quick.sortAsc();
        quick.printPre(10);
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
        while(i<=j){
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
        array[mid] = midVal;
        if((mid-1) - start > 0){
            recursionAsc(start,mid-1);
        }
        if(end - (mid+1) > 0){
            recursionAsc(mid+1,end);
        }
    }


    @Override
    public void desc() {

    }
}
