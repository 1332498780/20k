package test;

public class Naixue {
    public static void main(String[] args) {
        // 10个
        Naixue naixue = new Naixue();
        int[] ints = naixue.insertSort(new int[]{5, 1, 4, 6, 3, 4, 7, 8, 9, 0});
        for (int i : ints) {
            System.out.println("i = " + i);
        }
    }
    public int[] insertSort(int[] arrs) {
        int k =0;
        for (int i = 1; i < arrs.length; i++) {
            int tmp = arrs[i];
            int j = k;
            for (; j >= 0; j--) {
                if (tmp < arrs[j]) {
                    // 后移
                    arrs[j+1] = arrs[j];
                } else {
                    break;
                }
            }
            arrs[j+1] = tmp;
            k++;
        }
        return arrs;
    }
}
