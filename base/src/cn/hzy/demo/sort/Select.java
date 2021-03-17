package cn.hzy.demo.sort;

import java.security.Security;
import java.util.Arrays;
import java.util.Random;

public class Select {

    public void selectDesc(int[] array){
        for(int i=0;i<array.length-1;i++){
            int index = i;
            for(int j=index+1;j<array.length;j++){
                if(array[j]>array[index]){
                    index = j;
                }
            }
            if(index!=i){
                swap(array,index,i);
            }
        }
    }

    public void selectAsc(int[] array){
        for(int i=0;i<array.length-1;i++){
            int index = i;
            for(int j=index+1;j<array.length;j++){
                if(array[j]<array[index]){
                    index = j;
                }
            }
            if(index!=i){
                swap(array,index,i);
            }
        }
    }

    public void upgradeDesc(int[] array){
        for(int i=0;i<array.length/2;i++){
            int leftIndex = i;
            int rightIndex = array.length-1-i;
            int maxValueIndex = leftIndex;
            int minValueIndex = rightIndex;
            if(array[maxValueIndex] < array[minValueIndex]){
                swap(array,maxValueIndex,minValueIndex);
            }
            for(int j=leftIndex+1;j<rightIndex;j++){
                if(array[j]>array[maxValueIndex]){
                    maxValueIndex = j;
                }else if(array[j]<array[minValueIndex]){
                    minValueIndex = j;
                }
            }
            if(leftIndex!=maxValueIndex){
                swap(array,leftIndex,maxValueIndex);
            }
            if(rightIndex!=minValueIndex){
                swap(array,rightIndex,minValueIndex);
            }
        }
    }

    void print(int[] array){
        for(int i:array){
            System.out.print(i+",");
        }
        System.out.println();
    }
    int[] randomArray(int count){
        Random random = new Random();
        final int val = count*2;
        int[] array = new int[count];
        for(int i=0;i<count;i++){
            array[i] = random.nextInt(val);
        }
        return array;
    }


    public static void main(String[] args){
        Select select = new Select();
        final int w = 10000;
        int[] array = select.randomArray(20);
        int[] other = Arrays.copyOf(array,array.length);
        select.print(array);
        select.print(other);

        long start = System.currentTimeMillis();
        select.upgradeDesc(other);
        long end = System.currentTimeMillis();
        System.out.print("cost"+ (end-start)/1000+" s");
        select.print(other);

        start = System.currentTimeMillis();
        select.selectDesc(array);
        end = System.currentTimeMillis();
        System.out.print("cost"+ (end-start)/1000+" s");
        select.print(array);
    }

    int count=0;
    void swap(int[] array,int a,int b){
        count++;
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
