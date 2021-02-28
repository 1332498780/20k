package DayFive20210228;

import java.util.Arrays;

public class MergeSortTest {
    public static void merge(int []target,int left,int mid,int right){
        int []tmp=new int[target.length];
        int p1=left,p2=mid+1,k=left;

        while(p1<=mid && p2<=right){
            if(target[p1]<=target[p2])
                tmp[k++]=target[p1++];
            else
                tmp[k++]=target[p2++];
        }

        while(p1<=mid) tmp[k++]=target[p1++];
        while(p2<=right) tmp[k++]=target[p2++];


        for (int i = left; i <=right; i++)
            target[i]=tmp[i];
    }

    public static int [] mergeSort(int [] target,int start,int end){
        if(start<end){
            int mid=(start+end)/2;
            mergeSort(target, start, mid);
            mergeSort(target, mid+1, end);
            merge(target, start, mid, end);
        }
        return target;
    }

    public static void main(String[] args) {
        int[] target = new int[11];
        for (int i = 0; i < 11; i++) {
            target[i] = (int) (Math.random() * 100) + 1;
        }
        System.out.print(Arrays.toString(target));
        System.out.println();
        System.err.print(Arrays.toString(mergeSort(target, 0, target.length - 1)));
    }
}
