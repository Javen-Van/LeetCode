package JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javen
 * @create 2022-03-03
 * @Description 垃圾回收器测试
 */
public class GCTest {

    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        while (true) {
            list.add(new byte[100]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
