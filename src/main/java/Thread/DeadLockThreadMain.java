package Thread;

import java.util.concurrent.TimeUnit;

/**
 * 是否死锁关键：获取锁的顺序是否严格一致
 * Create by peng on 2021/8/17.
 */
public class DeadLockThreadMain {
    public static final Object LockA = new Object();
    public static final Object LockB = new Object();

    public static void main(String[] args) {
        //Thread1,2 不会死锁，因为锁顺序一致；Thread1,3 会死锁，因为锁顺序不一致；
        new Thread1().start();
        //new Thread2().start();
        new Thread3().start();
    }

    static void ThreadSleep(long mills) {
        try {
            TimeUnit.MILLISECONDS.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread1: 尝试获取LockA....");
        synchronized (DeadLockThreadMain.LockA) {
            System.out.println("Thread1: 获取到了LockA");
            DeadLockThreadMain.ThreadSleep(1000);

            System.out.println("Thread1: 尝试获取LockB....");
            synchronized (DeadLockThreadMain.LockB){
                System.out.println("Thread1: 获取到了LockB");
                DeadLockThreadMain.ThreadSleep(1000);
            }
            System.out.println("Thread1: 释放LockB");
        }
        System.out.println("Thread1: 释放LockA");
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread2: 尝试获取LockA....");
        synchronized (DeadLockThreadMain.LockA) {
            System.out.println("Thread2: 获取到了LockA");
            DeadLockThreadMain.ThreadSleep(1000);

            System.out.println("Thread2: 尝试获取LockB....");
            synchronized (DeadLockThreadMain.LockB){
                System.out.println("Thread2: 获取到了LockB");
                DeadLockThreadMain.ThreadSleep(1000);
            }
            System.out.println("Thread2: 释放LockB");
        }
        System.out.println("Thread2: 释放LockA");
    }
}

class Thread3 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread3: 尝试获取LockB....");
        synchronized (DeadLockThreadMain.LockB) {
            System.out.println("Thread3: 获取到了LockB");
            DeadLockThreadMain.ThreadSleep(1000);

            System.out.println("Thread3: 尝试获取LockA....");
            synchronized (DeadLockThreadMain.LockA){
                System.out.println("Thread3: 获取到了LockA");
                DeadLockThreadMain.ThreadSleep(1000);
            }
            System.out.println("Thread3: 释放LockA");
        }
        System.out.println("Thread3: 释放LockB");
    }
}