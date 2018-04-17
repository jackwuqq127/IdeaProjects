package person.jack.thread.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    while (true) {
                        int v = myData.getV();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    myData.incre();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
class MyData {
    private int v = 1;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public int getV() {
        int rv;
        readWriteLock.readLock().lock();
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + ">> 准备读取数据");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rv = this.v;
        System.out.println(threadName + ">> 读取数据：" + v);
        readWriteLock.readLock().unlock();
        return rv;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void incre() {
        readWriteLock.writeLock().lock();
        String threadName = Thread.currentThread().getName();
        System.out.println("=========>" + threadName + ">> 准备 <<<<< 写入数据");
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.v++;
        System.out.println("=========>" + threadName + ">> 数据加1");
        readWriteLock.writeLock().unlock();
    }
}
