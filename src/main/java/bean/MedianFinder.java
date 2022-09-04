package bean;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-12-14
 * @Description p295 数据流的中位数「大小堆」
 */
public class MedianFinder {

    Queue<Integer> min;
    Queue<Integer> max;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        min = new PriorityQueue<>(); // 中位数右侧的数据
        max = new PriorityQueue<>((o1, o2) -> o2 - o1); // 中位数左侧的数据
    }

    public void addNum(int num) {
        if (min.isEmpty() || num >= min.peek()) {
            min.offer(num);
            if (min.size() > max.size() + 1) {
                max.offer(min.poll());
            }
        } else {
            max.offer(num);
            if (max.size() > min.size()) {
                min.offer(max.poll());
            }
        }
    }

    public double findMedian() {
        if (min.size() > max.size()) {
            return min.peek();
        }
        return (min.peek() + max.peek()) / 2.0;
    }
}
