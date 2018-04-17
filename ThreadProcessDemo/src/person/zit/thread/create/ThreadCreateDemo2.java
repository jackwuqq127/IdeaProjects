package person.zit.thread.create;

/***
 * 新建一个子线程循环2……98 偶数
 * 主线程循环 1……99 奇数 person.zit.thread.create.ThreadCreateDemo.SubThread
 */
public class ThreadCreateDemo2 {
    public static void main(String[] args) {
        ThreadCreateDemo2 tcd=new ThreadCreateDemo2();
        Thread subThread=tcd.new SubThread("子线程1");
        //subThread.setName("子线程1");
        subThread.setPriority(3);
        subThread.start();

        Runnable task=tcd.new SubThread2();
        Thread subThread2 = new Thread(task,"子线程2");
        subThread2.setPriority(10);
        subThread2.start();
    }

    private class SubThread2 implements Runnable {
        @Override
        public void run() {
            Thread thread = Thread.currentThread();//获取当前线程对象
            String thredName=thread.getName();
            for (int i = 1; i <=100; i++) {
                System.out.println(thredName+"---->"+thread.getPriority()+">>"+i);
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
            Thread cur=Thread.currentThread();
            String threadName=cur.getName();
            for (int i = 1; i <=100 ; i++) {
                System.out.println(threadName+">"+cur.getPriority()+"======>"+i);
            }
        }
    }


}

