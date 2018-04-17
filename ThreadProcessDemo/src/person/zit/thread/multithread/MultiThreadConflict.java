package person.zit.thread.multithread;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MultiThreadConflict {
    static {
        try {
            PrintStream printStream = new PrintStream("print.text");
            System.setOut(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("开始");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    print("abcefg");
                }
            }
        }).start();
        while (true) {
            print("123456");
        }
    }
    //static 默认监视器对象：所在类的字节码对象
    public synchronized static void print(String str){
        //synchronized (MultiThreadConflict.class) {
            String name=Thread.currentThread().getName();
            System.out.print(name+">>");
            for (int i = 0; i < str.length(); i++) {
                System.out.print(str.charAt(i)+",");
            }
            System.out.println();
        //}
    }
}
