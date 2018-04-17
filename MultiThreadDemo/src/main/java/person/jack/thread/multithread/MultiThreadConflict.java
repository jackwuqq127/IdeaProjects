package person.jack.thread.multithread;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MultiThreadConflict {
    static {
        try {
            PrintStream out = new PrintStream("print.txt");
            System.setOut(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        /*新建一个子线程并立即开始执行*/
        new Thread(new Runnable() { //新建Runnable匿名对象
            public void run() {
                for (int i=1;i<=100;i++) {
                    print("abcdefghijklml",i);
                }
            }
        }).start();

        /*主线程打印*/
        for (int i=1;i<=100;i++) {
            print("123456789",i);
        }
    }

    public static void print(String str,int n){
        synchronized (String.class) {
            String threadName=Thread.currentThread().getName();
            System.out.print(threadName+"第"+n+"次：");
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i)+",");
            }
            System.out.println();
        }
    }
}


