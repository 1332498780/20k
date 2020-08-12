import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public static void main(String[] args){
        System.out.println(new ThreeSum().threeSum(new int[]{0,0,0,0}));
    }
    //排序后双指针
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            int target = -nums[i];
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int j=i+1;
            int k=nums.length-1;
            //双指针移动，j从左往右移动，k从右往左
            while(j<k){
                if(nums[j]+nums[k]==target){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j++;
                    while(j<k&&nums[j]==nums[j-1]) j++;  //如果j++之后的值和前一次相等，那就循环移动，直到遇到不等的
                    k--;
                    while(j<k&&nums[k]==nums[k+1]) k--;  //如果k--之后的值和前一次相等，那就循环移动，直到遇到不等的
                }else if(nums[j]+nums[k]>target){  // 如果和大于target就让k变小
                    k--;
                    while(j<k&&nums[k]==nums[k+1]) k--;
                }else{ // 如果和小于target就让j变大
                    j++;
                    while(j<k&&nums[j]==nums[j-1]) j++;
                }
            }
        }
        return res;
    }
}
