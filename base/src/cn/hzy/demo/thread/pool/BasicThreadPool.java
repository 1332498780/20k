package cn.hzy.demo.thread.pool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/***
 * 自定义线程池。实现任务执行，控制线程数量，队列拒绝策略，关闭实现等功能。
 *
 *  有待优化：
 *  1. BasicThreadPool 暴露Thread方法。应该把继承该为组合方式
 *  2. 增加支持关闭线程池时返回还未执行的任务
 */
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
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();
    private final DenyPolicy denyPolicy;

    private final long keepAliveTime;
    private final TimeUnit timeUnit;

    public BasicThreadPool(int maxSize,int coreSize,int initSize,int taskSize){
        this(maxSize,coreSize,initSize,taskSize,Thread::new,new DenyPolicy.DiscardDenyPolicy(),1,TimeUnit.SECONDS);
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
        this.init();
    }

    private void init(){

        start();

        for(int i=0;i<initSize;i++){
            newThread();
        }
    }

    private void newThread(){
        InteralTask interalTask = new InteralTask(this.taskQueue);
        Thread thread = threadFactory.createThread(interalTask);
        ThreadTask threadTask = new ThreadTask(thread,interalTask);
        threadQueue.add(threadTask);
        this.activeSize++;
        thread.start();
    }
    private void removeThread(){
        this.activeSize--;
        ThreadTask ts = threadQueue.remove();
        ts.getInternalTask().stop();
    }

    @Override
    public void run(){
        while(!isShutdown && !Thread.currentThread().isInterrupted()){

            //每keepalive时间检查一次空闲线程的情况
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown=true;
                break;
            }

            if(isShutdown){
                break;
            }
            synchronized (this){
                System.out.println("taskSize:"+taskQueue.size());
                //1. 队列中有遗留任务，当前活跃线程未达到核心线程数
                if(taskQueue.size() >0 && activeSize < coreSize){
                    //扩容至coreSize
                    for(int i=activeSize+1;i<=coreSize;i++){
                        System.out.println("扩容至核心大小："+i);
                        newThread();
                    }
                    continue;
                }
                //2. 队列中有遗留任务，当前活跃线程未达到最大线程数
                if(taskQueue.size()>0 && activeSize < maxSize){
                    for(int i=coreSize+1;i<=maxSize;i++){
                        System.out.println("扩容至最大大小："+i);
                        newThread();
                    }
                }
                //3. 队列中无遗留任务，当前活跃线程大于核心线程数
                if(taskQueue.size()==0 && activeSize >= coreSize){
                    for(int i=activeSize;i>=coreSize;i--){
                        System.out.println("缩减至核心大小："+i);
                        removeThread();
                    }
                }
            }
        }
    }


    @Override
    public void execute(Runnable runnable) {
            if(isShutdown){
                throw new IllegalStateException();
            }
            taskQueue.offer(runnable);
    }

    @Override
    public int getMaxSize() {
        if(isShutdown){
            throw new IllegalStateException("threadpool has shutdown!");
        }
        return maxSize;
    }

    @Override
    public int getCoreSize() {
        if(isShutdown){
            throw new IllegalStateException("threadpool has shutdown!");
        }
        return coreSize;
    }

    @Override
    public int getInitSize() {
        if(isShutdown){
            throw new IllegalStateException("threadpool has shutdown!");
        }
        return initSize;
    }

    @Override
    public void shutDown() {
        if(isShutdown)
            return;
        isShutdown = true;
        ThreadTask ts = null;
        while((ts = threadQueue.poll())!=null){
            ts.getInternalTask().stop();
            ts.getThread().interrupt();
        }
        this.interrupt();
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public int getQueueSize() {
        if(isShutdown){
            throw new IllegalStateException("threadpool has shutdown!");
        }
        return taskQueue.size();
    }

    @Override
    public int getActiveSize() {
        if(isShutdown){
            throw new IllegalStateException("threadpool has shutdown!");
        }
        return activeSize;
    }
}
class ThreadTask{

    private Thread thread;
    private InteralTask task;

    public ThreadTask(Thread thread,InteralTask interalTask){
        this.thread = thread;
        this.task = interalTask;
    }
    public Thread getThread(){
        return this.thread;
    }
    public InteralTask getInternalTask(){
        return this.task;
    }
}