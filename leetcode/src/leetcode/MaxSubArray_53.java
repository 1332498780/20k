package leetcode;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 */
public class MaxSubArray_53 {

    public static void main(String[] args) {
        MaxSubArray_53 main = new MaxSubArray_53();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = main.maxSubArraySumDp(nums);
        System.out.println(i == 6);
    }

    /**
     * 动态规划
     */
    public int maxSubArraySumDp(int[] nums) {
        int max = nums[0];
        int preMax = 0;
        for (int x : nums) {
            preMax = Math.max(x, x + preMax);
            max = Math.max(max, preMax);
        }
        return max;
    }
}
