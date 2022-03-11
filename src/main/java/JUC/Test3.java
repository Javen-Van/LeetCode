package JUC;

/**
 * @author Javen
 * @create 2022-03-09
 * @Description
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();
        Thread.sleep(3500);
        tpt.stop();
    }
}

class TwoPhaseTermination {
    private Thread monitor;

    // 启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                Thread cur = Thread.currentThread();
                if (cur.isInterrupted()) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 重新设置打断标记
                    cur.interrupt();
                }
            }
        });

        monitor.start();
    }

    public void stop() {
        monitor.getState();
        monitor.interrupt();
    }
}
