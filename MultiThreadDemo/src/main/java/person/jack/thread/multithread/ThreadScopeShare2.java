package person.jack.thread.multithread;

import java.util.Random;
/**
 * 新建两个子线程 t1 t2，在线程内调用两个模块A B,使 A B调用对应线程的数据
 */
public class ThreadScopeShare2 {
    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private static String[] names = {"无忌", "李靖", "魏征", "炫铃", "如晦", "叔宝", "敬得"};
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            final int in = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt(100);
                    String name = names[in];
                    threadLocal.set(data);
                    MyThreadScopeData myData = MyThreadScopeData.getInstance();
                    myData.setId(in);
                    myData.setName(name);
                    myData.setAge(data);
                    new AO().getData();
                    new BO().getData();
                }
            }, "thread-" + i + ">> ").start();
        }
    }
}
class AO {
    public void getData() {
        Thread cur = Thread.currentThread();
        String threadName = cur.getName();
        int data2 = ThreadScopeShare2.threadLocal.get();
        System.out.println("A from " + threadName + " getMyData: " + data2);
        MyThreadScopeData myData = MyThreadScopeData.getInstance();
        System.out.println("A from " + threadName + "id>> " + myData.getId());
        System.out.println("A from " + threadName + "name>> " + myData.getName());
        System.out.println("A from " + threadName + "age>> " + myData.getAge());
    }
}
class BO {
    public void getData() {
        Thread cur = Thread.currentThread();
        String threadName = cur.getName();
        int data2 = ThreadScopeShare2.threadLocal.get();
        System.out.println("B from " + threadName + " getMyData: " + data2);
        MyThreadScopeData myData = MyThreadScopeData.getInstance();
        System.out.println("B from " + threadName + "id>> " + myData.getId());
        System.out.println("B from " + threadName + "name>> " + myData.getName());
        System.out.println("B from " + threadName + "age>> " + myData.getAge());
    }
}
class MyThreadScopeData {
    private int id;
    private String name;
    private int age;
    public static ThreadLocal<MyThreadScopeData> local = new ThreadLocal<>();
    public static MyThreadScopeData getInstance() {
        MyThreadScopeData instance = local.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            local.set(instance);
        }
        return instance;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
