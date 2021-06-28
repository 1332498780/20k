package cn.hzy.demo.thread.pool;

public interface ThreadPool {

    void execute(Runnable runnable);

    int getMaxSize();

    int getCoreSize();

    int getInitSize();

    void shutDown();

    boolean isShutdown();

    int getQueueSize();

    int getActiveSize();
}
