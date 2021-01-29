package sort;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] ints = bubbleSort.insertSort(new int[]{3, 5, 4, 1, 2, 6});
        for (int i : ints) {
            System.out.println(i);
        }
    }

    /**
     * 插入排序
     * 1、假定遍历过的数列已有序，拿每个元素去找其已遍历有序数列的相应位置
     * 2、依次向后挪动元素，然后把该元素放到相应位置。
     * 3、接着遍历下个元素，执行以上1、2两步，直到遍历结束
     */
    public int[] insertSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int t = a[i];
            int j = i - 1;
            while (j >= 0 && t < a[j]) {
                a[j+1] = a[j];
                j--;
            }
            if (j != i-1) {
                a[j+1] = t;
            }
        }
        return a;
    }

    /**
     * 冒泡排序
     * 每次遍历，相邻元素间比较大小，按有序方向交换
     * 这样每次遍历至少会有一个元素放在正确位置
     * 技巧：不用遍历n次，如果某次遍历无交换发生，说明没有可交换的元素，证明已有序
     */
    public int[] bubbleSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean flag = false;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    flag = true;
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
            // 无交换则证明已有序
            if (!flag) {
                break;
            }
        }
        return a;
    }
}
