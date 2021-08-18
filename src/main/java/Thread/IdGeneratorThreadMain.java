package Thread;

import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * java.util.concurrent编写线程
 * 非线程安全的类，会出现重复的id
 * Create by peng on 2021/8/18.
 */
public class IdGeneratorThreadMain {

    public static final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {

        Thread getIdThread1 = new GetIdThread("getIdThread1");
        Thread getIdThread2 = new GetIdThread("getIdThread2");
        Thread getIdThread3 = new GetIdThread("getIdThread3");
        //主线程启动其它线程并等待其它线程处理完成
        long stime = System.currentTimeMillis();
        getIdThread1.start();
        getIdThread2.start();
        getIdThread3.start();
        getIdThread1.join();
        getIdThread2.join();
        getIdThread3.join();
        long etime = System.currentTimeMillis();
        System.out.println("执行时长：" + (etime - stime) +" 毫秒");

        System.out.println("id队列长度：" + queue.size());
        HashSet<Long> hashSet = new HashSet<>();
        while (!queue.isEmpty()) {
            Long id = queue.poll();
            boolean repeat = hashSet.add(id);
            if (!repeat) {
                System.out.println("有重复id：" + id);
            }
        }
        System.out.println("set队列长度：" + hashSet.size());
    }

    public static class IdGeneratorWithAtomic {
        private static final AtomicLong id = new AtomicLong(0);

        public static long getNextId(){
            return id.incrementAndGet();
        }
    }

    public static class IdGeneratorWithsynchronized {
        private static long id = 1L;

        public static synchronized long getNextId(){
            id += 1;
            return id;
        }
    }

    public static class IdGeneratorNoSafe {
        private static long id = 0L;

        public static long getNextId(){
            id += 1;
            return id;
        }
    }

}

class GetIdThread extends Thread {

    private final String threadName;

    public GetIdThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            Long id = IdGeneratorThreadMain.IdGeneratorWithAtomic.getNextId();
            //Long id = IdGeneratorThreadMain.IdGeneratorWithsynchronized.getNextId();
            //Long id = IdGeneratorThreadMain.IdGeneratorNoSafe.getNextId();
            IdGeneratorThreadMain.queue.offer(id);
            //System.out.println(threadName + " get:  " + id);
        }
        System.out.println(threadName + " Done");
    }
}
