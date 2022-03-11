package JUC;

import java.util.List;

/**
 * @author Javen
 * @create 2022-03-11
 * @Description
 */
public class Test5 {

    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            System.out.println("等待结果");
            Object o = guardedObject.get(3000);
            System.out.println(o);
        }, "t1").start();

        new Thread(() -> {
            try {
                System.out.println("执行任务");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.complete(null);
        }, "t2").start();

    }
}

class GuardedObject {

    private Object response; // 结果

    // 获取结果
    public Object get(long timeout) {
        synchronized (this) {
            long end = System.currentTimeMillis() + timeout, leftTime = timeout;
            while (response == null && leftTime > 0) {
                try {
                    wait(leftTime); // 避免虚假唤醒时多等
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                leftTime = end - System.currentTimeMillis();
            }
        }
        System.out.println("拿到结果");
        return response == null ? "超时！" : response;
    }

    public void complete(Object obj) {
        synchronized (this) {
            this.response = obj;
            notifyAll();
        }
    }
}
