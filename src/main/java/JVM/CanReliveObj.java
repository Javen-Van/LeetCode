package JVM;

/**
 * @author Javen
 * @create 2022-01-19
 * @Description
 */
public class CanReliveObj {

    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this; // 当前待回收的对象在finalize()方法中与引用链上的任何一个对象建立了联系
    }

    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            // 对象第一次成功拯救自己
            obj = null;
            System.gc();
            System.out.println("第一次gc");
            Thread.sleep(2000); // 因为Finalizer线程优先级很低，暂停2秒，以等待他
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
            obj = null;
            System.out.println("第二次gc");
            System.gc();
            Thread.sleep(2000); // 因为Finalizer线程优先级很低，暂停2秒，以等待他
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
