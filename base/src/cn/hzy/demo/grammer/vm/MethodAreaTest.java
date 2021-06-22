package cn.hzy.demo.grammer.vm;

/***
 * 探究方法区都存哪些东西
 *
 * -XX:MaxMetaspaceSize=256m
 */
public class MethodAreaTest {

    private byte[] bytes = new byte[1024];


    public static void main(String[] args){
        new MethodAreaTest();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

//    public void a(String[] arg){}
//    public void b(String[] arg){}
//    public void c(String[] arg){}
//    public void d(String[] arg){}
//    public void e(String[] arg){}
//    public void f(String[] arg){}
//    public void g(String[] arg){}
}
