package JUC;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Javen
 * @create 2022-03-17
 * @Description
 */
public class TestDeadLock {
    public static void main(String[] args) {
        Chopsticks c1 = new Chopsticks(1);
        Chopsticks c2 = new Chopsticks(2);
        Chopsticks c3 = new Chopsticks(3);
        Chopsticks c4 = new Chopsticks(4);
        Chopsticks c5 = new Chopsticks(5);
        new Philosopher("苏格拉底", c1, c2).start();
        new Philosopher("亚里士多德", c2, c3).start();
        new Philosopher("柏拉图", c3, c4).start();
        new Philosopher("阿基米德", c4, c5).start();
        new Philosopher("牛顿", c5, c1).start();
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lockInterruptibly();
            // 要保护的代码块
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        try {
            if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                System.out.println("获取锁失败");
                return;
            }
        } catch (InterruptedException e) {
            System.out.println("获取锁失败"); // 等待获取锁的过程中被打断
            e.printStackTrace();
            return;
        }
        try {
            // 临界区代码
        } finally {
            lock.unlock();
        }
    }
}

class Chopsticks {
    public int number;

    public Chopsticks(int number) {
        this.number = number;
    }
}

class Philosopher extends Thread {
    public String name;
    public Chopsticks left;
    public Chopsticks right;

    public Philosopher(String name, Chopsticks left, Chopsticks right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (left) {
                synchronized (right) {
                    eat();
                }
            }
        }
    }

    public void eat() {
        System.out.println(Thread.currentThread().getName() + "吃饭");
        System.out.println("思考");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
