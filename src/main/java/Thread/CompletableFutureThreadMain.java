package Thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 回调结果
 * Create by peng on 2021/8/18.
 */
public class CompletableFutureThreadMain {
    public static void main(String[] args) throws InterruptedException {
        // 创建异步执行任务
        System.out.println("开启异步执行....");
        CompletableFuture<Double> completableFuture = CompletableFuture.supplyAsync(CompletableFutureThreadMain::fetchPrice);
        // 如果执行成功:
        completableFuture.thenAccept(result -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        completableFuture.exceptionally(e ->{
            e.printStackTrace();
            return null;
        });
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        System.out.println("等待异步执行结果....");
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    static Double fetchPrice() {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

}
