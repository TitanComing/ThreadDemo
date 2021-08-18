package Thread.component;

/**
 * Create by peng on 2021/8/16.
 */
public class AddThread extends Thread{

    private Counter counter;

    public AddThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run(){
        for(int i =0; i < 10000; i++){
            counter.add(1);
        }
    }
}
