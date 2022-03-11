package JUC;

/**
 * @author Javen
 * @create 2022-03-09
 * @Description 正常运行的程序被打断
 */
public class Test2 {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("被打断了，退出循环");
                    break;
                }
            }
        },"t1");
        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("打断t1");
        t1.interrupt();
        System.out.println(t1.isInterrupted());
    }
}
