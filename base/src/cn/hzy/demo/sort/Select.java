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
        select.sortAsc();
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
}
