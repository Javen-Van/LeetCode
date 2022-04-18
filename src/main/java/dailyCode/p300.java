package dailyCode;

import org.junit.Test;

import java.util.*;

public class p300 {

    // p300 最长递增子序列「贪心 + 二分查找」
    public int lengthOfLIS(int[] nums) {
        int len = 0, n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int num : nums) {
            if (num > dp[len]) dp[++len] = num;
            else binarySearch(dp, num, len);
        }
        return len + 1;
    }

    public void binarySearch(int[] nums, int target, int r) {
        int l = 0, median;
        while (l < r) {
            median = (l + r) / 2;
            if (nums[median] >= target) r = median;
            else l = median + 1;
        }
        nums[l] = target;
    }

    // p306 累加数
    public boolean isAdditiveNumber(String num) {
        return false;
    }

    // p310 最小高度树「bfs + 拓扑排序｜
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        List<List<Integer>> table = new ArrayList<>();
        int[] degree = new int[n];
        if (n == 1) {
            res.add(0);
            return res;
        }
        for (int i = 0; i < n; i++) {
            table.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
            table.get(edge[0]).add(edge[1]);
            table.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            res.clear();
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int cur = queue.poll();
                res.add(cur);
                for (int next : table.get(cur)) {
                    degree[next]--;
                    if (degree[next] == 1) queue.offer(next);
                }
            }
        }
        return res;
    }

    // p313 超级丑数「动态规划」
    public int nthSuperUglyNumber(int n, int[] primes) {
        int len = primes.length;
        int[] dp = new int[n + 1];
        int[] point = new int[len];
        Arrays.fill(point, 1);
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < len; j++) {
                min = Math.min(min, dp[point[j]] * primes[j]);
            }
            for (int j = 0; j < len; j++) {
                if (dp[point[j]] * primes[j] == min) point[j]++;
            }
            dp[i] = min;
        }
        return dp[n];
    }

    // p322 零钱兑换「动态规划」
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // p357 统计各位数字都不同的数字个数「数学」
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int res = 10, cur = 9;
        for (int i = 0; i < n - 1; i++) {
            cur *= 9 - i;
            res += cur;
        }
        return res;
    }

    int MOD = 1337;

    // p372 超级次方
    public int superPow(int a, int[] b) {
        return dfs(a, b, b.length - 1);
    }

    public int dfs(int a, int[] b, int i) {
        if (i == -1) return 1;
        return pow(dfs(a, b, i - 1), 10) * pow(a, b[i]) % MOD;
    }

    public int pow(int a, int n) {
        int res = 1;
        a %= MOD;
        while (n > 0) {
            res = (res * a) % MOD;
            n--;
        }
        return res;
    }

    // p373 查找和最小的k对数字「多路归并 + 优先队列」
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<int[]> queue = new PriorityQueue<>(((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]]));
        int m = nums1.length, n = nums2.length;
        for (int i = 0; i < m; i++) {
            queue.offer(new int[]{i, 0});
        }
        while (k > 0 && !queue.isEmpty()) {
            int[] cur = queue.poll();
            res.add(new ArrayList<>(Arrays.asList(nums1[cur[0]], nums2[cur[1]])));
            if (cur[1] < n - 1) queue.offer(new int[]{cur[0], cur[1] + 1});
            k--;
        }
        return res;
    }

    // p378 有序矩阵中第K小的元素
    // 方法一：「多路归并 + 优先队列」
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        Queue<int[]> queue = new PriorityQueue<>((Comparator.comparingInt(o -> matrix[o[0]][o[1]])));
        for (int i = 0; i < Math.min(k, n); i++) {
            queue.offer(new int[]{i, 0});
        }
        while (k > 1 && !queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[1] < n - 1) queue.offer(new int[]{cur[0], cur[1] + 1});
            k--;
        }
        int[] res = queue.peek();
        return matrix[res[0]][res[1]];
    }

    // 方法二：「二分查找」
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length, min = matrix[0][0], max = matrix[n - 1][n - 1];
        int res;
        while (min < max) {
            res = min + (max - min) / 2;
        }
        return 1;
    }

    // p386 字典序排数
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int cur = 1;
        for (int i = 0; i < n; i++) {
            res.add(cur);
            if (cur * 10 <= n) {
                cur *= 10;
            } else {
                while (cur % 10 == 9 || cur + 1 > n) cur /= 10;
                cur++;
            }
        }
        return res;
    }

    // p393 UTF-8编码验证
    public boolean validUtf8(int[] data) {
        int n = data.length;
        for (int i = 0; i < n; i++) {
            int d = data[i], count = 0;
            for (int j = 7; j >= 0; j--) {
                if (((d >> j) & 1) == 1) count++;
                else break;
            }
            if (count == 1 || count > 4) return false;
            count = count == 0 ? 0 : count - 1;
            if (i + count >= n) return false;
            for (int j = i + 1; j <= count + i; j++) {
                int next = data[j];
                if (((next >> 7) & 1) != 1 || ((next >> 6) & 1) != 0) return false;
            }
            i += count;
        }
        return true;
    }

    // p397 整数替换
    public int integerReplacement(int n) {
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                res++;
                n /= 2;
            } else if (n % 4 == 1) {
                res += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    res = 2;
                    n = 1;
                } else {
                    res += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return res;
    }

}
