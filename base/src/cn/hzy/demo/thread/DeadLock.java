package cn.hzy.demo.thread;

public class DeadLock {

    static Integer a =1;

    public static void main(String[] args){
        Thread t = new Thread(new AThread());
        t.start();
    }
    static class AThread implements Runnable{
        @Override
        public void run() {
            synchronized (a){
                System.out.println("1");
                synchronized (a){
                    System.out.println("2");
                }
            }
        }

        private void doSomething(){

        }
    }


    static class Child extends Parent{
        @Override
        public synchronized void doSomething(){
            System.out.println("child");
        }
    }
    static class Parent{
        public synchronized void doSomething(){
            System.out.println("parent");
        }
    }
}
