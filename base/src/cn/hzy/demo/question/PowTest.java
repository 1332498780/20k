package cn.hzy.demo.question;

public class PowTest {

    public static void main(String[] args) {
        System.out.println(pow1(2,5));

    }

    /***
     * 特殊值的考虑：base = 0,
     *              enpand= 0,负数
     * @param base
     * @param expand
     */
    public static double pow(double base,int expand){
        if(base == 0){
            return 0;
        }
        double result = 1.0;
        expand = Math.abs(expand);
        for(int i=0;i<expand;i++){
            result *= base;
        }
        if(expand < 0){
            result = 1.0 / result;
        }
        return result;
    }

    /***
     * 优化
     * @param base
     * @param expand
     * @return
     */
    public static double pow1(double base,int expand){
        if(base == 0){
            return 0;
        }
        double result = 1.0;
        boolean flag = expand > 0 ;
        expand = Math.abs(expand);
        if(expand == 0){
            return 1;
        }

        int target = 0;
        if((expand & 1) == 1){
            //奇数
            target = (expand - 1)/2;
            int i=1;
            double a1 = base;
            while(i<target){
                a1 = a1 * a1 * base;
                i += 2;
            }
            result = a1;
        }else{
            target = expand / 2;
            double a2 = base * base;
            int i=2;
            while(i<target){
                a2 = a2 * a2;
                i += 2;
            }
            result = a2;
        }

        if(!flag){
            result = 1.0 / result;
        }
        return result;
    }
}
