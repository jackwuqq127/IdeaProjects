package person.jack.thread.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A线程循环10次, B程循环100次，接着主线程循环5次；
 * A线程循环10次, B程循环100次，接着主线程循环5次；
 * A线程循环10次, B程循环100次，接着主线程循环5次；
 * ……
 * 如此往复50次，请写出程序！
 */
public class ConditionTest2 {
    public static void main(String[] args) {
        Business bn = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    bn.subPrintA(i);
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    bn.subPrintB(i);
                }
            }
        }, "B").start();

        for (int i = 1; i <= 50; i++) {
            bn.mainPrint(i);
        }
    }
}

class Business {
    private String should = "A";

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionMain = lock.newCondition();

    public void subPrintA(int i) {
        String threadName = Thread.currentThread().getName();
        lock.lock();
        while (!should.equals("A")) {
            try {
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 10; j++) {
            System.out.println(">>>" + threadName + "子线程第" + i + "轮" + j + "次");
        }
        should = "B";
        conditionB.signal();
        lock.unlock();
    }

    public void subPrintB(int i) {
        String threadName = Thread.currentThread().getName();
        lock.lock();
        while (!should.equals("B")) {
            try {
                conditionB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 100; j++) {
            System.out.println(">>>>>>" + threadName + "子线程第" + i + "轮" + j + "次");
        }
        should = "Main";
        conditionMain.signal();
        lock.unlock();
    }

    public void mainPrint(int i) {
        String threadName = Thread.currentThread().getName();
        lock.lock();
        while (!should.equals("Main")) {
            try {
                conditionMain.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j <= 5; j++) {
            System.out.println(">>>>>>>>>" + threadName + i + "轮" + j + "次");
        }
        should = "A";
        conditionA.signal();
        lock.unlock();
    }
}