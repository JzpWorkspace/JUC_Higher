import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jzp
 * @create 2019-12-14 20:48
 * 两个线程，操作初始值为0的一个变量
 * 实现对该变量的加一 一个线程对该变量减一
 * 实现交替 十轮
 */
class ResourceUpdate {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increNumber() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+"当前number：" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decreNumber() throws InterruptedException {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+"当前number：" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadWaitNotifyDemoUpdate {
    public static void main(String[] args) {
        ResourceUpdate resource1 = new ResourceUpdate();
        ResourceUpdate resource2 = new ResourceUpdate();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    for (int i = 0; i < 10; i++) {
                        resource1.increNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A+").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 10; i++) {
                        resource2.increNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA+").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    for (int i = 0; i < 10; i++) {
                        resource1.decreNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B-").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    for (int i = 0; i < 10; i++) {
                        resource2.decreNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB-").start();
    }
}
