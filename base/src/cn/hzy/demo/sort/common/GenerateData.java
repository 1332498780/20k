package cn.hzy.demo.sort.common;

import java.util.Arrays;

public class GenerateData {


    private static final int w = 10000;

    public static final int w1 = w;
    public static final int w10 = w*10;
    public static final int w20 = w*20;
    public static final int w50 = w*50;


    public static Integer[] asc(int num){
        Integer[] array = new Integer[num];
        for(int i=0;i<num;i++){
            array[i] = i+1;
        }
        return array;
    }

    public static Integer[] desc(int num){
        Integer[] array = new Integer[num];
        final int number = 2*num;
        for(int i=0;i<num;i++){
            array[i] = number - i+1;
        }
        return array;
    }

    public static Integer[] ad(int num){
        Integer[] array = new Integer[num];
        int half = num / 2;
        Integer[] ascData = asc(half);
        Integer[] descData = desc(half);
        System.arraycopy(ascData,0,array,0,half);
        System.arraycopy(descData,0,array,half,half);
        return array;
    }

    public static Integer[] da(int count){
        Integer[] array = new Integer[count];
        int half = count / 2;
        Integer[] ascData = asc(half);
        Integer[] descData = desc(half);
        System.arraycopy(descData,0,array,0,half);
        System.arraycopy(ascData,0,array,half,half);
        return array;
    }

}
