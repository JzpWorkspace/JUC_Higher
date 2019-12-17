import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jzp
 * @create 2019-12-16 18:43
 * 进行通知调用。顺序执行
 */

class Resourses {
    private int flag = 1;//此时标志位1=AA，2=BB，3=CC
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public void AA() {
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            for (int i = 1; i < 6; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 线程打印" +i);
            }
            flag=2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void BB() {
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 1; i < 11; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 线程打印" +i);
            }
            flag=3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void CC() {
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 1; i < 21; i++) {
                System.out.println(Thread.currentThread().getName() + "\t 线程打印" +i);
            }
            flag=1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadOrderAccess {
    public static void main(String[] args) {
        Resourses resourses = new Resourses();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resourses.AA();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resourses.BB();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resourses.CC();
            }
        },"CC").start();
    }
}
