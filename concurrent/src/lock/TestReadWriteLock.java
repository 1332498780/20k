package lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {

    static int count;

    static void read(Lock lock) {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read end!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    static void write(Lock lock, int a) {
        try {
            lock.lock();
            Thread.sleep(1000);
            count = a;
            System.out.println("write end!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock =  rw.readLock();
        ReentrantReadWriteLock.WriteLock writeLock =  rw.writeLock();

        Runnable readR = () -> read(readLock);
        Runnable writeR = () -> write(writeLock, 3);

        for (int i = 0; i < 18; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(writeR).start();
        }
    }
}
