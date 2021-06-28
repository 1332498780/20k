package cn.hzy.demo.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

/***
 * 实现了lock, lock(millins s)，unlock，getBlockThread()方法
 *
 * 对于有锁定时长的lock方法，如果阻塞超时会抛出TimeoutException
 */
public class BooleanLock implements LockInterface{

    private boolean hasLocked;
    private Thread currentThread;
    private final Set<Thread> blockSet = new HashSet<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        while(hasLocked){
            blockSet.add(Thread.currentThread());
            println("未获得锁，开始阻塞...");
            try {
                this.wait();
            } catch (InterruptedException e) {
                println("被中断");
//                e.printStackTrace();
                blockSet.remove(Thread.currentThread());
                throw new InterruptedException();
            }
        }
        println("获得锁");
        blockSet.remove(Thread.currentThread());
        hasLocked = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long millis) throws TimeoutException, InterruptedException {
        if(millis<=0){
            lock();
        }else{
            long currentTime = System.currentTimeMillis();
            long endMillis = currentTime + millis;
            while(hasLocked){
                if(currentTime>endMillis){
                    throw new TimeoutException(Thread.currentThread().getName()+" 阻塞超时");
                }
                if(blockSet.contains(Thread.currentThread())){
                    println("未获得锁，继续阻塞");
                }else{
                    blockSet.add(Thread.currentThread());
                    println("未获得锁，开始阻塞"+(millis/1000)+"s");
                }
                try {
                    this.wait(millis);
                } catch (InterruptedException e) {
                    println("被中断");
                    e.printStackTrace();
                    blockSet.remove(Thread.currentThread());
                    return;
                }
                currentTime = System.currentTimeMillis();
            }
            println("获得锁");
            blockSet.remove(Thread.currentThread());
            hasLocked = true;
            currentThread = Thread.currentThread();
        }
    }

    @Override
    public void unlock() {
        if(Thread.currentThread() != currentThread){
            return;
        }
        synchronized (this){
            println("释放锁");
            blockSet.remove(currentThread);
            hasLocked = false;
            notifyAll();
        }
    }

    @Override
    public void getBlockThreads() {
    }

    private void println(String content){
        System.out.println(Thread.currentThread().getName()+" "+content);
    }
}
interface LockInterface{

    void lock() throws InterruptedException;
    void lock(long millins) throws TimeoutException, InterruptedException;
    void unlock();
    void getBlockThreads();
}