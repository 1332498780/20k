package base;

public class Volatile {

    private volatile int flag = 1;

    private void handle() {
        System.out.println("handle start");
        while (flag == 1) {

        }
        System.out.println("handle end");
    }

    public void setFlag(int flag) {
        this.flag = flag;

    }

    public static void main(String[] args) {
        Volatile v = new Volatile();
        new Thread(v::handle).start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.setFlag(0);
        System.out.println("修改 flag=0");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("exit...");
    }
}
