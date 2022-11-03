package Thread.component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ExecutionExceptionThread implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        throw new ExecutionException(new Throwable("I am ExecutionException!"));
    }
}
