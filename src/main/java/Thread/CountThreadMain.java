package Thread;

import Thread.component.AddThread;
import Thread.component.Counter;
import Thread.component.DecThread;

/**
 * Create by peng on 2021/8/16.
 */
public class CountThreadMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread add = new AddThread(counter);
        Thread dec = new DecThread(counter);
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(counter.get());
    }
}
