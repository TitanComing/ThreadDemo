package Thread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create by peng on 2021/8/17.
 */
public class WaitAndNotityThreadMain {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue = new TaskQueue();
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            String s = taskQueue.getTask();
                            System.out.println("execute task: " + s);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            thread.start();
            threadList.add(thread);
        }
        Thread add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                // 放入task:
                String s = "t-" + Math.random();
                System.out.println("add task: " + s);
                taskQueue.addTask(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : threadList) {
            t.interrupt();
        }

    }
}

class TaskQueue {
    private final Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String s) {
        this.queue.add(s);
        //在锁对象上通知
        this.notifyAll();
    }

    public synchronized String getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            //在锁对象上等待
            this.wait();
        }
        return queue.remove();
    }
}
