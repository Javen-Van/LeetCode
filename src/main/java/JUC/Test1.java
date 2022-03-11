package JUC;

/**
 * @author Javen
 * @create 2022-03-09
 * @Description
 */
public class Test1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("sleeping");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        System.out.println("t1 start");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("interrupt");
        t1.interrupt();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.isInterrupted());
    }
}
