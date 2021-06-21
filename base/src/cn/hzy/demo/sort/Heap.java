package cn.hzy.demo.sort;

import cn.hzy.demo.sort.common.GenerateData;
import cn.hzy.demo.sort.common.Sort;

/**
 * 最开始我自己实现时，是从上往下构造堆，这样的话比官方的写法(从下往上构造)多比较很多次。
 * 这里还有一个点需要注意，如果排序为升序，那么构造的堆应该是最大堆。因为在构造完堆后，需要每次交换堆顶到尾部，这样在尾部就形成
 * 了一个升序。
 * heapMaxIf,heapMinIf 是我自己的实现。（不好用）
 * heapSelect 官方实现。
 *
 * 稳定性：不稳定。父亲和右子树值相同，父节点在前，右子树在后。这样在交换的时候，父节点在后，右子树在前。
 * 时间复杂度：nlogn. 堆调整的过程是从最后一个父节点(length/2-1)开始循环向上，先和子节点比较，然后循环向下比较被交换元素。
 * 需要被向上循环的元素个数：n个，需要向下被交换的元素的个数是书的高度logn。然后排序过程也是nlogn,两步做加法。
 */
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
            if(k+1 < len && compareTo(k+1,k) == 1){
                k++;
            }
            if(compareTo(k,index) == 1){
                swap(k,index);
                index = k;
            }else{
                break;
            }
        }
    }

    private void heapSelect(){
        //构造堆
        for(int i=array.length/2-1;i>=0;i--){
            goDownAdjust(i,array.length);
        }
        //排序
        for(int i=0;i<array.length-1;i++){
            swap(0,array.length-1-i);
            goDownAdjust(0,array.length-1-i);
        }

    }


    public static void main(String[] args) {
        Integer[] array = GenerateData.da(GenerateData.w10);
        Heap heap = new Heap(array);
        heap.printPre(20);
        heap.sortAsc();
        heap.printPre(20);
        heap.validateAsc();
    }

    private boolean validateDesc(){
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

    private boolean validateAsc(){
        for(int i=0;i<array.length;i++){
            int lc = (i<<1)+1;
            int rc = (i<<1)+2;
            if(lc < array.length){
                if(array[i]>array[lc]){
                    System.out.println(array[i]+","+array[lc]);
                    return false;
                }
                if(rc < array.length && array[i] > array[rc]){
                    System.out.println(array[i]+","+array[rc]);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void asc() {
        heapSelect();
    }

    @Override
    public void desc() {
    }

    @Override
    public void upgrade() {
    }
}
