package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * Create by peng on 2021/8/18.
 */
public class ExecutorServiceMain {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            es.submit(new ExecutorTask("" + i));
        }
        es.shutdown();
    }
}

class ExecutorTask implements Runnable {

    private final String name;

    public ExecutorTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end task " + name);
    }
}


