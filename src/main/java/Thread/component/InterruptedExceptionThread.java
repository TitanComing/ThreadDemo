package Thread.component;

import java.util.concurrent.Callable;

public class InterruptedExceptionThread implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        throw new InterruptedException("I am InterruptedException");
    }
}
