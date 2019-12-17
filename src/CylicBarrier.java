import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jzp
 * @create 2019-12-16 19:49
 * 正数来完成
 */
public class CylicBarrier {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cy = new CyclicBarrier(7, () -> {
            System.out.println("葫芦娃救爷爷。");
        });
        for (int i = 1; i <= 7; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.println(finalI + "娃来了。");
                    cy.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
        System.out.println("救完收工！");
    }
}
