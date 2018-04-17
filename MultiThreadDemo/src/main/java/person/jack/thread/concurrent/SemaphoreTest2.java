package person.jack.thread.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreTest2 {
    public static void main(String[] args) {
        PrintObj po=new PrintObj();
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
class PrintObj{
    private Semaphore signal = new Semaphore(1);
    int k=1;
    public void subPrint(int n){

        try {
            signal.acquire();
            try {
                while (k!=1) {
                    signal.release();
                }
            } finally {
                signal.acquire();
            }
            String threadName=Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                System.out.println(threadName+"线程第"+n+"轮"+i+"次");
            }
            this.k=0;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            signal.release();
        }
    }

    public synchronized void mainPrint(int n) {
        try {
            signal.acquire();
            try {
                while (k != 0) {
                    signal.release();
                }
            } finally {
                signal.acquire();
            }
            String threadName=Thread.currentThread().getName();
            for (int i = 1; i <= 100; i++) {
                System.out.println(threadName+"线程第"+n+"轮"+i+"次");
            }
            this.k=1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            signal.release();
        }
    }
}