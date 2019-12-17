import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jzp
 * @create 2019-12-15 15:29
 */

/**
 * 1.故障现象
 *  java.util.ConcurrentModificationException
 * 2.导致原因
 *  读写并发操作异常，就是在进行写操作的时候 读取了，并发导致数据异常在输出的时候报出的异常
 * 3.解决方案
 *  3.1 vector(Integer.MAXVALUE) 懒加载，加了把锁，线程是安全了 但是效率低了；
 *  3.2 Collections.synchronizedList() 工具类的加锁方法。
 *  3.3 CopyOnWriteArrayList() 写时复制技术。
 *      写时复制技术就是，在写操作的时候加锁。读操作不加锁。进行写完成之后再将新的数据copy到新的锁当中去
 * 4.优化建议
 *  4.1 还没得优化建议。
 */
public class Collectionss{
    public static void main(String[] args) {
        List list = new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());//Vector(Integer.MAX_VALUE);//ArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(3));
                    System.out.println(list);
                }
            },String.valueOf(i)).start();
        }
    }
}
