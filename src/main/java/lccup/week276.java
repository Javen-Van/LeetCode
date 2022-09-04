package lccup;

import org.junit.Test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2022-01-16
 * @Description
 */
public class week276 {

    // Q1
    public String[] divideString(String s, int k, char fill) {
        int mod = s.length() % k;
        int n = mod == 0 ? s.length() / k : s.length() / k + 1;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            if (mod != 0 && i == n - 1) {
                StringBuilder sb = new StringBuilder(s.substring(i * k));
                for (int j = 0; j < k - mod; j++) {
                    sb.append(fill);
                }
                res[i] = sb.toString();
            } else {
                res[i] = s.substring(i * k, (i + 1) * k);
            }
        }
        return res;
    }

    // Q2
    public int minMoves(int target, int maxDoubles) {
        int res = 0;
        while (target != 1 && maxDoubles > 0) {
            if (target % 2 == 0) {
                target /= 2;
                maxDoubles--;
            } else {
                target -= 1;
            }
            res++;
        }
        return res + target - 1;
    }

    // Q3
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        dp[n - 1] = questions[n - 1][0];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1],
                    questions[i][0] + ((questions[i][1] + i + 1) < n ? dp[questions[i][1] + i + 1] : 0));
        }
        return dp[0];
    }

    // Q4
    public long maxRunTime(int n, int[] batteries) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int battery : batteries) {
            queue.offer(battery);
        }
        long res = 0;
        while (queue.size() >= n) {
            int consume = queue.peek() == 1 ? 1 : queue.peek() - 1;
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                temp[i] = queue.poll() - consume;
            }
            for (int x : temp) {
                if (x != 0)
                    queue.offer(x);
            }
            res += consume;
        }
        return res;
    }

    @Test
    public void test() {
        int[] batteries = new int[] { 3, 3, 3 };
        System.out.println(maxRunTime(2, batteries));
    }
}
