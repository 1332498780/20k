package cn.hzy.demo.thread;

public class StaticParamTest {

    private static StaticParamTest staticParamTes = new StaticParamTest();

//    static {
//        x = 10;
////        System.out.println(x);
//    }
    private static int x=0;
    private static int y;


    private StaticParamTest(){
        x++;
        y++;
    }


    private static void test(){
        System.out.println(x);
        System.out.println(y);
    }
    private static StaticParamTest getInstance(){
        return staticParamTes;
    }

    public static void main(String[] args) {
        StaticParamTest s = getInstance();
        System.out.println(s.x);
        System.out.println(s.y);
    }



//    private static byte[] bytes = new byte[1024*1024*15];
//
//    public static void main(String[] args) {
//        try {
//            TimeUnit.SECONDS.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}


