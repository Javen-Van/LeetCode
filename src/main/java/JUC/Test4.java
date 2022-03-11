package JUC;

/**
 * @author Javen
 * @create 2022-03-11
 * @Description
 */
public class Test4 {

    static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Test4 test4 = new Test4();
        test4.method();
    }

    public synchronized void method() {
        notify();
        try {
            wait(); // wait方法必须在同步方法或同步代码块中使用，必须要获得了锁
            lock.wait(); // 抛出异常，lock不是当前锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
