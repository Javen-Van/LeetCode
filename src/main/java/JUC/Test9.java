package JUC;

/**
 * @author Javen
 * @create 2022-03-24
 * @Description
 */
public class Test9 {

    static int x;
    static Object m = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (m) {
                x = 10;
            }
        }, "t1").start();
        new Thread(() -> {
            synchronized (m) {
                System.out.println(x);
            }
        }, "t2").start();
    }

}

class Singleton {

    private Singleton() {
    }

    private static final class SingletonHolder {
        static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

}