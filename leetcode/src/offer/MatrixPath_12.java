package offer;

import java.util.ArrayList;

public class MatrixPath_12 {
    public static void main(String[] args) {
        char[][] board = {
            {'A','B','C','E'},
            {'S','F','E','S'},
            {'A','D','E','E'}
        };
        boolean res =  new MatrixPath_12().exist(board, "ABCEFSADEESE");
        System.out.println(res);
    }

    // 执行用时：21 ms, 在所有 Java 提交中击败了 7.64% 的用户
    // 内存消耗：39.2 MB, 在所有 Java 提交中击败了 95.58% 的用户
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == "") {
            return false;
        }
        // 遍历二维数组，找到所有开头字符
        ArrayList<int[]> chars = new ArrayList<>();
        char head = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == head) {
                    chars.add(new int[]{i,j});
                }
            }
        }

        // 遍历矩阵中所有开头字符，调用递归方法，使用回溯法来寻找
        for (int[] coordinate : chars) {
            // 辅助面板
            int[][] board2 = new int[board.length][board[0].length];
            board2[coordinate[0]][coordinate[1]] = 1;
            if (lookBackUpon(board, board2, word, 0, coordinate)) {
                return true;
            }
        }
        return false;
    }
    // 有前路径则，从前路径依据上右下左顺时针来寻找
    // 没有前路径，在从上开始
    public boolean lookBackUpon(char[][] board, int[][] board2, String word, int i, int[] current) {
        if (i == word.length() - 1) {
            return true;
        }
        int x = current[0];
        int y = current[1];
        // 上
        if (x-1 >= 0 && board2[x-1][y] == 0 && board[x - 1][y] == word.charAt(i+1)) {
            board2[x-1][y] = 1;
            if (lookBackUpon(board, board2, word, i + 1, new int[]{x - 1, y})) {
                return true;
            } else {
                board2[x-1][y] = 0;
            }
        }
        // 右
        if (y+1 <= board[0].length - 1 && board2[x][y+1] == 0 && board[x][y+1] == word.charAt(i+1)) {
            board2[x][y+1] = 1;
            if (lookBackUpon(board, board2, word, i + 1, new int[]{x, y + 1})) {
                return true;
            } else {
                board2[x][y+1] = 0;
            }
        }
        // 下
        if (x+1 <= board.length - 1 && board2[x+1][y] == 0 && board[x+1][y] == word.charAt(i+1)) {
            board2[x+1][y] = 1;
            if (lookBackUpon(board, board2, word, i + 1, new int[]{x + 1, y})) {
                return true;
            } else {
                board2[x+1][y] = 0;
            }
        }
        // 左
        if (y-1 >= 0 && board2[x][y-1] == 0 && board[x][y-1] == word.charAt(i+1)) {
            board2[x][y-1] = 1;
            if (lookBackUpon(board, board2, word, i + 1, new int[]{x, y - 1})) {
                return true;
            } else {
                board2[x][y-1] = 0;
            }
        }
        // 上右下左都没有，则返回false
        return false;
    }
}
