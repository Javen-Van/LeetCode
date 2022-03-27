package JUC;

/**
 * @author Javen
 * @create 2022-03-24
 * @Description
 */
public class TestVolatile {
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (flag) {

            }
        }).start();

        Thread.sleep(1000);
        System.out.println("结束循环");
        flag = false;
    }
}
