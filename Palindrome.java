public class Palindrome {
//    public boolean isPalindrome(int x) {
//        if(x < 0)
//            return false;
//        int len = String.valueOf(x).length();
//        int y = x;
//        while(x != 0 && len > 1){
//            if(x%10 != y/((len-1)*10))
//                return false;
//            x /= 10;
//            y = y%((len-1)*10);
//            len--;
//        }
//        return true;
//    }

    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        String str = String.valueOf(x);
        int len = str.length();
        int half = len/2;
        for(int i = 0; i<half;i++){
            if(str.charAt(i) != str.charAt(len-1-i))
                return false;
        }
        return true;
    }
}
