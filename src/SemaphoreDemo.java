import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author jzp
 * @create 2019-12-16 20:15
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                Boolean flag = false;
                try {
                    semaphore.acquire();
                    flag=true;
                    System.out.println(Thread.currentThread().getName() + "号车抢到车位了。");
                    try{TimeUnit.SECONDS.sleep(3);}catch (Exception e){e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName() + "号车离开车位了。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (flag){
                        flag=false;
                        semaphore.release();
                    }
                }
            }, String.valueOf(i)).start();
        }

    }
}
