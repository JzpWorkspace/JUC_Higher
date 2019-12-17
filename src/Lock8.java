import java.util.concurrent.TimeUnit;

/**
 * @author jzp
 * @create 2019-12-17 23:01
 */
class Phone{
    public  synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("-----Email");
    }
    public synchronized void sendSms(){
        System.out.println("-----Sms");
    }
    public void Hello(){
        System.out.println("-----Hello");
    }


}
public class Lock8 {
    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            phone2.sendSms();
        }).start();

    }
}
