package dailyCode;

import java.util.*;

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

    // p668 乘法表中第k小的数「二分查找」
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int x = l + (r - l) / 2;
            int line = x / n, count = line * n;
            for (int i = line + 1; i <= m; i++) {
                count += x / i;
            }
            if (count >= k)
                r = x;
            else
                l = x + 1;
        }
        return l;
    }

    // p675 为高尔夫比赛砍树「BFS」
    public int cutOffTree(List<List<Integer>> forest) {
        int m = forest.size(), n = forest.get(0).size(), res = 0;
        int[][] forests = new int[m][n];
        int[] pre = { 0, 0 };
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int height = forest.get(i).get(j);
                forests[i][j] = height;
                if (height > 1)
                    queue.offer(new int[] { height, i, j });
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int distance = distance(pre, new int[] { cur[1], cur[2] }, forests);
            if (distance == -1)
                return -1;
            res += distance;
            pre[0] = cur[1];
            pre[1] = cur[2];
        }
        return res;
    }

    public int distance(int[] source, int[] target, int[][] forest) {
        int count = 0, m = forest.length, n = forest[0].length;
        int[] dif = { 0, 1, 0, -1, 0 };
        boolean[][] isVis = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(source);
        isVis[source[0]][source[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int[] cur = queue.poll();
                if (cur[0] == target[0] && cur[1] == target[1])
                    return count;
                for (int i = 0; i < 4; i++) {
                    int x = cur[0] + dif[i], y = cur[1] + dif[i + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !isVis[x][y] && forest[x][y] != 0) {
                        isVis[x][y] = true;
                        queue.offer(new int[] { x, y });
                    }
                }
            }
            count++;
        }
        return -1;
    }

    // p686 重复叠加字符串匹配「KMP算法」
    public int repeatedStringMatch(String a, String b) {
        int m = a.length(), n = b.length();
        if (n == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < m; i++) {
            if (a.charAt(i) == b.charAt(0)) {
                int temp = 1, l = i, j = 0;
                while (j < n && a.charAt(l) == b.charAt(j)) {
                    j++;
                    l++;
                    if (l == m && j != n) {
                        l = 0;
                        temp++;
                    }
                }
                if (j == n) {
                    res = temp;
                    break;
                }
            }
        }
        return res;
    }

    // p689 三个无重叠子数组的最大和「滑动窗口」，也可以用DP？
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int sum1 = 0, idx1 = 0, maxSum1 = 0, n = nums.length;
        int sum2 = 0, idx2 = 0, maxSum2 = 0;
        int sum3 = 0, idx1Sum2 = 0, maxSum3 = 0;
        int[] res = new int[3];
        for (int i = 2 * k; i < n; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= 3 * k - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    idx1 = i - 3 * k + 1;
                }
                if (sum2 + maxSum1 > maxSum2) {
                    maxSum2 = sum2 + maxSum1;
                    idx1Sum2 = idx1; // 记录2个无重叠子数组的最大和的答案
                    idx2 = i - 2 * k + 1;
                }
                if (sum3 + maxSum2 > maxSum3) {
                    maxSum3 = sum3 + maxSum2;
                    res[0] = idx1Sum2;
                    res[1] = idx2;
                    res[2] = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return res;
    }

    // p691 贴纸拼词「状态压缩dp+记忆化搜索」
    public int minStickers(String[] stickers, String target) {
        int n = target.length(), mask = 1 << n;
        int[] dp = new int[mask];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        int res = dfs(stickers, target, dp, mask - 1);
        return res <= n ? res : -1;
    }

    public int dfs(String[] stickers, String target, int[] dp, int mask) {
        int n = target.length();
        if (dp[mask] < 0) {
            int res = n + 1;
            for (String sticker : stickers) {
                int left = mask;
                int[] count = new int[26];
                for (int i = 0; i < sticker.length(); i++) {
                    count[sticker.charAt(i) - 'a']++;
                }
                for (int i = 0; i < n; i++) {
                    int index = target.charAt(i) - 'a';
                    if (((mask >> i) & 1) == 1 && count[index] > 0) {
                        count[index]--;
                        left ^= 1 << i;
                    }
                }
                if (left < mask)
                    res = Math.min(res, dfs(stickers, target, dp, left) + 1);
            }
            dp[mask] = res;
        }
        return dp[mask];
    }

    // p699 掉落的方块「线段树」
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> res = new ArrayList<>();
        SegmentTree tree = new SegmentTree();
        int max = 0;
        for (int[] position : positions) {
            int left = position[0], width = position[1], right = left + width - 1;
            int height = tree.query(left, right) + width;
            max = Math.max(max, height);
            res.add(max);
            tree.update(left, right, height);
        }
        return res;
    }

    public static class Node {
        Node left;
        Node right;
        int l;
        int r;
        int val;
        boolean add;

        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int getMid() {
            return l + (r - l) / 2;
        }

        public void update() {
            this.left.add = true;
            this.right.add = true;
            this.left.val = this.val;
            this.right.val = this.val;
            this.add = false;
        }
    }

    public static class SegmentTree {
        private final Node root = new Node(1, 1000000000);

        public void update(int l, int r, int val) {
            update(l, r, val, this.root);
        }

        private void update(int l, int r, int val, Node node) {
            if (l > r)
                return;
            if (l <= node.l && node.r <= r) {
                node.val = val;
                node.add = true;
                return;
            }
            pushDown(node);
            if (l <= node.getMid())
                update(l, r, val, node.left);
            if (r > node.getMid())
                update(l, r, val, node.right);
            pushUp(node);
        }

        public int query(int l, int r) {
            return query(l, r, this.root);
        }

        private int query(int l, int r, Node node) {
            if (l > r)
                return 0;
            if (l <= node.l && node.r <= r)
                return node.val;
            pushDown(node);
            int res = 0;
            if (l <= node.getMid())
                res = Math.max(res, query(l, r, node.left));
            if (r > node.getMid())
                res = Math.max(res, query(l, r, node.right));
            return res;
        }

        public void pushDown(Node node) {
            if (node.left == null)
                node.left = new Node(node.l, node.getMid());
            if (node.right == null)
                node.right = new Node(node.getMid() + 1, node.r);
            if (node.add)
                node.update();
        }

        public void pushUp(Node node) {
            node.val = Math.max(node.left.val, node.right.val);
        }
    }
}
