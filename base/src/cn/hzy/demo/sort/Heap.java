package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

public class Heap extends Sort<Integer> {

    public Heap(Integer[] array){
        super("堆排序",array);
    }

    private void goDownDesc(int index,int len){
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        boolean isSwap = false;
        if(lc<len) {
            if (rc < len) {
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
            goUpDesc(index,len);
        }
    }

    private void goDownAsc(int index,int len){
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        boolean isSwap = false;
        if(lc<len) {
            if (rc < len) {
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
            goUpAsc(index,len);
        }
    }

    private void goUpDesc(int index,int len){
        int parentIndex;
        if(index % 2 == 0){
            parentIndex = (index >> 1) -1;
        }else {
            parentIndex = index >> 1;
        }
        if(index != 0 && parentIndex >= 0){
            if(compareTo(index,parentIndex) == 1){
                swap(index,parentIndex);
                goUpDesc(parentIndex,len);
                goDownDesc(index,len);
            }
        }
    }

    private void goUpAsc(int index,int len){
        int parentIndex;
        if(index % 2 == 0){
            parentIndex = (index >> 1) -1;
        }else {
            parentIndex = index >> 1;
        }
        if(index != 0 && parentIndex >= 0){
            if(compareTo(index,parentIndex) == -1){
                swap(index,parentIndex);
                goUpAsc(parentIndex,len);
                goDownAsc(index,len);
            }
        }
    }

    /**
     * 调整最大堆
     * @param index
     */
    private void heapMaxIf(int index,int len){
        goDownDesc(index,len);
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        if(lc<len){
            heapMaxIf(lc,len);
            if(rc<len){
                heapMaxIf(rc,len);
            }
        }
    }

    private void heapMinIf(int index,int top){
        goDownAsc(index,top);
        int lc = (index << 1) + 1;
        int rc = lc + 1;
        if(lc<array.length){
            heapMinIf(lc,top);
            if(rc<array.length){
                heapMinIf(rc,top);
            }
        }
    }

    private void goDownAdjust(int index,int len){
        for(int k=2*index+1;k<len;k=2*index+1){
            if(k+1 < len && compareTo(k+1,index) == 1){
                k++;
            }
            if(compareValTo(k,index) == 1){
                swap(k,index);
                index = k;
            }else{
                break;
            }
        }
    }

    private void heapSelect(int index){
        for(int i=array.length/2-1;i>=0;i--){
            goDownAdjust(i,array.length);
        }
    }


    public static void main(String[] args) {
        Integer[] array = GenerateData.desc(10);
        Heap heap = new Heap(array);
        heap.printPre(10);
        heap.sortAsc();
        heap.printPre(10);
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

    @Override
    public void asc() {
        for(int i=0;i<array.length-1;i++){
            heapMaxIf(0,array.length-i);
            swap(0,array.length-i-1);
        }
    }

    @Override
    public void desc() {
//        heapMaxIf(0);
    }

    @Override
    public void upgrade() {

    }
}
