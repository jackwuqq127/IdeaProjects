package person.jack.thread.concurrent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i=1;i<=10;i++) {//创建10个任务
            final int ii=i;
            Runnable task=new Runnable(){
                @Override
                public void run() {
                    String threadName=Thread.currentThread().getName();
                    for (int j = 0; j < ii; j++) {
                        System.out.println(threadName+"执行任务"+ii+">>"+j);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(task); //将任务交给线程池执行
        }
        executorService.shutdown(); //任务执行完成后关闭线程池
    }
}
