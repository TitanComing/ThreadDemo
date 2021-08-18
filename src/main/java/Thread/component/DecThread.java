package Thread.component;

/**
 * Create by peng on 2021/8/16.
 */
public class DecThread extends Thread{

    private Counter counter;

    public DecThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i =0; i < 10000; i++){
            counter.dec(1);
        }
    }
}
