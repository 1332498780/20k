package cn.hzy.demo.question;

/***
 * 旋转数组的最小值
 */
public class ArrayRotate {

    public static void main (String[] args) {
//        int[] arr = {3,4,6,1,2};
//        int[] arr = {3};
//        int[] arr = {1,1,1,0,1,1,1};
        int[] arr = {1,2,3,4,5,6};

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
        if(array == null || array.length ==0){
            throw new NullPointerException();
        }
        int i = 0,j = array.length-1;
        while(array[i] >= array[j]){
            int mid = (j + i) >> 1;
            if(array[mid] == array[i] && array[mid] == array[j]){
                //i,mid,j 都相等
                return alternative(array);
            }
            if(array[mid]>=array[i]){
                //目标值在右边
                i = mid;
            }else if(array[mid]<=array[j]){
                j = mid;
            }
            if(i+1==j){
                break;
            }
        }
        return Math.min(array[i],array[j]);
    }

    private static int alternative(int[] array){
        return arrayRotate(array);
    }

}
