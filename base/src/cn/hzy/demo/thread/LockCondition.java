package cn.hzy.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCondition {

    private static List<Integer> list = new ArrayList<>();
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args){
        Consumer consumer = new Consumer(list);
        Producer producer = new Producer(list);
        producer.start();
        consumer.start();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
             producer.stopMe();
        }
    }

    static class Consumer extends Thread{

        private List<Integer> list;
        private boolean isInterupt;
        public Consumer(List<Integer> list){
            this.list = list;
            super.setName("consumer");
        }
        public void stopMe(){
            this.isInterupt = true;
        }

        @Override
        public void run() {
            while(!isInterupt){
                    lock.lock();
                    if (list.isEmpty()) {
                        condition.signal();
                    } else {
                        System.out.println("消费者消费了：" + list.remove(0));
                    }
                    lock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer extends Thread{

        private List<Integer> list;
        private boolean isInterupt;
        public Producer(List<Integer> list){
            this.list = list;
            super.setName("producer");
        }
        public void stopMe(){
            this.isInterupt = true;
        }

        @Override
        public void run() {
            while(!isInterupt){
                    lock.lock();
                    if (list.size() > 7) {
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Random random = new Random();
                        int res = random.nextInt(10);
                        list.add(res);
                        System.out.println("生产者生产了：" + res);
                    }
                    lock.unlock();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
