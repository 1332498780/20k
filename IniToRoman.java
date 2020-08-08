public class IniToRoman {

    public static void main(String[] args){
        System.out.println(new IniToRoman().intToRoman(1994));
    }

    //自己研究的旁门左道
    //与官网的算法差之甚远，主要差的地方有：
    // 1. 我这个思路不可重用，就是只能解决当下问题，因题解题(可重用性)
    // 2. 如果罗马数字增加，我的逻辑就要跟着动，这样的后果是，无论是别人改起来，或者我自己改起来，即使有文档说明，也需要费时间读代码（延展性，可维护性）
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        char[] romanChar = {'I','V','X','L','C','D','M'};
        int count = 0;
        while(num!=0){
            int t = num % 10;
            num /= 10;
            if(t==4)
                sb.append(romanChar[count*2+1]).append(romanChar[count*2]);
            else if (t==9)
                sb.append(romanChar[count*2+2]).append(romanChar[count*2]);
            else{
                if(t>=5){
                    t -= 5;
                    while(t-->0)
                        sb.append(romanChar[count*2]);
                    sb.append(romanChar[count*2+1]);
                }else{
                    while(t-->0)
                        sb.append(romanChar[count*2]);
                }
            }
            count++;
        }
        return sb.reverse().toString();
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    //何为贪心算法？
    //官网的贪心算法
    //倒叙给出values，从最大到最小开始逐一用values匹配num，每次使用num-values得到剩下未匹配的num
    //可重用性与扩展性，与易读性 不言而喻
    public String intToRoman1(int num) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<values.length&& num>0;i++){
            //此处设循环的目的是：num当前值可能匹配多次同一个values[i]
            while(num>=values[i]){
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }
}
