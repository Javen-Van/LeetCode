import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2021-08-10
 * @Description
 */
public class p413 {

    // p413 等差数列划分
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] - nums[i - 1];
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int start = i + 1;
            while (start < n - 1 && dp[start] == dp[start + 1]) start++;
            int len = start - i + 1;
            if (len >= 3) res += (1 + len - 2) * (len - 2) / 2;
            i = start - 1;
        }
        return res;
    }

    // p407 三维接雨水
    public int trapRainWater(int[][] heightMap) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] isVisit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    queue.offer(new int[]{i * n + j, heightMap[i][j]});
                    isVisit[i][j] = true;
                }
            }
        }
        int res = 0;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = temp[0] / n + dx[i];
                int ny = temp[0] % n + dy[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !isVisit[nx][ny]) {
                    if (heightMap[nx][ny] < temp[1]) {
                        res += (temp[1] - heightMap[nx][ny]);
                    }
                    isVisit[nx][ny] = true;
                    queue.offer(new int[]{nx * n + ny, Math.max(temp[1], heightMap[nx][ny])});
                }
            }
        }
        return res;
    }

    // p423 从英文中重建数字
    public String originalDigits(String s) {
        // zero one two three four five six seven eight nine
        int[] count = new int[26];
        int[] res = new int[10];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        res[0] = count['z' - 'a'];
        res[2] = count['w' - 'a'];
        res[4] = count['u' - 'a'];
        res[6] = count['x' - 'a'];
        res[8] = count['g' - 'a'];
        res[5] = count['f' - 'a'] - res[4];
        res[7] = count['v' - 'a'] - res[5];
        res[1] = count['o' - 'a'] - res[0] - res[2] - res[4];
        res[3] = count['r' - 'a'] - res[0] - res[4];
        res[9] = count['i' - 'a'] - res[8] - res[5] - res[6];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < res[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    // p458 可怜的小猪
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil((Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1)));
    }

    @Test
    public void test() {

    }
}
