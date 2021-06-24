package cn.hzy.demo.thread;

public class SaleTicket {

    public static void main(String[] args) {
        RunIt runIt = new RunIt();

        Thread t1 = new Thread(runIt,"1号窗口");
        Thread t2 = new Thread(runIt,"2号窗口");
        Thread t3 = new Thread(runIt,"3号窗口");

        System.out.println("售卖开始------->");
        t1.start();
        t2.start();
        t3.start();
    }


}

class RunIt implements Runnable{

    private int ticket = 100;

    @Override
    public void run() {
        while(true){
            synchronized (RunIt.class) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + " 售卖" + ticket + "号票");
                    ticket--;
                }else{
                    break;
                }
            }
        }
    }
}
