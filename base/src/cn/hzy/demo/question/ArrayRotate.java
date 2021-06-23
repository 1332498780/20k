package cn.hzy.demo.question;

/***
 * 旋转数组的最小值
 */
public class ArrayRotate {

    public static void main (String[] args) {
        int[] arr = {3,4,6,1,2};
        System.out.println(arrayRotate1(arr));
    }

    public static int arrayRotate(int[] array){
        for(int i=0;i<array.length-1;i++){
            if(array[i]>array[i+1]){
                return array[i+1];
            }
        }
        return array[0];
    }

    public static int arrayRotate1(int[] array){
        int i = 0,j = array.length-1;
        while(i+1<j){
            int mid = (j + i) >> 2;
            if(array[mid]>=array[i]){
                //目标值在右边
                i = mid;
            }else if(array[mid]<=array[j]){
                j = mid;
            }
            if(i==j+1){
                break;
            }
        }
        return j;
    }

}
