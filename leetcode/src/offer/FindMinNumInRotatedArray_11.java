package offer;

/**
 * 旋转数组的最小数字
 * 思路
 * 各种判断
 */
public class FindMinNumInRotatedArray_11 {
    public static void main(String[] args) {
        int min = new FindMinNumInRotatedArray_11().minArray(
                new int[]{2,0,0,0,1,2}
        );
        System.out.println(min);
    }

    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：38.3 MB, 在所有 Java 提交中击败了 35.7% 的用户
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        // 如果无旋转，返回numbers[0]
        if (numbers[0] < numbers[numbers.length - 1]) {
            return numbers[0];
        }
        int low = 0;
        int high = numbers.length -1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            // mid在第二段有序数组上
            if (numbers[mid] < numbers[0]) {
                if (numbers[mid] < numbers[mid - 1]) {
                    return numbers[mid];
                } else {
                    high = mid - 1;
                }
            } else if (numbers[mid] >= numbers[0] && numbers[0] > numbers[high]) { // mid在第一段有序数组上
                if (numbers[mid] > numbers[mid + 1]) {
                    return numbers[mid+1];
                } else {
                    low = mid + 1;
                }
            } else {    // nums[mid] == nums[0]
                if (mid == 0) {
                    return numbers[0];
                }
                if (numbers[0] == numbers[numbers.length - 1]) {
                    // 循环遍历后返回
                    for (int i = 0; i < numbers.length - 2; i++) {
                        if (numbers[i] > numbers[i + 1]) {
                            return numbers[i + 1];
                        }
                    }
                    // 遍历结束未发现最小值，则证明全是最小值，返回数组里任意值即可
                    return numbers[0];
                }

            }
        }
        return -1;
    }

    // 执行用时：0 ms, 在所有 Java 提交中击败了 100.00% 的用户
    // 内存消耗：38.3 MB, 在所有 Java 提交中击败了 44.64% 的用户
    public int minArray1(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }
}
