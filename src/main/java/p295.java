import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-08-27
 * @Description
 */
public class p295 {

    @Test
    public void test(){
        MedianFinder finder = new MedianFinder();
        finder.addNum(5);
        finder.addNum(1);
        finder.addNum(2);
        System.out.println(finder.queue);
        System.out.println(finder.findMedian());
        finder.addNum(3);
        System.out.println(finder.findMedian());
    }
}
class MedianFinder {

    Queue<Integer> queue = new PriorityQueue<>();
    int len = 0; // 数组长度

    /** initialize your data structure here. */
    public MedianFinder() {
        int odd;
        int[] even = new int[4];
    }

    public void addNum(int num) {
        this.queue.offer(num);
        len++;
    }

    public double findMedian() {
        return 0;
    }

    public int binarySearch(int num){
        return 0;
    }
}
