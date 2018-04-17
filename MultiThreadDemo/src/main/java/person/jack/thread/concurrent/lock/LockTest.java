package person.jack.thread.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        /*新建一个子线程并立即开始执行*/
        new Thread(new Runnable() { //新建Runnable匿名对象
            public void run() {
                for (int i=1;i<=50;i++) {
                    print("abcdefghijklml",i);
                }
            }
        }).start();

        /*主线程打印*/
        for (int i=1;i<=50;i++) {
            print("123456789",i);
        }
    }

    public static void print(String str,int n){
        lock.lock();
            String threadName=Thread.currentThread().getName();
            System.out.print(threadName+"第"+n+"次：");
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i)+",");
            }
            System.out.println();
        lock.unlock();
    }
}
