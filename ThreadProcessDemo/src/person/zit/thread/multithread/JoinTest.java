package person.zit.thread.multithread;

/**
 *  A B 两线程，A 每个一秒循环10次
 *  B 每隔2秒循环10次
 *  统计：A B 两个线程执行完一共需花费的时间
 * */
public class JoinTest {
    public static void main(String[] args) {
        Runnable r=new Runnable() {
            @Override
            public void run() {
                String threadName=Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    System.out.println(threadName+"> "+i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1=new Thread(r,"A");
        t1.start();



        Thread t2=new Thread("B") {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                for (int i = 0; i < 10; i++) {
                    try {
                        System.out.println(threadName + "> " + i);
                        if(i==5){
                            t1.join();
                        }
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        long l1=System.currentTimeMillis();
        t2.start();

        while (true) {
            String threadName=Thread.currentThread().getName();
            System.out.println(threadName+"--->t2线程状态:》"+t2.getState());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*long l2=System.currentTimeMillis();
        System.out.println("共消耗时间"+(l2-l1)+"毫秒！");*/
    }
}
