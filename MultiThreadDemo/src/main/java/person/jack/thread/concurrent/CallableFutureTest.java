package person.jack.thread.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String threadName=Thread.currentThread().getName();
                Thread.sleep(2000);
                return threadName+"返回"+(long)(Math.random()*1000);
            }
        });
        try {
            System.out.println(submit.get()); //阻塞线程，直到拿到线程的返回值为止
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
