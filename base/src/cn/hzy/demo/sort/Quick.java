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
        Integer[] array = GenerateData.desc(1000);
        Quick quick = new Quick(array);
        quick.printPre(20);
        quick.sortAsc();
        quick.printPre(20);
    }

    private int pivot(int start,int end){
        int mid = (end - start) >> 1;
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
                    return mid;
                }else{
                    return end;
                }
            }
        }
    }

    private void recursionAsc(int start,int end){
        int midVal = array[start];
        boolean fromRight = true;
        int i = start,j = end;
        while(i!=j){
            if(fromRight){
                if(compareValTo(array[j],midVal)==-1){
                    array[i++] = array[j];
                    fromRight = !fromRight;
                }else{
                    j--;
                }
            }else{
                if(compareValTo(array[i],midVal)==1){
                    array[j--] = array[i];
                    fromRight = !fromRight;
                }else{
                    i++;
                }
            }
        }
        array[i] = midVal;
        if((i-1) - start > 1){
            recursionAsc(start,i-1);
        }
        if(end - (i+1) > 1){
            recursionAsc(i+1,end);
        }
    }


    @Override
    public void desc() {

    }
}
