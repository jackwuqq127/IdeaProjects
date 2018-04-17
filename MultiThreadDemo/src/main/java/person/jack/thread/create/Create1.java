package person.jack.thread.create;
/**
 *  继承方式创建多线程
 *  题目：创建一个子线程1，让它打印10次，子线程2，让它打印3次，主线程打印5次
 * */
public class Create1 {
    public static void main(String[] args) {
        /*线程一*/
        Thread t1=new SubThread1(); //新建该 子线程1 的实例
        t1.start(); //调用start() 方法开启此子线程

        /*线程二*/
        Runnable run=new RunablePrint(); //创建Runnable对象
        Thread t2 = new Thread(run); //新建该 子线程2 的实例
        t2.start();

        /*主线程打印10次*/
        String threadName=Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName+">>"+i);
        }
    }
}

/**
 * 定义一个类，使其继承Thread
 * 打印10次
 */
class SubThread1 extends Thread{
    @Override
    public void run() {
        String threadName=Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            System.out.println(threadName+">>"+i);
        }
    }
}

/**
 * 定义一个类，使其实现Runnable接口
 * 打印3次
 */
class RunablePrint implements Runnable{
    public void run() {
        String threadName=Thread.currentThread().getName();
        for (int i = 0; i < 3; i++) {
            System.out.println(threadName+">>"+i);
        }
    }
}
