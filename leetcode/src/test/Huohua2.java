package test;

import java.util.LinkedList;
import java.util.List;

public class Huohua2 {
    // 数组M=[1,2,3,4,5,6,15,20,30,40,50,...139...]
    // 求和=N的组合，不限制组内个数，找出组合有多少？
    public static void main(String[] args) {
        Huohua2 huohua2 = new Huohua2();
        int[] arrs = new int[]{1,5,15,20,30,49};
        huohua2.recursion(arrs, 0, 50, new LinkedList<>());
        System.out.println(result);
    }

    public static List<List<Integer>> result = new LinkedList<>();
    public void recursion(int[] arrs, int left, int n, List<Integer> temp) {
        if (n<0){
            return;
        }
        if (n==0){
            result.add(new LinkedList<>(temp));
        }else {
            for (int i=left;i<arrs.length;i++){
                temp.add(arrs[i]);
                recursion(arrs,i+1, n-arrs[i],temp);
                temp.remove(temp.size()-1);
            }
        }
    }
}