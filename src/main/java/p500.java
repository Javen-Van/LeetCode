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

    //p594 最长和谐子序列
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
