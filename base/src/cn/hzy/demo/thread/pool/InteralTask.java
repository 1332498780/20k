package cn.hzy.demo.thread.pool;

public class InteralTask implements Runnable{

    private final TaskQueue taskQueue;
    private boolean isRunning = true;

    public InteralTask(TaskQueue taskQueue){
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while(isRunning && !Thread.currentThread().isInterrupted()){
            Runnable runnable = null;
            try {
                runnable = taskQueue.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.run();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
