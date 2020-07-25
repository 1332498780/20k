public class MediaSortArrays {

    public static void main(String[] args){
        System.out.println(new MediaSortArrays().findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = 0;
        int len2 = 0;
        boolean odd = true;
        if (nums1 != null)
            len1 += nums1.length;
        if (nums2 != null)
            len2 += nums2.length;

        if ((len1 + len2) % 2 == 0) {
            odd = false;
        }

        int counter = 0;
        int[] tempArr = new int[((len1 + len2)) / 2+1];
        int index1 = 0;
        int index2 = 0;
        while (len1 > 0 && len2 > 0) {
            if (counter < tempArr.length) {
                if (nums1[index1] > nums2[index2]) {
                    tempArr[counter++] = nums2[index2];
                    len2--;
                    index2++;
                } else {
                    tempArr[counter++] = nums1[index1];
                    len1--;
                    index1++;
                }
            } else {
                if (odd)
                    return tempArr[counter - 1];
                else
                    return (tempArr[counter - 1] + tempArr[counter - 2]) / 2.0;
            }
        }
        if (counter < tempArr.length) {
            int minus = tempArr.length - counter;

            if (len1 > 0)
                for (int i = 0; i < minus; i++) {
                    tempArr[counter++] = nums1[index1++];
                }
            if (len2 > 0)
                for (int i = 0; i < minus; i++) {
                    tempArr[counter++] = nums2[index2++];
                }
            if (odd)
                return tempArr[tempArr.length - 1];
            else
                return (tempArr[tempArr.length - 1] + tempArr[tempArr.length - 2])/2.0;
        } else {
            if (odd)
                return tempArr[counter - 1];
            else
                return (tempArr[counter - 1] + tempArr[counter - 2]) / 2.0;
        }
    }
}
