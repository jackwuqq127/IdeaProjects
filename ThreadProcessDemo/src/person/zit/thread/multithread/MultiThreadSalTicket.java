package person.zit.thread.multithread;

import java.util.Random;

public class MultiThreadSalTicket {
    public static void main(String[] args) {
        TicketData td=new TicketData();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                   while (true){
                        String name=Thread.currentThread().getName();
                        int tno=td.sale();
                        if(tno>0){
                            System.out.println(name+">:"+tno);
                        }else{
                            System.out.println("票已售完！");
                            break;
                        }
                    }
                }
            }).start();
        }
    }

    static class TicketData{
        int tickNo=100;
        //普通方法监视器对象：this
        public /*synchronized*/ int sale(){
            synchronized (this) {
                int t=tickNo;
                if(t>0){
                    try {
                        Thread.sleep(new Random().nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tickNo--;
                    return t;
                }
                return -1;
            }
        }
    }
}
