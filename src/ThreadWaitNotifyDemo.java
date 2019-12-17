import java.util.Collections;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

/**
 * @author jzp
 * @create 2019-12-14 20:48
 * <p>
 * 两个线程，操作初始值为0的一个变量
 * 实现对该变量的加一 一个线程对该变量减一
 * 实现交替 十轮
 * 原始
 */
class Resource {
    private int number = 0;

    public synchronized void increNumber() throws InterruptedException
    {
        while (number != 0) {
            this.wait();
        }
        ++number;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+"当前number：" + number);
    }

    public synchronized void decreNumber() throws InterruptedException
    {
        while (number == 0) {
            this.wait();
        }
        --number;
        this.notifyAll();
        System.out.println(Thread.currentThread().getName()+":当前number：" + number);
    }
}

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    for (int i = 0; i < 10; i++) {
                        resource.increNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A+").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 10; i++) {
                        resource.increNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA+").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                    for (int i = 0; i < 10; i++) {
                        resource.decreNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B-").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    for (int i = 0; i < 10; i++) {
                        resource.decreNumber();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB-").start();
    }
}
