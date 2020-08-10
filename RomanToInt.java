import java.util.Arrays;
import java.util.List;

public class RomanToInt {

    public static void main(String[] args){
        System.out.println(new RomanToInt().romanToInt("MCMXCIV"));
    }

    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public int romanToInt(String s) {
        int res = 0;
        List<String> list = Arrays.asList(symbols);
        for(int i=0;i<s.length();){
            if(i+2<=s.length()){
                int index = list.indexOf(s.substring(i,i+2));
                if(index!=-1){
                    res += values[index];
                    i+=2;
                }else{
                    res += values[list.indexOf(s.substring(i,i+1))];
                    i+=1;
                }
            }else if(i+1<=s.length()){
                res += values[list.indexOf(s.substring(i,i+1))];
                i+=1;
            }
        }
        return res;
    }
}
