package Thread.component;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

public class TimeoutExceptionThread implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        throw new TimeoutException("I am TimeoutException");
    }
}
