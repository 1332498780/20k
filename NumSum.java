import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class NumSum {

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        int[] res = new int[2];
        for(int i=0; i<length;i++){
            for(int j=i+1;j<length;j++){
                if(nums[i]+nums[j]==target){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }

}
