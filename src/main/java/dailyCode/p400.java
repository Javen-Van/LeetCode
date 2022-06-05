package dailyCode;

import bean.Node;
import bean.TreeNode;
import bean.Trie;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class p400 {

    // dailyCode.p400
    public int findNthDigit(int n) {
        int i = 1, j = 1;
        while (n > 9 * i * j) {
            n -= 9 * i * j;
            i *= 10;
            j++;
        }
        int temp = (n % (i * j)) == 0 ? 10 : n % (i * j) / j;
        int num = (n / (i * j) + 1) * i + temp - 1;
        int index = n % (i * j) % j;
        while (index > 0) {
            index--;
            num /= 10;
        }
        return num % 10;
    }

    // p407 三维接雨水「优先队列」
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

    // p414 第三大的数
    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) set.remove(set.first());
        }
        return set.size() == 3 ? set.last() : set.first();
    }

    // p416 分割等和子集「动态规划」
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                dp[i] |= dp[num - i];
            }
        }
        return dp[sum];
    }

    // p417 太平洋大西洋水流问题「dfs」
    int[][] heights;
    int[] dif = {0, 1, 0, -1, 0};
    int m, n;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.m = heights.length;
        this.n = heights[0].length;
        this.heights = heights;
        boolean[][] canFlowPac = new boolean[m][n], canFlowAtl = new boolean[m][n];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            dfs(i, 0, canFlowPac);
            dfs(i, n - 1, canFlowAtl);
        }
        for (int i = 0; i < n; i++) {
            dfs(0, i, canFlowPac);
            dfs(m - 1, i, canFlowAtl);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canFlowAtl[i][j] && canFlowPac[i][j]) res.add(Arrays.asList(i, j));
            }
        }
        return res;
    }

    public void dfs(int raw, int col, boolean[][] grid) {
        if (grid[raw][col]) return;
        grid[raw][col] = true;
        for (int i = 0; i < 4; i++) {
            int x = raw + dif[i], y = col + dif[i + 1];
            if (x >= 0 && x < m && y >= 0 && y < n && heights[x][y] >= heights[raw][col]) dfs(x, y, grid);
        }
    }

    // p419 甲板上的战舰「dfs」
    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    res++;
                    dfs(board, i, j);
                }
            }
        }
        return res;
    }

    void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] == '.') return;
        board[x][y] = '.';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
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

    // p427 建立四叉树P「递归」
    public Node construct(int[][] grid) {
        return dfs(0, 0, grid.length, grid.length, grid);
    }

    public Node dfs(int sX, int sY, int eX, int eY, int[][] grid) {
        boolean same = true;
        out:
        for (int i = sX; i < eX; i++) {
            for (int j = sY; j < eY; j++) {
                if (grid[i][j] != grid[sX][sY]) {
                    same = false;
                    break out;
                }
            }
        }
        if (same) return new Node(grid[sX][sY] == 1, true);
        return new Node(true, false,
                dfs(sX, sY, (sX + eX) / 2, (sY + eY) / 2, grid),
                dfs(sX, (sY + eY) / 2, (sX + eX) / 2, eY, grid),
                dfs((sX + eX) / 2, sY, eX, (sY + eY) / 2, grid),
                dfs((sX + eX) / 2, (sY + eY) / 2, eX, eY, grid));
    }

    public static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    // p429 N叉树的层序遍历「BFS」
    public List<List<Integer>> levelOrder(bean.Node root) {
        Queue<bean.Node> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> layer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                bean.Node cur = queue.poll();
                layer.add(cur.val);
                if (cur.children != null && cur.children.size() != 0) {
                    for (bean.Node child : cur.children) {
                        queue.offer(child);
                    }
                }
            }
            res.add(layer);
        }
        return res;
    }

    // p433 最小基因变化「dfs」
    int ans = Integer.MAX_VALUE;
    String[] bank;

    public int minMutation(String start, String end, String[] bank) {
        this.bank = bank;
        List<List<Integer>> table = new ArrayList<>();
        int n = bank.length;
        for (int i = 0; i < n; i++) {
            table.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (countDiff(bank[i], bank[j]) == 1) {
                    table.get(i).add(j);
                    table.get(j).add(i);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (countDiff(start, bank[i]) == 1) {
                dfs(i, end, table, 1, new boolean[n]);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int countDiff(String a, String b) {
        int count = 0;
        for (int k = 0; k < 8; k++) {
            if (a.charAt(k) != b.charAt(k)) count++;
        }
        return count;
    }

    public void dfs(int cur, String end, List<List<Integer>> table, int step, boolean[] isVis) {
        if (this.bank[cur].equals(end)) {
            ans = Math.min(ans, step);
            return;
        }
        isVis[cur] = true;
        for (int next : table.get(cur)) {
            if (!isVis[next]) dfs(next, end, table, step + 1, isVis);
        }
        isVis[cur] = false;
    }

    // p440 字典序的第k小数字
    public int findKthNumber(int n, int k) {
        int prefix = 1, p = 1;
        while (p < k) {
            int count = getCount(n, prefix);
            if (count + p > k) {
                prefix *= 10;
                p++;
            } else {
                prefix++;
                p += count;
            }
        }
        return prefix;
    }

    public int getCount(int n, int prefix) {
        int count = 0;
        long cur = prefix, next = prefix + 1;
        while (cur <= n) {
            count += Math.min(n + 1, next) - cur;
            cur *= 10;
            next *= 10;
        }
        return count;
    }

    // p442 数组中的重复数据「技巧题」
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int idx = Math.abs(num);
            if (nums[idx - 1] > 0) nums[idx - 1] *= -1;
            else res.add(idx);
        }
        return res;
    }

    // p443 压缩字符串
    public int compress(char[] chars) {
        int start = 0, count = 0, index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[start]) {
                chars[index] = chars[start];
                index = handle(chars, index + 1, i - start);
                start = i;
            }
            if (i == chars.length - 1) {
                chars[index] = chars[start];
                index = handle(chars, index + 1, i - start + 1);
            }
        }
        System.out.println(chars);
        return index;
    }

    public int handle(char[] c, int index, int num) {
        if (num == 1) return index;
        int count = 0;
        while (num > 0) {
            c[index++] = (char) (num % 10 + '0');
            num /= 10;
            count++;
        }
        int left = index - count, right = index - 1;
        while (left < right) {
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        return index;
    }

    // p446 等差数列划分2 - 子序列
    public int numberOfArithmeticSlices2(int[] nums) {
        int n = nums.length;
        int res = 0;
        Map<Long, Integer>[] maps = new Map[n];
        for (int i = 0; i < n; i++) {
            maps[i] = new HashMap<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = (long) nums[i] - nums[j];
                Integer count = maps[j].getOrDefault(d, 0);
                res += count;
                maps[i].put(d, maps[i].getOrDefault(d, 0) + count + 1);
            }
        }
        return res;
    }

    // p450 删除二叉搜索树中的节点「递归」
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        } else {
            if (root.left == null && root.right == null) return null;
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            TreeNode successor = root.right;
            while (successor.left != null) successor = successor.left;
            root.right = deleteNode(root.right, successor.val);
            successor.right = root.right;
            successor.left = root.left;
            return successor;
        }
    }

    // p456 132模式「单调栈」
    public boolean find132pattern(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE, n = nums.length; // max记录3
        stack.push(nums[n - 1]); // 从右向左遍历1
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < max) return true;
            while (!stack.isEmpty() && nums[i] > stack.peek()) max = stack.pop();
            if (nums[i] > max) stack.push(nums[i]); // max更新过说明存在3 > 2
        }
        return false; // 遍历结束若还未找到返回false
    }

    // p457 环形数组是否存在循环
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i, fast = next(nums, i);
            // 判断非零且方向相同
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        return true;
                    } else {
                        break;
                    }
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            int add = i;
            while (nums[add] * nums[next(nums, add)] > 0) {
                int tmp = add;
                add = next(nums, add);
                nums[tmp] = 0;
            }
        }
        return false;
    }

    public int next(int[] nums, int cur) {
        int n = nums.length;
        return ((cur + nums[cur]) % n + n) % n; // 保证返回值在 [0,n) 中
    }

    // p458 可怜的小猪
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        return (int) Math.ceil((Math.log(buckets) / Math.log(minutesToTest / minutesToDie + 1)));
    }

    // p462 最少移动次数使数组元素相等「数学」
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, res = 0, x = nums[n / 2];
        for (int num : nums) {
            res += Math.abs(num - x);
        }
        return res;
    }

    // p463 寻找右区间「哈希表」
    public int[] findRightInterval(int[][] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int n = intervals.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }
        for (int i = 0; i < n; i++) {
            Integer ceil = map.ceilingKey(intervals[i][1]);
            res[i] = ceil == null ? -1 : map.get(ceil);
        }
        return res;
    }

    // p464 我能赢吗「状态压缩dp」
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 < desiredTotal) return false;
        int[] dp = new int[1 << maxChoosableInteger];
        Arrays.fill(dp, -1);
        return dfs(0, maxChoosableInteger, desiredTotal, 0, dp) == 1;
    }

    public int dfs(int mask, int max, int desiredTotal, int total, int[] dp) {
        if (dp[mask] == -1) {
            dp[mask] = 0;
            for (int i = 0; i < max; i++) {
                if (((mask >> i) & 1) == 0) {
                    if (i + 1 + total >= desiredTotal || dfs(mask | (1 << i), max, desiredTotal, i + 1 + total, dp) == 0) {
                        dp[mask] = 1;
                        break;
                    }
                }
            }
        }
        return dp[mask];
    }

    // p467 环绕字符串中唯一的子字符串「动态规划」
    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int n = p.length();
        for (int start = 0, i = 1; i <= n; i++) {
            char pre = p.charAt(i - 1);
            count[pre - 'a'] = Math.max(count[pre - 'a'], i - start);
            if (i == n || (p.charAt(i) - pre + 26) % 26 != 1) start = i;
        }
        return Arrays.stream(count).sum();
    }
  
    // p468 验证IP地址
    public String validIPAddress(String queryIP) {
        if (queryIP.startsWith(".") || queryIP.startsWith(":") || queryIP.endsWith(".") || queryIP.endsWith(":"))
            return "Neither";
        String[] IPv4 = queryIP.split("\\.");
        String[] IPv6 = queryIP.split(":");
        if (isIPv4(IPv4)) return "IPv4";
        if (isIPv6(IPv6)) return "IPv6";
        return "Neither";
    }

    public boolean isIPv4(String[] IP) {
        if (IP.length != 4) return false;
        for (String s : IP) {
            if ("".equals(s) || s.length() > 3) return false;
            if (s.charAt(0) == '0' && s.length() > 1) return false;
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) return false;
            }
            if (Integer.parseInt(s) > 255) return false;
        }
        return true;
    }

    public boolean isIPv6(String[] IP) {
        if (IP.length != 8) return false;
        for (String s : IP) {
            if (s.length() == 0 || s.length() > 4) return false;
            for (int i = 0; i < s.length(); i++) {
                char c = Character.toLowerCase(s.charAt(i));
                if (!Character.isDigit(c) && (c < 'a' || c > 'f')) return false;
            }
        }
        return true;
    }

    // p472 连接词
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> res = new ArrayList<>();
        Trie trie = new Trie();
        for (String word : words) {
            if (word.length() == 0) continue;
            if (dfs(word, 0, trie)) res.add(word);
            else trie.insert(word);
        }
        return res;
    }

    public boolean dfs(String word, int index, Trie root) {
        if (index == word.length()) return root.isWord;
        for (int i = index; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            Trie child = root.children[idx];
            if (child == null) return false;
            if (child.isWord && dfs(word, i + 1, root)) {
                return true;
            }
        }
        return false;
    }

    // p473 火柴拼正方形「状态压缩dp」
    public boolean makeSquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum(), n = matchsticks.length;
        int target = sum / 4, mask = 1 << n;
        Arrays.sort(matchsticks);
        if (n < 4 || sum % 4 != 0 || target < matchsticks[n - 1]) return false;
        boolean[] dp = new boolean[mask];
        int[] curSum = new int[mask];
        dp[0] = true;
        for (int i = 0; i < mask; i++) {
            if (dp[i]) {
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) == 0 && !dp[i | (1 << j)]) {
                        if ((curSum[i] % target) + matchsticks[j] <= target) {
                            curSum[i | (1 << j)] = curSum[i] + matchsticks[j];
                            dp[i | (1 << j)] = true;
                        } else break;
                    }
                }
            }
        }
        return dp[mask - 1];
    }

    // p475 供暖器「排序 + 双指针」
    public int findRadius(int[] houses, int[] heaters) {
        int n = houses.length, m = heaters.length;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int dis = Math.abs(houses[i] - heaters[j]);
            while (j < m - 1 && dis >= Math.abs(houses[i] - heaters[j + 1])) {
                j++;
                dis = Math.abs(houses[i] - heaters[j]);
            }
            res = Math.max(dis, res);
        }
        return res;
    }

    // p479 最大回文数乘积
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        int MOD = 1337, upper = (int) (Math.pow(10, n) - 1), res = 0;
        for (int left = upper; res == 0; left--) {
            StringBuilder sb = new StringBuilder();
            sb.append(left);
            long num = Long.parseLong(left + sb.reverse().toString());
            for (long i = upper; i * i >= num; i--) {
                if (num % i == 0) {
                    res = (int) (num % MOD);
                    break;
                }
            }
        }
        return res;
    }

    // p482 密钥格式化
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder(s.replaceAll("-", ""));
        int n = sb.length();
        while (n >= 0) {
            n -= k;
            sb.insert(n, "-");
        }
        return sb.toString().toUpperCase();
    }

    @Test
    public void test() {
        System.out.println(largestPalindrome(2));
    }

}
