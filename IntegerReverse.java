/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hh;

/**
 *
 * @author ZCG04000034
 */
public class IntegerReverse {
    public static void main(String[] args) {
//        int a = -27;
//        Integer inte = new Integer(a);
//        long l = inte.longValue();
//        System.out.println(l);
        int a = -9000;
        System.out.println(new IntegerReverse().reverse1(a));
    }
    
    public int reverse(int x) {
         Integer inte = new Integer(x);
         boolean positiveFlag = true;
         StringBuilder sb = new StringBuilder(inte.toString());
         if(sb.toString().startsWith("-")){
             positiveFlag = false;
             sb = sb.deleteCharAt(0);
         }
         sb = sb.reverse();
         long l = Long.valueOf(sb.toString());
         if(!positiveFlag)
             l = -l;
         if(l < -Math.pow(2, 31)||l>Math.pow(2, 31)-1)
             return 0;
        return new Long(l).intValue();
    }
    
    public int reverse1(int x){
        int ans = 0;
        while(x!=0){
            //最优方案，巧妙的判断溢出
            if((ans * 10) / 10 != ans)
                return 0;
            //官方的解答：-2^31=-2147483648 2^31-1=2147483647
//            if(ans>Integer.MAX_VALUE/10&&(x%10)>7)return 0;
//            if(ans<Integer.MIN_VALUE/10&&(x%10)<-8)return 0;
            ans = ans*10 + x%10;
            x /= 10;
        }
        return ans;
    }
}
