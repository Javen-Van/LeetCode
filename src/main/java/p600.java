import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-12-08
 * @Description
 */
public class p600 {

    // p630 课程表「贪心 + 优先队列」
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(o -> o[1]));
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1); // Java中默认是小根堆
        int sum = 0;
        for (int[] course : courses) {
            if (sum + course[0] <= course[1]) {
                queue.offer(course[0]);
                sum += course[0];
            } else if (!queue.isEmpty() && queue.peek() > course[0]) {
                sum -= queue.poll() - course[0];
                queue.offer(course[0]);
            }
        }
        return queue.size();
    }
}
