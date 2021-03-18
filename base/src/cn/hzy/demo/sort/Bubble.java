package cn.hzy.demo.sort;

public class Bubble {


    public void bubbleDesc(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=array.length-1;j>=i+1;j--){
                if(array[j]>array[j-1]){
                    swap(array,j-1,j);
                }
            }
        }
    }
    public void bubbleAsc(int[] array){
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<=array.length-i-2;j++){
                if(array[j]>array[j+1]){
                    swap(array,j,j+1);
                }
            }
        }
    }

    void upgrade(int[] array){
        int mark = 0;
        while(mark<array.length-1){
            int i = mark;
            for(int j=array.length-1;j>=i+1;j--){
                if(array[j]>array[j-1]){
                    swap(array,j,j-1);
                    mark = j;
                }
            }
            if(mark == i){
                break;
            }
        }
    }


    void swap(int[] array,int a,int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
    void print(int[] array){
        for(int i : array){
            System.out.print(i+",");
        }
    }

    public static void main(String[] args){
        Bubble b = new Bubble();
        int[] array = new int[]{1,2,3,4,5,6,7,8,9};
//        int[] array = new int[]{9,8,7,6,5,4,3,2,1};
        b.upgrade(array);
        b.print(array);
    }

}
