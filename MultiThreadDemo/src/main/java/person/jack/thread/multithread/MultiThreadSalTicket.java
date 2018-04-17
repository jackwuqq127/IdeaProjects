package person.jack.thread.multithread;

/**
 * 模拟火车站售票程序，开启三个窗口售票。
 * */
public class MultiThreadSalTicket {
    public static void main(String[] args) {
        TicketData ticketData=new TicketData();
        for (int i = 0; i < 3; i++) { //循环创建3个线程，模拟三个窗口
            new Thread(new Runnable() {
                public void run() {
                    String threadName=Thread.currentThread().getName();
                    int ticketNo;
                    while ((ticketNo=ticketData.sale()) != -1) {
                        System.out.println(threadName+"得到车票,编号："+ticketNo);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    static class TicketData {
        private  int ticketNo =100;
        public synchronized int sale(){
            if (ticketNo == 0) {
                return -1;
            }
            int t= ticketNo;
            ticketNo--;
            return t;
        }

        public int sale2(){
            synchronized (TicketData.class) {
                if (ticketNo == 0) {
                    return -1;
                }
                int t= ticketNo;
                ticketNo--;
                return t;
            }
        }
    }
}

class SingleObj1{ //饱汉模式
    private static SingleObj1 singleObj=new SingleObj1();
    private SingleObj1(){}
    public static SingleObj1 getInstance(){
        return singleObj;
    }
}

class SingleObj2{ //饥汉模式
    private static SingleObj2 singleObj=null;
    private SingleObj2(){}
    public static SingleObj2 getInstance(){
        if (singleObj==null) {
            synchronized (SingleObj2.class) {
                if(singleObj==null){
                    singleObj=new SingleObj2();
                }
            }
        }
        return singleObj;
    }
}
