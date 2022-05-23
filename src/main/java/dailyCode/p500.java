package dailyCode;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-09-08
 * @Description
 */
public class p500 {

    // p500 键盘行
    public String[] findWords(String[] words) {
        Set<Character> set1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
        Set<Character> set2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l'));
        Set<Character> set3 = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));
        List<String> res = new ArrayList<>();
        for (String str : words) {
            boolean flag = true;
            char[] chars = str.toLowerCase().toCharArray();
            if (set1.contains(chars[0])) {
                for (char c : chars) {
                    if (!set1.contains(c)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (set2.contains(chars[0])) {
                for (char c : chars) {
                    if (!set2.contains(c)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (set3.contains(chars[0])) {
                for (char c : chars) {
                    if (!set3.contains(c)) {
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                res.add(str);
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    // p502 IPO
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int cur = 0;
        int[][] help = new int[n][2];
        for (int i = 0; i < n; i++) {
            help[i][0] = capital[i];
            help[i][1] = profits[i];
        }
        Arrays.sort(help, Comparator.comparingInt(o -> o[0]));
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            while (cur < n && help[cur][0] <= w) {
                cur++;
                queue.offer(help[cur][1]);
            }
            if (!queue.isEmpty()) {
                w += queue.poll();
            }
        }
        return w;
    }

    // p503 下一个更大的元素「单调栈」
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int index = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                res[stack.pop()] = nums[index];
            }
            stack.push(index);
        }
        return res;
    }

    // p509 斐波那契数「动态规划」
    public int fib(int n) {
        if (n < 2) return n;
        int a = 0, b = 1, c = 1;
        while (n > 1) {
            c = a + b;
            a = b;
            b = c;
            n--;
        }
        return c;
    }

    // p516 最长回文子序列「动态规划」
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n]; // s[i...j]是否是回文子序列
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

    // p520 检测大写字母
    public boolean detectCapitalUse(String word) {
        if (word.equals(word.toLowerCase())) return true;
        if (word.equals(word.toUpperCase())) return true;
        int index = 1, n = word.length();
        if (Character.isUpperCase(word.charAt(0))) {
            while (index < n && Character.isLowerCase(word.charAt(index))) {
                index++;
            }
        }
        return index == n;
    }

    // p521 最长特殊序列I
    public int findLUSlength(String a, String b) {
        int m = a.length(), n = b.length();
        if (m != n) return Math.max(m, n);
        return a.equals(b) ? -1 : m;
    }

    // p524 通过删除字母匹配到字典里最长单词
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() != o2.length()) return o2.length() - o1.length();
            else return o1.compareTo(o2);
        });
        for (String str : dictionary) {
            if (containsSubstring(s, str)) return str;
        }
        return "";
    }

    public boolean containsSubstring(String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) j++;
            i++;
        }
        return j == b.length();
    }

    // p526 优美的排列「状态压缩dp」
    public int countArrangement(int n) {
        int mask = 1 << n;
        int[] dp = new int[mask];
        dp[0] = 1;
        for (int i = 1; i < mask; i++) {
            int num = Integer.bitCount(i);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0 && (num % (j + 1) == 0 || (j + 1) % num == 0)) dp[i] += dp[i ^ (1 << j)];
            }
        }
        return dp[mask - 1];
    }

    // p537 复数乘法「字符串分割」
    public String complexNumberMultiply(String num1, String num2) {
        String[] n1 = num1.substring(0, num1.length() - 1).split("\\+");
        String[] n2 = num2.substring(0, num2.length() - 1).split("\\+");
        int a = Integer.parseInt(n1[0]), b = Integer.parseInt(n1[1]), c = Integer.parseInt(n2[0]), d = Integer.parseInt(n2[1]);
        int real = a * c - b * d, virtual = a * d + b * c;
        return real + "+" + virtual + "i";
    }

    // p539 最小时间差
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 24 * 60) return 0;
        int[] minutes = new int[n];
        for (int i = 0; i < n; i++) {
            String[] time = timePoints.get(i).split(":");
            minutes[i] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        }
        Arrays.sort(minutes);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            res = Math.min(minutes[i] + 24 * 60 - minutes[i + 1], Math.min(res, minutes[i + 1] - minutes[i]));
        }
        res = Math.min(minutes[0] + 24 * 60 - minutes[n - 1], res);
        return res;
    }

    // p553 最优除法
    public String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1) return Integer.toString(nums[0]);
        if (n == 2) return nums[0] + "/" + nums[1];
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/(");
        for (int i = 1; i < n; i++) {
            sb.append(nums[i]);
            if (i != n - 1) sb.append("/");
            else sb.append(")");
        }
        return sb.toString();
    }

    // p564 寻找最近的回文数「模拟」
    public String nearestPalindromic(String n) {
        int len = n.length();
        long originNum = Long.parseLong(n);
        long[] candidates = new long[5];
        candidates[3] = (long) Math.pow(10, len) + 1; // 进位，3位数边4位数，1001
        candidates[4] = (long) Math.pow(10, len - 1) - 1; // 退位，3位数变2位数，99
        long prefix = Long.parseLong(n.substring(0, (len + 1) / 2)) - 1;
        for (int i = 0; i < 3; i++) {
            StringBuilder sb = new StringBuilder();
            String pre = String.valueOf(prefix);
            sb.append(pre); // 前半段
            sb.append(reverse(pre).substring(len & 1)); // 后半部分
            candidates[i] = Long.parseLong(sb.toString());
            prefix++;
        }
        long res = -1;
        for (long candidate : candidates) {
            if (candidate != originNum) {
                if (res == -1 || Math.abs(candidate - originNum) < Math.abs(res - originNum) || (Math.abs(candidate - originNum) == Math.abs(res - originNum) && candidate < res)) {
                    res = candidate;
                }
            }
        }
        return Long.toString(res);
    }

    public String reverse(String s) {
        char[] chars = s.toCharArray();
        int l = 0, r = chars.length - 1;
        while (l < r) {
            char t = chars[l];
            chars[l] = chars[r];
            chars[r] = t;
            l++;
            r--;
        }
        return new String(chars);
    }

    // p576 出界的路经数
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int res = 0;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startColumn});
        while (maxMove >= 0 && !queue.isEmpty()) {
            int size = queue.size();
            maxMove--;
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int x = poll[0], y = poll[1];
                if (boundary(x, y, m, n)) {
                    res++;
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    queue.offer(new int[]{x + dx[j], y + dy[j]});
                }
            }
        }
        return res;
    }

    boolean boundary(int x, int y, int m, int n) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }

    // p587 安装栅栏「Andrew算法，求凸包」
    public int[][] outerTrees(int[][] trees) {
        Arrays.sort(trees, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int n = trees.length, index = 0;
        int[] stack = new int[n + 5];
        boolean[] isVis = new boolean[n];
        stack[++index] = 0;
        for (int i = 1; i < n; i++) {
            int[] tree = trees[i];
            while (index > 1) {
                int[] a = trees[stack[index - 1]], b = trees[stack[index]];
                if (getArea(a, b, tree) > 0) isVis[stack[index--]] = false;
                else break;
            }
            stack[++index] = i;
            isVis[i] = true;
        }
        int size = index;
        for (int i = n - 1; i >= 0; i--) {
            if (isVis[i]) continue;
            int[] tree = trees[i];
            while (index > size) {
                int[] a = trees[stack[index - 1]], b = trees[stack[index]];
                if (getArea(a, b, tree) > 0) index--;
                else break;
            }
            stack[++index] = i;
        }
        int[][] res = new int[index - 1][2];
        for (int i = 1; i < index; i++) {
            res[i - 1] = trees[stack[i]];
        }
        return res;
    }

    public int[] subtraction(int[] x, int[] y) {
        return new int[]{x[0] - y[0], x[1] - y[1]}; // 向量相减
    }

    public int cross(int[] x, int[] y) {
        return x[0] * y[1] - x[1] * y[0]; // 叉乘
    }

    public int getArea(int[] a, int[] b, int[] c) {
        return cross(subtraction(b, a), subtraction(c, a));
    }

    // p594 最长和谐子序列
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
        }
        return res;
    }

}
