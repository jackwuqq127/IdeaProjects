package person.jack.thread.concurrent.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存系统例子
 */
public class ReadWriteCacheDemo {
    public static void main(String[] args) {
        Cache cache=new Cache();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String data = cache.getData();
                    System.out.println(data);
                }
            }).start();
        }
    }
}
class Cache{
    private String data=null;

    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    public String getData(){
        rwl.readLock().lock();
        try {
            if (data==null){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (data==null){
                        System.out.println("准备写");
                        data = "readWriteLock";
                        System.out.println("写完了");
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return data;
    }
}