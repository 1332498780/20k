package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

import java.security.Security;
import java.util.Arrays;
import java.util.Random;

public class Select extends Sort<Integer> {

    public Select() {
        super("选择排序");
    }

//    public void upgradeDesc(int[] array){
//        for(int i=0;i<array.length/2;i++){
//            int leftIndex = i;
//            int rightIndex = array.length-1-i;
//            int maxValueIndex = leftIndex;
//            int minValueIndex = rightIndex;
//            if(array[maxValueIndex] < array[minValueIndex]){
//                swap(array,maxValueIndex,minValueIndex);
//            }
//            for(int j=leftIndex+1;j<rightIndex;j++){
//                if(array[j]>array[maxValueIndex]){
//                    maxValueIndex = j;
//                }else if(array[j]<array[minValueIndex]){
//                    minValueIndex = j;
//                }
//            }
//            if(leftIndex!=maxValueIndex){
//                swap(array,leftIndex,maxValueIndex);
//            }
//            if(rightIndex!=minValueIndex){
//                swap(array,rightIndex,minValueIndex);
//            }
//        }
//    }


    public static void main(String[] args){
        Select select = new Select();
        Integer[] array = GenerateData.asc(GenerateData.w1);
        select.sortAsc(array);
    }

    @Override
    public void asc(Integer[] array) {
        for(int i=0;i<array.length-1;i++){
            int index = i;
            for(int j=index+1;j<array.length;j++){
                if(compareTo(j,index) == -1){
                    index = j;
                }
            }
            if(index!=i){
                swap(index,i);
            }
        }
    }

    @Override
    public void desc(Integer[] array) {
        for(int i=0;i<array.length-1;i++){
            int index = i;
            for(int j=index+1;j<array.length;j++){
                if(compareTo(j,index) == 1){
                    index = j;
                }
            }
            if(index!=i){
                swap(index,i);
            }
        }
    }
}
