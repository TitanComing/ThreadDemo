package Thread;

import Thread.component.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExceptionTestMain {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<Boolean>> futureList = new ArrayList<>();

        try {
            futureList.add(executorService.submit(new NoExceptionThread()));
            futureList.add(executorService.submit(new ExecutionExceptionThread()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            futureList.add(executorService.submit(new NoExceptionThread()));
            futureList.add(executorService.submit(new InterruptedExceptionThread()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            futureList.add(executorService.submit(new NoExceptionThread()));
            futureList.add(executorService.submit(new RuntimeExceptionThread()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            futureList.add(executorService.submit(new NoExceptionThread()));
            futureList.add(executorService.submit(new TimeoutExceptionThread()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            for (Future<Boolean> future : futureList) {
                System.out.println("Done: " + future.get(1, TimeUnit.SECONDS));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        executorService.shutdown();
    }
}
