package cn.hzy.demo.thread;

/***
 * 实现A，B，C 3个线程按照顺序依次打印
 */
public class ThreadOrder {

    public static void main(String[] args) {

        MyThreadOrder A = new MyThreadOrder();
        A.setName("A");
        MyThreadOrder B = new MyThreadOrder();
        B.setName("B");
        MyThreadOrder C = new MyThreadOrder();
        C.setName("C");

        A.setMyNext(B);
        B.setMyNext(C);
        C.setMyNext(A);

        MyThreadOrder.next = A;

        A.start();
        B.start();
        C.start();
    }
}
class MyThreadOrder extends Thread{

    public static MyThreadOrder next;
    public static Integer val = 10;
    private MyThreadOrder myNext;
    private static Object object = new Object();

    public void setMyNext(MyThreadOrder myNext){
        this.myNext = myNext;
    }

    @Override
    public void run() {
        while (true){
            synchronized (object){
                System.out.println(Thread.currentThread().getName());
                while(this != next){
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                next = myNext;
                object.notifyAll();
                if(val<=0){
                    break;
                }
                System.out.println(Thread.currentThread().getName()+" "+val--);
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


class Runner1 implements Runnable{
    private int num = 2;
    private String cruu = "A";

    @Override
    public void run() {
        while(true){
            synchronized (this){
                String tName = Thread.currentThread().getName();
                if(tName.equals(cruu)){
                    notify();  //不会立刻释放锁
                    if(num == 0){
                        break;
                    }

                    try {

                        wait(); //释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

