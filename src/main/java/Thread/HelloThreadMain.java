package Thread;

import Thread.component.HelloThread;

import java.util.concurrent.TimeUnit;

/**
 * Create by peng on 2021/8/16.
 */
public class HelloThreadMain {
    public static void main(String[] args) throws InterruptedException {
        HelloThread t = new HelloThread();
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.running = false;
    }
}
