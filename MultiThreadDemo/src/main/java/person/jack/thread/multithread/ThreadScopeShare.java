package person.jack.thread.multithread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 新建两个子线程 t1 t2，在线程内调用两个模块A B,使 A B调用对应线程的数据
 */
public class ThreadScopeShare {
    private static Map<Thread, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        Object obj=new Object();
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (obj) {
                        int data = new Random().nextInt(100);
                        map.put(Thread.currentThread(), data);
                        System.out.println(Thread.currentThread().getName()
                                + " has put data :" + data);
                        new A().get();
                        new B().get();
                    }
                }
            }).start();
        }
    }

    private static class A {
        public void get() {
            int data = map.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

    private static class B {
        public void get() {
            int data = map.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }
}