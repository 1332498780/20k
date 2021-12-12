package offer;

/**
 * 剪绳子
 * 思想：
 * 动态规划
 */
public class CuttingRope_14 {
    public static void main(String[] args) {
        int max = new CuttingRope_14().cuttingRope(15);
        System.out.println(max);
    }

    // 贪婪算法
    // 每次尽量剪长度为3的段，
    // 余1的话，最后一段不剪，分成2*2
    // 余2的话，就不
    // 时间复杂度O(1),空间复杂度O(1)
    public int cuttingRope(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        int timeOf3 = n / 3;
        int remainder = n % 3;
        if (remainder == 1) {
            timeOf3 -= 1;
            return (int)Math.pow(3, timeOf3) * (2 * 2);
        } else if (remainder == 2) {
            return (int)Math.pow(3, timeOf3) * 2;
        } else {
            return (int)Math.pow(3, timeOf3);
        }
    }

    public int cuttingRope3(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        int timeOf3 = n / 3;
        int remainder = n % 3;
        if (remainder == 1) {
            timeOf3 -= 1;
            return (int)Math.pow(3, timeOf3) * (2 * 2);
        } else if (remainder == 2) {
            return (int)Math.pow(3, timeOf3) * 2;
        } else {
            return (int)Math.pow(3, timeOf3);
        }
    }

    // 从下往上，既然每个问题都必不可少的要依赖子问题，那就先从子问题着手
    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：35.1 MB, 在所有 Java 提交中击败了 83.32% 的用户
    public int cuttingRope2(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }

        int[] res = new int[n+1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;
        for (int i = 4; i <= n; i++) {
            int max = 0;
            for (int j = i / 2; j >=1; j--) {
                int t = res[j] * res[i-j];
                if (t > max) {
                    max = t;
                } else {
                    break;
                }
            }
            res[i] = max;
        }
        return res[n];
    }



    // 类似斐波那契数列，从上往下，分解的子问题重复太多了
    // 执行用时：99 ms, 在所有 Java 提交中击败了 9.60% 的用户
    // 内存消耗：38.1 MB, 在所有 Java 提交中击败了 5.26% 的用户
    public int cuttingRope1(int n) {
        if (n < 2) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        return subProblem(n);
    }

    public int subProblem(int n){
        if (n <= 3) {
            int[] base = new int[]{0,1,2,3};
            return base[n];
        }
        int y = n / 2;
        int x = n - y;
        int r2 = 0;
        while (x - 1 > 0) {
            int r1;
            if (r2 != 0) {
                r1 = r2;
            } else if (x == y) {
                r1 = subProblem(x);
                r1 = r1 * r1;
            } else {
                r1 = subProblem(x) * subProblem(y);
            }
            x = x - 1;
            y = y + 1;
            if (x == y - 1) {
                r2 = r1;
                continue;
            } else {
                r2 = subProblem(x) * subProblem(y);
            }
            if (r2 < r1) {
                return r1;
            }
        }
        return r2;
    }


}
