package Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalMain {
    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(3);
        String[] users = new String[]{"Bob", "Alice", "Tim", "Mike", "Lily", "Jack", "Bush"};
        for (String user : users) {
            es.submit(new Task(user));
        }
        es.awaitTermination(3, TimeUnit.SECONDS);
        es.shutdown();
    }
}

class UserContext implements AutoCloseable {

    //本质的上是通过ThreadLocal维持类的域，进行线程内传递
    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<>();

    public UserContext(String name) {
        userThreadLocal.set(name);
        System.out.printf("[%s] init user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }

    public static String getCurrentUser() {
        return userThreadLocal.get();
    }

    @Override
    public void close() {
        System.out.printf("[%s] cleanup for user %s...\n", Thread.currentThread().getName(),
                UserContext.getCurrentUser());
        userThreadLocal.remove();
    }
}

class Task implements Runnable {

    final String username;

    public Task(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        try (UserContext ctx = new UserContext(this.username)) {
            new Task1().process();
            new Task2().process();
            new Task3().process();
        }
    }
}

class Task1 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[%s] check user %s...\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}

class Task2 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[%s] %s registered ok.\n", Thread.currentThread().getName(), UserContext.getCurrentUser());
    }
}

class Task3 {
    public void process() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[%s] work of %s has done.\n", Thread.currentThread().getName(),
                UserContext.getCurrentUser());
    }
}