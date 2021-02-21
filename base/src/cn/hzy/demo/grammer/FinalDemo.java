package cn.hzy.demo.grammer;

public class FinalDemo {

    final String str1;
    final String str2;
    final String str3 = "java";
    {
        str1 = "java";
    }

    public FinalDemo(){
        str2 = "java";
    }

    public static void main(String[] args){
        new FinalDemo().test();
    }

    public void test(){
        System.out.println((str1+str1) == "javajava");
        System.out.println((str2+str2) == "javajava");
        System.out.println((str3+str3) == "javajava");
    }
}
