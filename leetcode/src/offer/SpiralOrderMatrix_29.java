package offer;

import java.util.stream.Stream;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */
public class SpiralOrderMatrix_29 {
    public static void main(String[] args) {
        SpiralOrderMatrix_29 main = new SpiralOrderMatrix_29();
        main.testSpiralOrder();
    }

    public void testSpiralOrder() {
        int[][] matrix = {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}};
        int[] ints = spiralOrder(matrix);
        for (int i : ints) {
            System.out.println(i);
        }
    }

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int[] result = new int[matrix.length * matrix[0].length];
        int count = 0;
        for (int i = 0; i < (matrix.length + 1) / 2; i++) {
            for (int a = i; a < matrix[i].length - i; a++) {
                result[count++] = matrix[i][a];
            }
            for (int b = i + 1; b < matrix.length - i; b++) {
                result[count++] = matrix[b][matrix[i].length - i - 1];
            }
            for (int c = matrix[i].length - i - 2; c >= i && count < result.length; c--) {
                result[count++] = matrix[matrix.length - i - 1][c];
            }
            for (int d = matrix.length - i - 2; d > i && count < result.length; d--) {
                result[count++] = matrix[d][i];
            }
        }
        return result;
    }
}
