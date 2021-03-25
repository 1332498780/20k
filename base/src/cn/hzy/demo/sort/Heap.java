package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

import java.io.FileInputStream;

public class Heap extends Sort<Integer> {

    public Heap(Integer[] array){
        super("堆排序",array);
    }

    private void goDownDesc(int index){
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        boolean isSwap = false;
        if(lc<array.length) {
            if (rc < array.length) {
                if (compareTo(lc, index) == 1) {
                    if (compareTo(rc, lc) == 1) {
                        swap(rc, index);
                    } else {
                        swap(lc, index);
                    }
                    isSwap = true;
                } else {
                    if (compareTo(rc, index) == 1) {
                        swap(rc, index);
                        isSwap = true;
                    }
                }
            } else {
                if (compareTo(lc, index) == 1) {
                    swap(lc, index);
                    isSwap = true;
                }
            }
        }
        if(isSwap){
            goUpDesc(index);
        }
    }

    private void goDownAsc(int index){
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        boolean isSwap = false;
        if(lc<array.length) {
            if (rc < array.length) {
                if (compareTo(lc, index) == -1) {
                    if (compareTo(rc, lc) == -1) {
                        swap(rc, index);
                    } else {
                        swap(lc, index);
                    }
                    isSwap = true;
                } else {
                    if (compareTo(rc, index) == -1) {
                        swap(rc, index);
                        isSwap = true;
                    }
                }
            } else {
                if (compareTo(lc, index) == -1) {
                    swap(lc, index);
                    isSwap = true;
                }
            }
        }
        if(isSwap){
            goUpAsc(index);
        }
    }

    private void goUpDesc(int index){
        int parentIndex;
        if(index % 2 == 0){
            parentIndex = (index >> 1) -1;
        }else {
            parentIndex = index >> 1;
        }
        if(index != 0 && parentIndex >= 0){
            if(compareTo(index,parentIndex) == 1){
                swap(index,parentIndex);
                goUpDesc(parentIndex);
                goDownDesc(index);
            }
        }
    }

    private void goUpAsc(int index){
        int parentIndex;
        if(index % 2 == 0){
            parentIndex = (index >> 1) -1;
        }else {
            parentIndex = index >> 1;
        }
        if(index != 0 && parentIndex >= 0){
            if(compareTo(index,parentIndex) == -1){
                swap(index,parentIndex);
                goUpAsc(parentIndex);
                goDownAsc(index);
            }
        }
    }

    /**
     * 调整最大堆
     * @param index
     */
    private void heapMaxIf(int index){
        goDownDesc(index);
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        if(lc<array.length){
            heapMaxIf(lc);
            if(rc<array.length){
                heapMaxIf(rc);
            }
        }
    }

    private void heapMinIf(int index){
        goDownAsc(index);
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        if(lc<array.length){
            heapMinIf(lc);
            if(rc<array.length){
                heapMinIf(rc);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] array = GenerateData.asc(GenerateData.w10);
        Heap heap = new Heap(array);
        heap.printPre(20);
        heap.sortAsc();
        heap.printPre(20);
    }
    private boolean validate(){
        for(int i=0;i<array.length;i++){
            int lc = (i<<1)+1;
            int rc = (i<<1)+2;
            if(lc < array.length){
                if(array[i]<array[lc]){
                    System.out.println(array[i]+","+array[lc]);
                    return false;
                }
                if(rc < array.length && array[i] < array[rc]){
                    System.out.println(array[i]+","+array[rc]);
                    return false;
                }
            }
        }
        return true;
    }

    enum HeapType{
        max,min;
    }

    @Override
    public void asc() {
        heapMinIf(0);
    }

    @Override
    public void desc() {
        heapMaxIf(0);
    }

    @Override
    public void upgrade() {

    }
}
