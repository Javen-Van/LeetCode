package JUC;

/**
 * @author Javen
 * @create 2022-03-09
 * @Description
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
//        TwoPhaseTermination tpt = new TwoPhaseTermination();
//        tpt.start();
//        Thread.sleep(3500);
//        tpt.stop();

        TwoPhaseTerminate tpt2 = new TwoPhaseTerminate();
        Thread t1 = new Thread(tpt2);
        t1.start();
        Thread.sleep(3500);
        tpt2.stop();
    }
}

class TwoPhaseTermination {
    private Thread monitor;

    private volatile boolean stop = false;

    // 启动监控线程
    public void start() {
        monitor = new Thread(() -> {
            while (true) {
                if (stop) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("执行监控记录");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        monitor.start();
    }

    public void stop() {
        stop = true;
        monitor.interrupt();
    }
}

class TwoPhaseTerminate implements Runnable {
    private boolean stop = false;

    @Override
    public void run() {
        while (true) {
            if (stop) {
                System.out.println("料理后事");
                break;
            }
            try {
                Thread.sleep(1000);
                System.out.println("执行监控记录");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stop = true;
    }
}
