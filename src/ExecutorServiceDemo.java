import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class tickets {
    private int ticket = 30;
    private Lock lock = new ReentrantLock();

    void saleTicket() {
        lock.lock();
        try {
            if (ticket>0) System.out.println(Thread.currentThread().getName() + "\t 窗口售卖第" + (ticket--) + "张票");
            else System.out.println("票卖光了!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * @author jzp
 * @create 2019-12-17 9:05
 */
public class ExecutorServiceDemo {
    public static void main(String[] args) {
        tickets tickets = new tickets();

        ExecutorService threadPoll = Executors.newFixedThreadPool(3);
        try {
            for (int i = 1; i <= 30; i++) {
                int finalI = i;
                threadPoll.execute(() -> {tickets.saleTicket();});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoll.shutdown();
        }
    }
}
