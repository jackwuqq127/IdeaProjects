package person.jack.thread.communication;

public class Demo1 {
    public static void main(String[] args) {
        PrintNum p = new PrintNum();
        new Thread(p, "线程一").start();

        Thread.currentThread().setName("主线程");
        p.run();
    }
}

class PrintNum implements Runnable {
    int n = 1;
    boolean isBegin = false;

    @Override
    public void run() {
        synchronized (this) {
            String threadName = Thread.currentThread().getName();
            if (!isBegin && !threadName.equals("线程一")) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!this.isBegin) {
                this.isBegin = true;
            }
            while (n <= 100) {
                notify();
                System.out.println(threadName + ">" + n);
                this.n++;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            notifyAll();
        }
    }
}