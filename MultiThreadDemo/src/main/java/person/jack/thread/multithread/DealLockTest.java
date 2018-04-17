package person.jack.thread.multithread;

public class DealLockTest {
    public static void main(String[] args) {
        DeadLock dl = new DeadLock();
        Thread t0 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    dl.run1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t1 = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    dl.run2();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t0.start();
        t1.start();
    }
}

class DeadLock {
    public void run1() throws Exception {
        synchronized (String.class) {
            Thread.sleep(2000);
            synchronized (Integer.class) {
                System.out.println("run1");
            }
        }
    }
    public void run2() throws Exception {
        synchronized (Integer.class) {
            Thread.sleep(2000);
            synchronized (String.class) {
                System.out.println("run2");
            }
        }
    }
}