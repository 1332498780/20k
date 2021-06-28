package cn.hzy.demo.thread.pool;

public interface TaskQueue {

    void offer(Runnable runnable);

    Runnable get() throws InterruptedException;

    int size();
}
