package offer;

/**
 * 机器人的运动范围
 * 思想：回溯法
 */
public class RobotMovingRange_013 {
    public static void main(String[] args) {
        int res = new RobotMovingRange_013().movingCount(3,1,0);
        System.out.println(res);
    }

    // 执行用时：3 ms, 在所有 Java 提交中击败了 34.73% 的用户
    // 内存消耗：36.4 MB, 在所有 Java 提交中击败了 14.49% 的用户

    // 回溯法，所经之处标记1，然后统计1的个数
    public int movingCount(int m, int n, int k) {
        int[][] board = new int[m][n];
        board[0][0] = 1;
        move(board, k, new int[]{0,0});
        int range = 0;
        // 遍历board,累加走过路径，得范围
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    range++;
                }
            }
        }
        return range;
    }

    // 上右下左
    public void move(int[][] board, int k, int[] curCoordinate) {
        int x = curCoordinate[0];
        int y = curCoordinate[1];
        // 上
        if (x - 1 >= 0 && inRange(x - 1, y, k) && board[x - 1][y] == 0) {
            board[x - 1][y] = 1;
            move(board,k,new int[]{x-1,y});
        }
        // 右
        if (y + 1 < board[0].length && inRange(x, y+1, k) && board[x][y+1] == 0) {
            board[x][y+1] = 1;
            move(board,k,new int[]{x,y+1});
        }
        // 下
        if (x + 1 < board.length && inRange(x + 1, y, k) && board[x + 1][y] == 0) {
            board[x + 1][y] = 1;
            move(board,k,new int[]{x+1,y});
        }
        // 左
        if (y-1 >= 0 && inRange(x, y-1, k) && board[x][y-1] == 0) {
            board[x][y-1] = 1;
            move(board,k,new int[]{x,y-1});
        }
    }

    public boolean inRange(int x, int y, int k) {
        int sum = 0;
        while (x > 0) {
            sum = sum + x % 10;
            x = x / 10;
        }
        while (y > 0) {
            sum = sum + y % 10;
            y = y / 10;
        }
        return sum <= k;
    }
}
