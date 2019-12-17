import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author jzp
 * @create 2019-12-17 18:18
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer(1,1L,TimeUnit.SECONDS);
        blockingQueue.offer(2,1L,TimeUnit.SECONDS);
        blockingQueue.offer(3,1L,TimeUnit.SECONDS);
        blockingQueue.offer(4,3L,TimeUnit.SECONDS);
        System.out.println("*******");




//        blockingQueue.put(1);
//        blockingQueue.put(2);
//        blockingQueue.put(3);
//        Object take = blockingQueue.take();
//        System.out.println(take);
//        System.out.println(blockingQueue.take());


//        System.out.println(blockingQueue.offer(1));
//        System.out.println(blockingQueue.offer(2));
//        System.out.println(blockingQueue.offer(3));
//        System.out.println(blockingQueue.offer(3));
//        System.out.println("开始移除："+blockingQueue.peek());
//        System.out.println(blockingQueue.poll()+"被移除");
//        System.out.println("开始移除："+blockingQueue.peek());
//        System.out.println(blockingQueue.poll()+"被移除");
//        System.out.println("开始移除："+blockingQueue.peek());
//        System.out.println(blockingQueue.poll()+"被移除");


//        BlockingQueue blockingQueue = new ArrayBlockingQueue(3);
//        System.out.println(blockingQueue.add("1"));
//        System.out.println(blockingQueue.add("2"));
//        System.out.println(blockingQueue.add("3"));
////        System.out.println(blockingQueue.add("4"));
//        System.out.println(blockingQueue.element());
//        blockingQueue.remove("1");
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.element());
    }
}
