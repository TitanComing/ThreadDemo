package Thread.component;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * Create by peng on 2021/8/16.
 */
public class HelloThread extends Thread {
    public volatile boolean running = true;
    @SneakyThrows
    @Override
    public void run(){
        int n = 0;
        while (running){
            n ++;
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(n + " hello!");
        }
        System.out.println("end!");
    }
}
