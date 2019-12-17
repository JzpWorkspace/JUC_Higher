import java.util.concurrent.CountDownLatch;

/**
 * @author jzp
 * @create 2019-12-16 19:38
 */
class ShareResourse {

}

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号走了");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("最后关门！");
    }
}
