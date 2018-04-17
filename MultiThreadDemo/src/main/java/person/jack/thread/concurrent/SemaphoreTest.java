package person.jack.thread.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService service= Executors.newCachedThreadPool();
        Semaphore sh = new Semaphore(3,true);
        for (int i = 0; i <10; i++) {
            final int ii=i;
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    String threadName=Thread.currentThread().getName();
                    System.out.println("线程"+threadName+"开始执行任务"+ii);
                    try {
                        sh.acquire();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+threadName+"开始执行任务"+ii+"！已并发："+(3-sh.availablePermits()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sh.release();
                    System.out.println("线程"+threadName+"已离开");
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
