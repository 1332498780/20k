package cn.hzy.demo.question;

import java.util.Arrays;

/***
 * 对公司所有的员工年龄进行排序，要求时间复杂度O(n)
 *
 * 说明：借助了一个辅助空间，利用数据特点：范围小，大量重复，使用数组下标作为年龄的排序，下标对应的值保存出现次数，最后循环输出结果
 */
public class AgeSort {

    public static void main(String[] args) {
        int[] ages = {21,20,19,35,42,53,23,18,16,30,31,22};
        sort(ages);
        System.out.println(Arrays.toString(ages));
    }


    public static void sort(int[] ages){

        //辅助空间
        int[] ageTimes = new int[100];

        //统计每个年龄的次数
        for(int i=0;i<ages.length;i++){
            int age = ages[i];
            ageTimes[age] = ageTimes[age]+1;
        }
        int j=0;
        for(int k=0;k<ageTimes.length;k++){
            for(int z=0;z<ageTimes[k];z++){
                ages[j++] = k;
            }
        }
    }

}
