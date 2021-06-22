package cn.hzy.demo.question;

public class Daemesion2Test {

    /***
     * 在二维数组斜边定位目标的范围(二分法查找),剩下用2次遍历范围
     *
     * 时间复杂度：log2^n （n是斜边的长度）
     * @param arr
     * @param target
     * @return
     */
    public static boolean existInArray(int[][] arr,int target){
        int rowCount = arr.length;
        int colCount = arr[0].length;
        int minVal = Math.min(rowCount,colCount);
        int left = 0,right = minVal;
        while(left>=0 && right<= minVal) {
            int mid = (left + right) / 2;
            if (target == arr[mid][mid]) {
                return true;
            } else {
                if (left + 1 == right) {
                    break;
                }
                if (target < arr[mid][mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        //从列开始遍历
        int num = left +1;
        if(num < rowCount){
            for(int i = 0;i<=colCount;i++){
                if(arr[num][i]==target){
                    return true;
                }
            }
        }
        if(num < colCount){
            for(int i = 0;i<=rowCount;i++){
                if(arr[i][num]==target){
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * 通俗易懂好理解
     * @param arr
     * @param target
     * @return
     */
    public static boolean existInArray1(int[][] arr,int target){
        //从右上角开始
        int rowLimit = arr.length;
        int colLimit = arr[0].length;
        int rowIndex = 0,colIndex = colLimit-1;
        while(rowIndex < rowLimit && colIndex > 0){
            if(target == arr[rowIndex][colIndex]){
                return true;
            }else{
                if(target < arr[rowIndex][colIndex]){
                    colIndex--;
                }else {
                    rowIndex++;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        boolean flag = Daemesion2Test.existInArray1(new int[][]{{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}},16);
        System.out.println(flag);
    }
}
