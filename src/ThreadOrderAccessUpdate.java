import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author jzp
 * @create 2019-12-16 21:52
 * 整合
 */
class Resources {
    private int flag = 1;
    private Lock lock = new ReentrantLock();


    void print() throws InterruptedException {
        lock.lock();
        int times = 0;
        String str =null;
        if (flag == 1) {
            times = 5;
            flag = 2;
            str = "AA";
        } else if (flag == 2) {
            times = 10;
            flag = 3;
            str="BB";
        } else {
            times = 20;
            flag = 1;
            str="CC";
        }
        for (int i = 1; i <= times; i++) {
            System.out.println(str+ " \t 线程打印"+i);
        }
        lock.unlock();
    }
}

public class ThreadOrderAccessUpdate {
    public static void main(String[] args) {
        Resources resources = new Resources();
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        resources.print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, String.valueOf(i)).start();
        }

    }
}
