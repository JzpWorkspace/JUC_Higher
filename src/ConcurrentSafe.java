import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

class ResourseClass {

}

/**
 * @author jzp
 * @create 2019-12-16 18:01
 * 线程不安全。
 */
public class ConcurrentSafe {
    public static void main(String[] args) {
        Map map =new ConcurrentHashMap(); //new HashMap<String,String>();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(()->{
                map.put(finalI, finalI);
                System.out.println(map);
            }).start();
        }

        /*Set<String> set = new CopyOnWriteArraySet();//Collections.synchronizedSet(new HashSet());//new Hashset()

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(set);
            },""+i).start();
        }*/
    }
}
