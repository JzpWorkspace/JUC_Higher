import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author jzp
 * @create 2019-12-16 19:08
 * 读写锁
 */
class MyCache {
    private volatile Map<String, String> map = new HashMap<>();
    //public Lock lock = new ReentrantLock();
    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public void put(String key, String value) {
        rwl.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 线程写入开始!");
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 线程写入结束!");
        }finally {
            rwl.writeLock().unlock();
        }

    }

    public void get(String key) {
        rwl.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 线程读取开始!");
            System.out.println(Thread.currentThread().getName() + "\t 线程读取结束!" + map.get(key));
        }finally {
            rwl.readLock().unlock();
        }

    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.get(finalI + "");
            }, String.valueOf(i)).start();
        }

    }
}
