package offer;

public class FindMinInRotatedArray_11 {
    public static void main(String[] args) {
        int min = new FindMinInRotatedArray_11().minArray1(
                new int[]{3,3,1,3}
        );
        System.out.println(min);
    }
    public int minArray(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        if (numbers[0] < numbers[numbers.length - 1]) {
            return  numbers[0];
        }
        int start = 0;
        int end = numbers.length -1;
        while (start <= end) {
            if (numbers[start] < numbers[end]) {
                return numbers[start];
            }
            int middle = (start + end) / 2;
            if (middle > 0 && numbers[middle] < numbers[middle -1]) {
                return  numbers[middle];
            }
            if (numbers[middle] >= numbers[start]) {
                if (numbers[middle] == numbers[end]) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            } else {
                end = middle - 1;
            }
        }
        return numbers[0];
    }

    public int minArray1(int[] numbers) {
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        return numbers[0];
    }
}
