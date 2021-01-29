package offer;

import java.util.*;

/**
 * 找出数组中重复的数字
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 思路：
 * 依据数组数据的特点来针对性的解题
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内
 * 假如数组是有序的，那么数组下标就是该下标中的值，如果不是就存在重复
 * 遍历数组，把对应的值放到对应的下标里，如果该下标下已有正确位置的值，则说明重复
 * 时间复杂度O(n),空间复杂度O(1)
 *
 * 双重循环：    时间复杂度：n-1+n-2+...+1 => O(n^2),  空间复杂度O(1)
 * 排序+遍历：   时间复杂度：nlogn + n => O(nlogn),    空间复杂度O(1)
 * 散列表：      时间复杂度：n， 空间复杂度n =>O(n),    空间复杂度O(n)
 */
public class FindRepeatNumberInArray_03 {
    public static void main(String[] args) {
        int r = new FindRepeatNumberInArray_03().
                findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
        System.out.println(r);
    }

    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：46.2 MB, 在所有 Java 提交中击败了 65.29% 的用户
    public int findRepeatNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }
        // [2, 3, 1, 0, 2, 5, 3]
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                int t = nums[i];
                if (t == nums[t]) {
                    return t;
                }
                int swap = nums[t];
                nums[t] = t;
                nums[i] = swap;
            }
        }
        return -1;
    }

    // 排序+遍历
    // 执行用时：3 ms, 在所有 Java 提交中击败了 58.08% 的用户
    // 内存消耗：46.2 MB, 在所有 Java 提交中击败了 63.36% 的用户
    public int findRepeatNumber_sort(int[] nums) {
        if (nums == null) {
            return -1;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return nums[i];
            }
        }
        return -1;
    }

    // 散列表
    // 执行用时：4 ms, 在所有 Java 提交中击败了 49.80% 的用户
    // 内存消耗：48.7 MB, 在所有 Java 提交中击败了 5.15% 的用户
    public int findRepeatNumber_hash(int[] nums) {
        if (nums == null) {
            return -1;
        }
        HashSet<Integer> integers = new HashSet<>(nums.length);
        for (int i : nums) {
            if (integers.add(i) == false) {
                return i;
            }

        }
        return -1;

    }

    // 双重循环_超出时间限制
    public int findRepeatNumber_doubleLoop_overtime(int[] nums) {
        if (nums == null) {
            return -1;
        }
        for (int i=0; i<nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return -1;
    }
}
