package JUC;

/**
 * @author Javen
 * @create 2022-03-19
 * @Description
 */
public class Test8 {

    public static void main(String[] args) {
        com com = new com();
        Thread t1 = new Thread(com, "t1");
        Thread t2 = new Thread(com, "t2");
//        Thread t3 = new Thread(com, "t3");
        t1.start();
        t2.start();
//        t3.start();
    }

}

class com implements Runnable {

    public static int flag = 0;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notifyAll();
                if (flag < 100) System.out.println(Thread.currentThread().getName() + ": " + flag++);
                else break;
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}