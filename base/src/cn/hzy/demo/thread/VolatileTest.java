package cn.hzy.demo.thread;

public class VolatileTest {

    public static void main(String[] args) {
        VolatileThread vt = new VolatileThread();
        vt.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        vt.setStop(true);
    }
}

class VolatileThread extends Thread{

    private boolean isStop = false;

    public void setStop(boolean isStop){
        this.isStop = true;
    }

    @Override
    public void run() {
        System.out.println("开始执行");
        while(!isStop){
            System.out.println("疯狂输出");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("结束");
    }
}
