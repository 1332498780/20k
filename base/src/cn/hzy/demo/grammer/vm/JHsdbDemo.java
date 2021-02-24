package cn.hzy.demo.grammer.vm;

public class JHsdbDemo {

    private static class InnerClass{
        static Holder staticHolder = new Holder();
        Holder holderInstance = new Holder();
        InnerClass(){}

        void foo(){
            Holder ic = new Holder();
            System.out.println(ic);
        }
    }

    static class Holder{
        int age = 10;
        String name = "zs";
    }

    public static void main(String[] args){
        InnerClass innerClas = new InnerClass();
        innerClas.foo();
    }
}
