package person.jack.thread.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    public static void main(String[] args) {
        Data data=new Data();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        String threadName=Thread.currentThread().getName();
                        int n=data.getIncre();
                        System.out.println(threadName+"线程第"+i+"次加1 >>age:"+n);
                    }
                }
            }).start();
        }
    }
}
/*class Data{

    int n=1;
    public synchronized int getIncre(){
        this.n++;
        return this.n;
    }
}*/

class Data{
    AtomicInteger ai = new AtomicInteger(1); //初始值为 1
    public synchronized int getIncre(){
        return ai.incrementAndGet(); //对象+1 并返回结果
    }
}
