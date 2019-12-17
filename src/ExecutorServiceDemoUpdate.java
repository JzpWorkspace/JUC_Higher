import sun.applet.Main;

import java.util.concurrent.*;

/**
 * @author jzp
 * @create 2019-12-17 18:51
 */
public class ExecutorServiceDemoUpdate {
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(5);//一池多用
//        ExecutorService executorService =Executors.newSingleThreadExecutor();//单个线程池
//        ExecutorService executorService =Executors.newCachedThreadPool();//n个。
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy()
//                new ThreadPoolExecutor.CallerRunsPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()
//                new ThreadPoolExecutor.DiscardPolicy()
        );
        //五个窗口处理业务
        try {
            for (int i = 1; i <= 20; i++) {
                int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "窗口\t办理" + finalI + "号。");
                });
            }
        } finally {
            threadPool.shutdown();
        }


    }
}
