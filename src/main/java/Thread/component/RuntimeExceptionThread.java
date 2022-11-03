package Thread.component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class RuntimeExceptionThread implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        throw new RuntimeException("I am RuntimeException!");
    }
}
