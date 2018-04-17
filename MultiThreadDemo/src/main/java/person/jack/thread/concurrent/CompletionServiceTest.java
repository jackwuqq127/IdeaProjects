package person.jack.thread.concurrent;

import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        CompletionService<String> service = new ExecutorCompletionService<>(threadPool);
        for (int i = 0; i <10; i++) { //新建10个任务，每个任务随机返回一个带所属线程名的字符串
            Callable<String> call=new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String threadName=Thread.currentThread().getName();
                    return threadName+":"+(new Random().nextInt(100));
                }
            };
            service.submit(call);
        }

        for (int i = 0; i < 10; i++) {
            try {
                Future<String> future = service.take();
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }
}
