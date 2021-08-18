package Thread.component;

/**
 * add, dec方法均对这个实例加锁，保证多线程对这两个方法的调用不被意外
 * count是私有变量，只有add, dec方法才能改变，所以控制住了add,dec的正确性就控制了count的正确性
 * Create by peng on 2021/8/16.
 */
public class Counter {
    private int count = 0;

    public void add(int n){
        synchronized (this){
            count += n;
        }
    }

    public void dec(int n){
        synchronized (this){
            count -= n;
        }
    }

     //等价写法-修饰方法等价与锁住了this变量
     //对于static方法，没有this实例，所以锁住的是Class实例（Counter.class）
//    public synchronized void add(int n){
//            count += n;
//    }
//    public synchronized void dec(int n){
//            count -= n;
//    }

    //只有一个变量，不需要同步方法控制，但是返回两个变量，就需要同步控制了
    public int get() {
        return count;
    }
}
