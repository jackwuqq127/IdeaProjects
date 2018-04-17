package person.jack.thread.communication;

public class Demo3 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Producter pro = new Producter(shop);
        new Thread(pro).start(); //生产者线程

        while (true) {
            new Customer(shop).start(); //消费者线程
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producter implements Runnable {
    private Shop shop;

    public Producter(Shop shop) {
        this.shop = shop;
    }

    public void product() {
        shop.add();
    }

    @Override
    public void run() {
        product();
    }
}

class Shop {
    private int repertory = 0;
    public final int REPERTORY_MAX = 12;

    public synchronized void sale() {
        if (this.repertory < this.REPERTORY_MAX / 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String threadName = Thread.currentThread().getName();
        System.out.println("消费者" + threadName+"准备购买，库存：>>" + this.repertory);
        this.repertory--;
        System.out.println("消费者" + threadName + "成功购买商品,剩余库存：>>>>>" + this.repertory);
        System.out.println();
        this.notify();
    }

    public synchronized void add() {
        while (true) {
            while (this.repertory == this.REPERTORY_MAX||(this.repertory>0&&this.repertory>=this.REPERTORY_MAX/3)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (true){
                System.out.println("生产者准备生产产品,库存："+this.repertory);
                this.repertory++;
                System.out.println("生产者成功生产产品，并添加到库存：" + this.repertory);
                System.out.println();
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.repertory == this.REPERTORY_MAX) {
                    break;
                }
            }
            notify();
        }
    }
}

class Customer extends Thread {
    private Shop shop;

    public Customer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        shop.sale();
    }
}
