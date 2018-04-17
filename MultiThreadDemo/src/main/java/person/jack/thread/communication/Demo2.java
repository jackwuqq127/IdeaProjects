package person.jack.thread.communication;

/*
 * 子线程循环10次然后主线程循环100次，接着子线程循环10次主线程循环100次，接着子线程循环10次主线程循环100次……
 * 如此往复50次，请写出程序！*/
public class Demo2 {
    public static void main(String[] args) {
        PrintObj po=new PrintObj();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=50; i++) {
                    po.subPrint(i);
                }
            }
        },">>>子线程").start();

        Thread.currentThread().setName("=====>主线程");
        for (int i = 1; i <=50; i++) {
            po.mainPrint(i);
        }
    }
}

class PrintObj{
    int k=1;
    public synchronized void subPrint(int n){
        if (k!=1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String threadName=Thread.currentThread().getName();
        for (int i = 1; i <= 10; i++) {
            System.out.println(threadName+"线程第"+n+"轮"+i+"次");
        }
        this.k=0;
        notify();
    }

    public synchronized void mainPrint(int n) {
        if (k != 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String threadName=Thread.currentThread().getName();
        for (int i = 1; i <= 100; i++) {
            System.out.println(threadName+"线程第"+n+"轮"+i+"次");
        }
        this.k=1;
        notify();
    }
}
