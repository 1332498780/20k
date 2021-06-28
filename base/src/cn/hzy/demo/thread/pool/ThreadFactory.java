package cn.hzy.demo.thread.pool;

public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
