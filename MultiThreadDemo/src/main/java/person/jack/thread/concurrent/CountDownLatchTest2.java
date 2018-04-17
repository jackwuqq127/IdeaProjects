package person.jack.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 模拟高并发
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) {
        PrintObject p=new PrintObject();
        for (int i = 0; i < 200; i++) {
            new Thread(p).start();
            p.cd.countDown();
        }
    }
}
class PrintObject implements Runnable{
    CountDownLatch cd = new CountDownLatch(10);
    public synchronized void print(){
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String threadName=Thread.currentThread().getName();
        System.out.print(threadName+">>打印：");
        for (int i = 0; i < 10; i++) {
            System.out.print(i+",");
        }
        System.out.println();
    }
    @Override
    public void run() {
        print();
    }
}
