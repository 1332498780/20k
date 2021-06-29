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
                synchronized (taskQueue){
                    runnable = taskQueue.get();
                }
            } catch (InterruptedException e) {
                System.out.println("被中断，break");
                e.printStackTrace();
                break;
            }
            runnable.run();
        }
    }

    public void stop(){
        isRunning = false;
    }
}
