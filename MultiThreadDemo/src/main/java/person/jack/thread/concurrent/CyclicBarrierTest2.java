package person.jack.thread.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  多个线程聚集（集合）后一起并发
 * */
public class CyclicBarrierTest2 {
    public static void main(String[] args) {
        /*线程池*/
        ExecutorService executorService = Executors.newCachedThreadPool();
        CyclicBarrier cb = new CyclicBarrier(5);
        for (int i = 0; i < 10; i++) {
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    try {
                        String threadName=Thread.currentThread().getName();
                        Thread.sleep((long) (Math.random()*1000));
                        System.out.println(threadName+">>到了，当前已有"+(cb.getNumberWaiting()+1)+"个线程！");
                        cb.await();

                        print((int)(Math.random()*100));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }
    private static Lock lock = new ReentrantLock();
    public static void print(int n){
        lock.lock();
        String threadName=Thread.currentThread().getName();
        System.out.print(threadName+">>打印：");
        for (int i = 0; i < 10; i++) {
            System.out.print(i+",");
        }
        System.out.println();
        lock.unlock();
    }
}