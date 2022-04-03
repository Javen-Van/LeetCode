package dailyCode;

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

    // p419 甲板上的战舰
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

    @Test
    public void me() {
        System.out.println(findKthNumber(10, 3));
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

    // TODO: p473 火柴拼正方形
    public boolean makeSquare(int[] matchsticks) {
        long sum = 0L, max = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
            max = Math.max(max, matchstick);
        }
        if (matchsticks.length < 4 || sum % 4 != 0 || max > sum / 4) return false;
        return true;
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
        Trie trie = new Trie();
        trie.insert("leetcode");
        System.out.println(trie.isInTree("leetcodee"));
        System.out.println(trie.isInTree("aaa"));
    }

}