package person.jack.thread.concurrent;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int k=1;
                while (true) {
                    try {
                        System.out.println("准备放入元素："+k+",队列已有："+bq.size());
                        bq.put(k);
                        System.out.println("成功放入一个元素，队列已有："+bq.size());

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("准备获取数据>>>>>>>");
                        int data=bq.take();
                        System.out.println("成功获取数据>>>>>>>："+data);
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
