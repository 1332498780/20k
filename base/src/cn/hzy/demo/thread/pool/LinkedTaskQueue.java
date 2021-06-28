package cn.hzy.demo.thread.pool;

import java.util.LinkedList;

public class LinkedTaskQueue implements TaskQueue{

    private final int limit;
    private LinkedList<Runnable> tasks = new LinkedList<>();
    private final DenyPolicy denyPolicy;
    private final ThreadPool threadPool;

    public LinkedTaskQueue(int limit,DenyPolicy policy,ThreadPool pool){
        this.limit = limit;
//        this.tasks = list;
        this.denyPolicy = policy;
        this.threadPool = pool;
    }

    @Override
    public void offer(Runnable runnable) {
        synchronized (tasks){
            if(tasks.size()>=limit){
                denyPolicy.reject(runnable,threadPool);
                return;
            }
            tasks.add(runnable);
            tasks.notifyAll();
        }
    }

    @Override
    public Runnable get() throws InterruptedException {
        synchronized (tasks){
            while(tasks.isEmpty()){
                try {
                    tasks.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
            return tasks.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (tasks){
            return tasks.size();
        }
    }
}
