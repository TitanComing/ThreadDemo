package Thread.component;

import java.util.concurrent.Callable;

public class NoExceptionThread implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
