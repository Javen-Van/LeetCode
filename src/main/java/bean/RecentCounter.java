package bean;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Javen
 * @create 2022-05-06
 * @Description p933 最近的请求次数「队列」
 */

class RecentCounter {

    Deque<Integer> queue = new LinkedList<>();

    public RecentCounter() {

    }

    public int ping(int t) {
        while (!queue.isEmpty() && queue.peekFirst() < t - 3000) queue.pollFirst();
        queue.offerLast(t);
        return queue.size();
    }
}
