package person.jack.thread.concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger exchanger=new Exchanger();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String es="天王盖地虎";
                    String threadName=Thread.currentThread().getName();
                    System.out.println(threadName+"准备交换数据>>>>>>"+es);
                    String str=exchanger.exchange(es).toString();
                    System.out.println(threadName+"交换获得数据>> "+str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String es="宝塔镇河妖";
                    String threadName=Thread.currentThread().getName();
                    System.out.println(threadName+"准备交换数据》》》"+es);
                    String str=exchanger.exchange(es).toString();
                    System.out.println(threadName+"交换获得数据>> "+str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
