package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

import java.security.Security;
import java.util.Arrays;
import java.util.Random;

public class Select extends Sort<Integer> {

    public Select(Integer[] array) {
        super("选择排序",array);
    }

    public static void main(String[] args){
        Integer[] array = GenerateData.asc(GenerateData.w1);
        Select select = new Select(array);
        select.printPre(20);
        select.sortUpgrade();
        select.printPre(20);
    }

    @Override
    public void asc() {
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
    public void desc() {
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

    @Override
    public void upgrade() {
        for(int i=0;i<array.length/2;i++){
            int leftIndex = i;
            int rightIndex = array.length-1-i;
            int maxValueIndex = leftIndex;
            int minValueIndex = rightIndex;
            if(compareTo(maxValueIndex,minValueIndex)==-1){
                swap(maxValueIndex,minValueIndex);
            }
            for(int j=leftIndex+1;j<rightIndex;j++){
                if(compareTo(j,maxValueIndex) == 1){
                    maxValueIndex = j;
                }else if(compareTo(j,minValueIndex) == -1){
                    minValueIndex = j;
                }
            }
            if(leftIndex!=maxValueIndex){
                swap(leftIndex,maxValueIndex);
            }
            if(rightIndex!=minValueIndex){
                swap(rightIndex,minValueIndex);
            }
        }
    }
}
