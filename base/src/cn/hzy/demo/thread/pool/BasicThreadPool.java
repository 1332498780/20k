package cn.hzy.demo.thread.pool;

import javafx.concurrent.Task;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class BasicThreadPool extends Thread implements ThreadPool{

    private final int maxSize;
    private final int coreSize;
    private final int initSize;
    private int activeSize;

    private boolean isShutdown = false;
    private final ThreadFactory threadFactory;
    //任务队列
    private final TaskQueue taskQueue;
    //工作线程
    private final Queue<TaskQueue> threadQueue = new ArrayDeque<>();
    private final DenyPolicy denyPolicy;

    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    public BasicThreadPool(int maxSize,int coreSize,int initSize,int taskSize){
        this(maxSize,coreSize,initSize,taskSize,Thread::new,new DenyPolicy.DiscardDenyPolicy(),10,TimeUnit.SECONDS);
    }

    public BasicThreadPool(int maxSize,int coreSize,int initSize,int taskSize
            ,ThreadFactory threadFactory,DenyPolicy denyPolicy,long keepAliveTime,TimeUnit timeUnit){
        this.maxSize= maxSize;
        this.coreSize = coreSize;
        this.initSize = initSize;
        this.threadFactory = threadFactory;
        this.denyPolicy = denyPolicy;
        this.taskQueue = new LinkedTaskQueue(taskSize,denyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
    }


    @Override
    public void execute(Runnable runnable) {

    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public void shutDown() {

    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public int getActiveSize() {
        return 0;
    }
}
