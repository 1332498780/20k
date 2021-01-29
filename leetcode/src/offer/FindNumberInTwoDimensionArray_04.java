package offer;

public class FindNumberInTwoDimensionArray_04 {
    public static void main(String[] args) {
        int[][] matrix = new int[1][3];
        matrix[0] = new int[]{1, 3, 5};
//        matrix[0] = new int[]{1,2,8,9};
//        matrix[1] = new int[]{2,4,9,12};
//        matrix[2] = new int[]{4,7,10,13};
//        matrix[3] = new int[]{6,8,11,15};
//        matrix[4] = new int[]{7,8,11,15};
        boolean result = new FindNumberInTwoDimensionArray_04().findNumberIn2DArray(matrix, 5);
        System.out.println(result);
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            int row =0;
            int col = matrix[0].length -1;
            while (row < matrix.length && col >= 0) {
                if (target == matrix[row][col]) {
                    return true;
                } else if (target < matrix[row][col]) {
                    col--;
                } else {
                    row++;
                }
            }
        }
        return false;
    }

    // 按列遍历，从右向左，从上到下
    public boolean findNumberIn2DArray_0(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int j = 1;
        for (int i = matrix[0].length - 1; i >= 0; i--) {
            if (target == matrix[0][i]) {
                return true;
            } else if (target > matrix[0][i]) {
                while (j < matrix.length) {
                    if (matrix[j][i] == target) {
                        return true;
                    } else if (target < matrix[j][i]) {
                        j++;
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    // 按遍历，从上到下，从小到大
    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：44.3 MB, 在所有 Java 提交中击败了 28.34% 的用户
    public boolean findNumberIn2DArray_n(int[][] matrix, int target) {
        if (matrix == null) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (target < matrix[i][0]) {
                    break;
                }
                if (target == matrix[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
