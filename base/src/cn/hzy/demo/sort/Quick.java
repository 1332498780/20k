package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.Sort;

public class Quick extends Sort<Integer> {

    public Quick(Integer[] array){
        super("快速排序",array);
    }

    @Override
    public void asc() {
        int end = array.length-1;
        int start = 0;
        int mid = (end - start)/2;

    }

    @Override
    public void desc() {

    }

    @Override
    public void upgrade() {

    }
}
