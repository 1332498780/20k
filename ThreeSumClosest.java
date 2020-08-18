/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

import java.util.Arrays;

/**
 *
 * @author ZCG04000034
 */
public class ThreeSumClosest {
    
    public static void main(String[] args) {
        int res = new ThreeSumClosest().threeSumClosest(new int[]{-1,2,1,-4}, 1);
        System.out.println(res);
    }
    
    //又是排序加双指针呀！！！
    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int res = 0;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            int j = i+1;
            int end = nums.length -1;
            while(end>j){
                int sums = nums[i]+nums[j]+nums[end];
                if(Math.abs(sums-target) < min){
                    min = Math.abs(sums-target);
                    res = sums;
                }
                
                if(sums>target){
                    end--;
                }else if(sums<target){
                    j++;
                }else{
                    return target;
                }
            }
        }
        return res;
    }
    
}
