package h202007;

public class SumOfTwoNum {
    public static void main(String[] args) {
        SumOfTwoNum two = new SumOfTwoNum();
        int[] res = two.twoSum(new int[]{2,7,11,15}, 26);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }

    // nums = [2, 7, 11, 15], target = 9
    // 双层循环遍历，类似冒泡排序
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length -1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{};
    }
}
