package leetcode;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 */
public class MaxIncrLengthSubArray_300 {

    public static void main(String[] args) {
        MaxIncrLengthSubArray_300 main = new MaxIncrLengthSubArray_300();
        // [0,3,1,6,2,2,7] [3,6,2,7] 4
        int[] nums1 = {0,3,1,6,2,2,7};
        System.out.println(main.lengthOfLIS(nums1) == 4);
        // [10,9,2,5,3,7,101,18] [2,3,7,101] 4
        int[] nums2 = {10,9,2,5,3,7,101,18};
        System.out.println(main.lengthOfLIS(nums2) == 4);
        // [0,1,0,3,2,3] [0,1,2,3] 4
        int[] nums3 = {0,1,0,3,2,3};
        System.out.println(main.lengthOfLIS(nums3) == 4);
        // [7,7,7,7,7,7,7] [7] 1
        int[] nums4 = {7,7,7,7,7,7,7};
        System.out.println(main.lengthOfLIS(nums4) == 1);
    }

    public int lengthOfLIS(int[] nums) {

        return 0;
    }
}
