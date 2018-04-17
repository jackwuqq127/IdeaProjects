package person.jack.thread.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
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
    private Lock lock = new ReentrantLock();
    private Condition condition=lock.newCondition();
    int k=1;
    public void subPrint(int n){
        lock.lock();
        try {
            while (k!=1) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String threadName=Thread.currentThread().getName();
            for (int i = 1; i <= 10; i++) {
                System.out.println(threadName+"线程第"+n+"轮"+i+"次");
            }
            this.k=0;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public synchronized void mainPrint(int n) {
        lock.lock();
        try {
            while (k != 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String threadName=Thread.currentThread().getName();
            for (int i = 1; i <= 100; i++) {
                System.out.println(threadName+"线程第"+n+"轮"+i+"次");
            }
            this.k=1;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}

