import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-12-24
 * @Description
 */
public class p1700 {

    @Test
    public void test() {
        int[] apples = {1, 2, 3, 5, 2};
        int[] days = {3, 2, 1, 4, 2};
        System.out.println(eatenApples(apples, days));
    }

    // p1705 吃苹果的最大数目「贪心 + 优先队列」
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int n = apples.length, res = 0;
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.peek()[0] <= i) {
                queue.poll();
            }
            if (apples[i] > 0) queue.offer(new int[]{i + days[i], apples[i]});
            if (!queue.isEmpty()) {
                int[] peek = queue.peek();
                peek[1]--;
                if (peek[1] == 0) queue.poll();
                res++;
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            while (!queue.isEmpty() && queue.peek()[0] <= n) {
                queue.poll();
            }
            if (queue.isEmpty()) break;
            int[] poll = queue.poll();
            int cur = Math.min(poll[0] - n, poll[1]);
            res += cur;
            n += cur;
        }
        return res;
    }
}
