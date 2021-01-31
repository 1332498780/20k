package binarysearch;

/**
 * 搜索旋转排序数组
 *
 */
public class FindInRotateArrays_33 {
    public static void main(String[] args) {
//        int search = new FindInRotateArrays_33().search(
//                new int[]{4,5,6,7,0,1,2}, 0);
        int search = new FindInRotateArrays_33().search(
                new int[]{5,1,3}, 5);
        System.out.println(search);

    }

    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：37.8 MB, 在所有 Java 提交中击败了 73.24% 的用户
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length -1;
        while (low <= high) {
            int mid = low + ((high-low) >> 1);
            if (nums[mid] == target) {
                return mid;
            }
            // mid在第一段
            if (nums[mid] >= nums[0]) {
                // target在区间[0,mid-1]
                if (target >= nums[0] && target < nums[mid]) {
                    high = mid -1;
                } else {    // target在区间[mid+1,length-1]
                    low = mid + 1;
                }
            } else {    // mid在第二段
                // target在区间[mid+1,length-1]
                if (target > nums[mid] && target <= nums[nums.length - 1]) {
                    low = mid +1;
                } else {    // target在区间[0,mid-1]
                    high = mid -1;
                }
            }
        }
        return -1;
    }

    // 执行用时：1 ms, 在所有 Java 提交中击败了 11.29% 的用户
    // 内存消耗：37.8 MB, 在所有 Java 提交中击败了 64.31% 的用户
    public int search1(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length -1;
        while (low <= high) {
            int mid = low + ((high-low) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // mid在第二段，target在第一段
                if (nums[mid] < nums[0] && target >= nums[0]) {
                    high = mid - 1;
                } else {    // mid与target在同一段
                    low = mid + 1;
                }
            } else {
                // mid在第一段，target在第二段
                if (nums[mid] >= nums[0] && target < nums[0]) {
                    low = mid + 1;
                } else {    // mid与target在同一段
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

}
