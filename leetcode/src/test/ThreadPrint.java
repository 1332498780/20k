package test;

import javax.sound.midi.Soundbank;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPrint {
    //    private static Object lockA = new Object();
//    private static Object lockB = new Object();
//    private static Object lockC = new Object();
    private static final int limit = 10;

    private static Object lock = new Object();
    private static int status = 0;

    public static void main(String[] args) {
//        new Thread(() -> {
//            for (int i = 0; i < limit; i++) {
//                synchronized (lockC) {
//                    synchronized (lockA) {
//                        System.out.println("A");
//                        lockA.notifyAll();
//                    }
//                    try {
//                        lockC.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "t1").start();
//        new Thread(() -> {
//            for (int i = 0; i < limit; i++) {
//                synchronized (lockA) {
//                    synchronized (lockB) {
//                        System.out.println("B");
//                        lockB.notifyAll();
//                    }
//                    try {
//                        lockA.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "t2").start();
//        new Thread(() -> {
//            for (int i = 0; i < limit; i++) {
//                synchronized (lockB) {
//                    synchronized (lockC) {
//                        System.out.println("C");
//                        lockC.notifyAll();
//                    }
//                    try {
//                        lockB.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, "t3").start();
//    }

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < limit; ) {
                synchronized (lock) {
                    if (status % 3 == 0) {
                        System.out.println("A");
                        status++;
                        i++;
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < limit; ) {
                synchronized (lock) {
                    if (status % 3 == 1) {
                        System.out.println("B");
                        status++;
                        i++;
                    }
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < limit; ) {
                synchronized (lock) {
                    if (status % 3 == 2) {
                        System.out.println("C");
                        status++;
                        i++;
                    }
                }
            }
        });

        t3.start();
        t2.start();
        t1.start();
    }
}

class ABC_ReLock {
    private static ReentrantLock lock = new ReentrantLock();
    private static int status = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; ) {
                lock.lock();
                try {
                    while (status % 3 == 0) {
                        System.out.println("A");
                        status++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; ) {
                lock.lock();
                try {
                    while (status % 3 == 1) {
                        System.out.println("B");
                        status++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 10; ) {
                lock.lock();
                try {
                    while (status % 3 == 2) {
                        System.out.println("C");
                        status++;
                        i++;
                    }
                } finally {
                    lock.unlock();
                }
            }
        });

        t3.start();
        t2.start();
        t1.start();
    }
}

class ABC_Condition {
    private static Lock lock = new ReentrantLock();
    private static Condition CA = lock.newCondition();
    private static Condition CB = lock.newCondition();
    private static Condition CC = lock.newCondition();
    private static final int limit = 100;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < limit; i++) {
                lock.lock();
                try {
                    System.out.println("A");
                    CA.signal();
                    try {
                        CC.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < limit; i++) {
                lock.lock();
                try {
                    System.out.println("B");
                    CB.signal();
                    try {
                        CA.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < limit; i++) {
                lock.lock();
                try {
                    System.out.println("C");
                    CC.signal();
                    try {
                        CB.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}

class ABC_ConditionStatus {
    private static Lock lock = new ReentrantLock();
    private static Condition CA = lock.newCondition();
    private static Condition CB = lock.newCondition();
    private static Condition CC = lock.newCondition();
    private static final int limit = 100;
    private static int status = 0;

    public static void main(String[] args) {
//        Throwable

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < limit; i++) {
                    while (status % 3 != 0) {
                        try {
                            CA.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("A");
                    status++;
                    CB.signal();
                }
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < limit; i++) {
                    while (status % 3 != 1) {
                        try {
                            CB.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B");
                    status++;
                    CC.signal();
                }
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < limit; i++) {
                    while (status % 3 != 2) {
                        try {
                            CC.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("C");
                    status++;
                    CA.signal();
                }
            } finally {
                lock.unlock();
            }
        }).start();
    }
}