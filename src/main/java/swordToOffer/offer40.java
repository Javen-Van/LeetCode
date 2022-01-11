package swordToOffer;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2022-01-07
 * @Description k个最小数
 */
public class offer40 {

    public int[] getLeastNumbers(int[] arr, int k) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                queue.offer(arr[i]);
                continue;
            }
            if (!queue.isEmpty() && arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int[] res = new int[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            res[i] = queue.poll();
        }
        return res;
    }
}
