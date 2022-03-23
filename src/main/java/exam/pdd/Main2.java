package exam.pdd;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int T = sc.nextInt();
            for (int i = 0; i < T; i++) {
                int n = sc.nextInt(), v = sc.nextInt();
                int[] nums = new int[n], start = new int[n], end = new int[n];
                Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
                    if (o1[2] != o2[2]) return o1[2] - o2[2];
                    else {
                        if (o1[1] != o2[1]) return o1[1] - o2[1];
                        return o1[0] - o2[0];
                    }
                });
                for (int j = 0; j < n; j++) {
                    nums[i] = sc.nextInt();
                    start[i] = sc.nextInt();
                    end[i] = sc.nextInt();
                    queue.offer(new int[]{nums[i], start[i], end[i]});
                }
                int day = 1, res = 0, cost = v;
                while (!queue.isEmpty()) {
                    while (!queue.isEmpty() && queue.peek()[2] < day) {
                        queue.poll();
                    }
                    if (queue.isEmpty()) break;
                    while (queue.peek()[1] > day) day++;
                    int[] cur = queue.poll();
                    if (cur[0] > v) {
                        res += v;
                        day++;
                        queue.offer(new int[]{cur[0] - v, cur[1], cur[2]});
                        v = cost;
                    } else {
                        res += cur[0];
                        v -= cur[0];
                    }
                }
                System.out.println(res);
            }
        }
    }
}
