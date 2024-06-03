package dailyCode;

import bean.TreeNode;

import java.util.*;

/**
 * @author Javen
 * @create 2021-12-17
 * @Description
 */
public class p1600 {

    int res = 0, count = 0, zero, n;
    int[] delta;

    // p1601 最多可达成的换楼请求数目
    public int maximumRequests(int n, int[][] requests) {
        this.zero = n;
        this.n = n;
        this.delta = new int[n];
        dfs(requests, 0);
        return res;
    }

    public void dfs(int[][] requests, int pos) {
        if (pos == requests.length) { // 枚举结束
            if (zero == n) res = Math.max(res, count); // 每个楼栋都守恒
            return;
        }
        dfs(requests, pos + 1); // 不考虑当前需求
        // 考虑当前需求
        count++;
        int from = requests[pos][0], to = requests[pos][1], temp = zero;
        zero -= delta[from] == 0 ? 1 : 0; // 考虑前该楼栋变化为0
        zero -= delta[to] == 0 ? 1 : 0;
        delta[from]--;
        delta[to]++;
        zero += delta[from] == 0 ? 1 : 0;
        zero += delta[to] == 0 ? 1 : 0;
        dfs(requests, pos + 1);
        // 回溯
        count--;
        delta[from]++;
        delta[to]--;
        zero = temp;
    }

    // p1606 找到处理最多请求的服务器「优先队列」
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int[] requests = new int[k];
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[0] <= arrival[i]) {
                available.add(busy.poll()[1]);
            }
            if (available.isEmpty()) continue; // 没有空闲服务器
            Integer p = available.ceiling(i % k);
            if (p == null) p = available.first();
            requests[p]++;
            busy.offer(new int[]{arrival[i] + load[i], p});
            available.remove(p); // 从可用集合中删除
        }
        int maxRequest = Arrays.stream(requests).max().getAsInt();
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] == maxRequest) ret.add(i);
        }
        return ret;
    }

    // p1609 奇偶树「BFS」
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sort = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    int cur = node.val;
                    if (level % 2 == 0) {
                        if (cur % 2 != 1 || cur <= sort) return false;
                    } else {
                        if (cur % 2 != 0 || cur >= sort) return false;
                    }
                    sort = cur;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return true;
    }

    // p1610 可见点的最大数目「数学 + 双指针 + 滑动窗口」
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int locationX = location.get(0), locationY = location.get(1);
        int samePoints = 0;
        List<Double> theta = new ArrayList<>();
        for (List<Integer> point : points) {
            int x = point.get(0), y = point.get(1);
            if (x == locationX && y == locationY) {
                samePoints++;
                continue;
            }
            theta.add(Math.atan2(y - locationY, x - locationX));
        }
        theta.sort(Comparator.naturalOrder());
        int m = theta.size();
        for (int i = 0; i < m; i++) {
            theta.add(theta.get(i) + 2 * Math.PI); // 循环数组
        }
        int right = 0, res = 0;
        double r = Math.toRadians(angle);
        for (int i = 0; i < m; i++) {
            while (right < theta.size() && theta.get(right) - theta.get(i) <= r) {
                right++;
            }
            res = Math.max(right - i, res);
        }
        return res + samePoints;
    }

    // p1624 两个相同字符间的最长子字符串
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] idx = new int[26];
        Arrays.fill(idx, -1);
        int max = -1;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            if (idx[c] != -1) {
                max = Math.max(max, i - idx[c] - 1);
            } else idx[c] = i;
        }
        return max;
    }

    // p1636 按照频率将数组升序排序，-100 <= nums[i] <= 100
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        int[] bucket = new int[201];
        for (int num : nums) {
            bucket[num + 100]++;
        }
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return o2[0] - o1[0];
            return o1[1] - o2[1];
        });
        for (int i = 0; i < 201; i++) {
            if (bucket[i] != 0) {
                queue.offer(new int[]{i - 100, bucket[i]});
            }
        }
        int[] res = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < poll[1]; i++) {
                res[index] = poll[0];
                index++;
            }
        }
        return res;
    }
}
