package JUC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Javen
 * @create 2022-03-17
 * @Description
 */
public class Test7 {

    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        // 创建新的条件变量
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        lock.lock();
        try {
            condition1.await(); // 进入condition1等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        condition1.signal(); // 唤醒condition1中的某一个线程
        condition1.signalAll(); // 唤醒condition1中的所有线程
    }
}
