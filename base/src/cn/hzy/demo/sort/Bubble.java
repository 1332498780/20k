package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

public class Bubble extends Sort<Integer> {

    public Bubble(Integer[] array) {
        super("冒泡排序", array);
    }

    public static void main(String[] args){
        Integer[] array = GenerateData.da(GenerateData.w1);
        Bubble bubble = new Bubble(array);
        bubble.printPre(20);
        bubble.sortUpgrade();
        bubble.printPre(20);
    }

    @Override
    public void asc() {
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<=array.length-i-2;j++){
                if(compareTo(j,j+1) == 1){
                    swap(j,j+1);
                }
            }
        }
    }

    @Override
    public void desc() {
        for(int i=0;i<array.length-1;i++){
            for(int j=array.length-1;j>=i+1;j--){
                if(compareTo(j,j-1) == 1){
                    swap(j-1,j);
                }
            }
        }
    }

    @Override
    public void upgrade() {
        int mark = 0;
        while(mark<array.length-1){
            int i = mark;
            for(int j=array.length-1;j>=i+1;j--){
                if(compareTo(j,j-1) == 1){
                    swap(j,j-1);
                    mark = j;
                }
            }
            if(mark == i){
                break;
            }
        }
    }
}
