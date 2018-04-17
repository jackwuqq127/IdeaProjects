package person.zit.thread.create;

/***
 * 新建一个子线程循环2……98 偶数
 * 主线程循环 1……99 奇数 person.zit.thread.create.ThreadCreateDemo.SubThread
 */
public class ThreadCreateDemo {
    public static void main(String[] args) {
        ThreadCreateDemo tcd=new ThreadCreateDemo();
        Thread subThread=tcd.new SubThread("子线程1");
        //subThread.setName("子线程1");
        subThread.start();

        Runnable task=tcd.new SubThread2();
        Thread subThread2 = new Thread(task,"子线程2");
        subThread2.start();

        new Thread(task,"子线程3").start();
    }

    private class SubThread2 implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();//获取当前线程对象
            String thredName=thread.getName();
            for (int i = 101; i <=200; i++) {
                System.out.println(thredName+">>"+i);
            }
        }
    }
    protected class SubThread extends Thread{
        public SubThread(){}
        public SubThread(String name){
            super(name); //调用父类的构造函数
        }
        @Override
        public void run() {
            String threadName=Thread.currentThread().getName();
            for (int i = 2; i <=98 ; i+=2) {
                System.out.println(threadName+"======>"+i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

