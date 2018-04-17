package person.jack.thread.concurrent;

import java.util.*;

public class CollectionHashMapTest {
    private Map map = new HashMap();
    //private Map map = new ConcurrentHashMap(new HashMap());

    public void doIt() {
        for (int i = 0; i < 50000; i++) {
            map.put(new Integer(i), i);
        }
        for (int i = 0; i < 1000; i++) {
            new Thread() {
                public void run() {
                    String threadName = Thread.currentThread().getName();
                    while (true) {
                        for (int i = 0; i < 500; i++) {
                            map.put(new Integer(i), i);
                        }
                    }
                }
            }.start();
            System.out.println("新建第"+(i+1)+"个线程！");
        }
        new Thread() {
            public void run() {
                String threadName = Thread.currentThread().getName();
                long l1=0,l2=0,l3=0;
                int n=1;
                while (true) {
                    l3=System.currentTimeMillis();
                    System.out.println("距离上一次获得CPU资源："+(l3-l2)+"毫秒");
                    l1 = System.currentTimeMillis();
                    System.out.println(threadName+"第"+n+"次读取，试图读取数据！");
                    int k = new Random().nextInt(50000);
                    Object get = map.get(k);
                    l2 = System.currentTimeMillis();
                    System.out.println(threadName+"第"+n+"次读取，====》读取数据！" + get + "耗时：" + (l2 - l1) + "毫秒");
                    System.out.println();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
    public static void main(String[] args) {
        new CollectionHashMapTest().doIt();
    }
}
