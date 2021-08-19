package Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Create by peng on 2021/8/18.
 */
public class FutureThreadMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(4);
        List<Future<String>> futureList = new ArrayList<>();
        System.out.println("启动线程池");
        for (int i = 0; i < 6; i++) {
            Future<String> random = es.submit(new FutureTask("" + i));
            futureList.add(random);
        }

        //这个地方不好处理多个线程同步的问题，主线程不好协调多个线程
        //线程处理完之后就返回结果分两种情况：
        // 1. 主线程依赖子线程结果，这时候主线程必须等待子线程处理 2. 主线程不需要依赖子线程处理结果，可以让子线程自行处理，可以参考CompletaFutre
        for(Future<String> future : futureList){
            System.out.println("Done: " + future.get());
        }

        es.shutdown();
    }
}

class FutureTask implements Callable<String> {

    private final String name;

    public FutureTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.println("start task " + name);
        double random = Math.random();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("end task " + name + "with " + random);
        return "task " + name + ": " + random;
    }
}