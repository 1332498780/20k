package cn.hzy.demo.thread;

public class ThreadOrder {

    public static void main(String[] args) {
        Integer val = Integer.valueOf(100);

        MyThreadOrder A = new MyThreadOrder(val);
        A.setName("A");
        MyThreadOrder B = new MyThreadOrder(val);
        B.setName("B");
        MyThreadOrder C = new MyThreadOrder(val);
        C.setName("C");

        A.setMyNext(B);
        B.setMyNext(C);
        C.setMyNext(A);

        MyThreadOrder.next = A;

        A.start();
//        B.start();
//        C.start();
    }
}
class MyThreadOrder extends Thread{

    public static MyThreadOrder next;

    private Integer val;
    private MyThreadOrder myNext;
    public MyThreadOrder(Integer val){
        this.val = val;
    }

    public void setMyNext(MyThreadOrder myNext){
        this.myNext = myNext;
    }

    @Override
    public void run() {
        while (true){
            synchronized (val){
                if(this != next){
                    notifyAll();
                    continue;
                }
//                next = myNext;
                if(val<=0){
                    notifyAll();
                    break;
                }
                System.out.println(Thread.currentThread().getName()+" "+val--);
                try {
                    wait();
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

