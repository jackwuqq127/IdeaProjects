package person.jack.thread.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest2 {
    public static void main(String[] args) {
        PrintObjBQ po=new PrintObjBQ();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=50; i++) {
                    po.subPrint(i);
                }
            }
        },">>>子线程").start();

        Thread.currentThread().setName("=====>主线程");
        for (int i = 1; i <=50; i++) {
            po.mainPrint(i);
        }
    }
}
class PrintObjBQ{
    private BlockingQueue<Integer> bq1 = new ArrayBlockingQueue<>(1);
    private BlockingQueue<Integer> bq2 = new ArrayBlockingQueue<>(1);
    {
        try {
            bq2.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    int k=1;
    public void subPrint(int n){
        try {
            bq1.put(1);
            String threadName=Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                System.out.println(threadName+"线程第"+n+"轮"+i+"次");
            }
            this.k=0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                bq2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void mainPrint(int n) {
        try {
            bq2.put(1);
            String threadName=Thread.currentThread().getName();
            for (int i = 1; i <= 100; i++) {
                System.out.println(threadName+"线程第"+n+"轮"+i+"次");
            }
            this.k=1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                bq1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
