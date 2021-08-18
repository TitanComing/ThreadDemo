package Thread.component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁，可以代替synchronized枷锁
 * Create by peng on 2021/8/17.
 */
public class CounterWithReenTranLock {

    //重入锁
    private final Lock lock = new ReentrantLock();
    //锁对象的操作
    private final Condition condition = lock.newCondition();
    private int count;

    public void add(int n) {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    count += n;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("等待锁超时");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void dec(int n){
        lock.lock();
        try {
            while (count < n){
                condition.await();
            }
            count -= n;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
