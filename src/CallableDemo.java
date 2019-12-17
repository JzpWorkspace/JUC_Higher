import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jzp
 * @create 2019-12-16 20:52
 */
class MyThread implements Runnable{
    @Override
    public void run() {

    }
}
class CallAble implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println("=====come in callAble=====");
        return "1024";
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(new CallAble());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }
}
