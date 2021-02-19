package cn.hzy.demo.grammer;

public class InnerClassDemo {

    private static int height = 2;
    private int width = 2;

    public void test(){
        InnerClass2.test();
        InnerClass2 ic2 = new InnerClass2();
        ic2.sex++;
    }

    public static void main(String[] args){

        InnerClass2.sex++;
        InnerClassDemo icd = new InnerClassDemo();
        icd.height++;

        icd.testFinal();
    }

    public Thread testFinal(){
        int a = 1;
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                //idea自动会在内部类的外部变量中加final
//                a++;
                width = a;
            }
        });
        return thread;
    }


    public class InnerClass1{

        private int age;
//        public static String name = "zs";

//        public static void test1(){}
        public void test(){
            System.out.println(height);
            System.out.println(width);
        }
    }

    public static class InnerClass2{
        public static int sex = 1;

        public String token = "abc";

        public static void test(){}

        private InnerClass2(){}

        public static void test1(){
            System.out.println(height);
//            System.out.println(width);
        }
    }

}
