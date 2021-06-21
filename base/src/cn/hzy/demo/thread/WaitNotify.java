package cn.hzy.demo.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaitNotify {

    private static List<Integer> list = new ArrayList<>();


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
                synchronized (list) {
                    if (list.isEmpty()) {
                        list.notify();
                    } else {
                        System.out.println("消费者消费了：" + list.remove(0));
                    }
                }
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
                synchronized (list) {
                    if (list.size() > 7) {
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Random random = new Random();
                        int res = random.nextInt(10);
                        list.add(res);
                        System.out.println("生产者生产了：" + res);
                    }
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }
    }
}
