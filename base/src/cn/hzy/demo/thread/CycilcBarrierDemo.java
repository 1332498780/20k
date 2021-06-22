package cn.hzy.demo.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;

public class CycilcBarrierDemo {

    static CyclicBarrier cyclicBarrier;

    static class Soldier implements Runnable{

        CyclicBarrier cyclicBarrier;
        String name;
        public Soldier(CyclicBarrier cyclicBarrier,String name){
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
                dowork();
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        public void dowork(){
            try {
                Thread.sleep(new Random().nextInt(10)*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+" 任务完成");
        }
    }

    static class BarrierPoint implements Runnable{

        boolean flag;
         int count;
        public BarrierPoint(int count){
            this.count = count;
        }
        @Override
        public void run() {
            if(flag){
                System.out.println("司令：【"+count+"】 个任务完成");
            }else{
                flag= true;
                System.out.println("司令：【"+count+"】 个到位");
            }
        }
    }

    public static void main(String[] args){
        cyclicBarrier = new CyclicBarrier(10,new BarrierPoint(10));
        for(int i=0;i<10;i++){
            Thread thread = new Thread(new Soldier(cyclicBarrier,i+""));
            thread.start();
            if(i==5){
                thread.interrupt();
            }
        }

    }
}
