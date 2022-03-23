package dailyCode;

import java.util.*;

public class p100 {

    // p139 单词拆分「动态规划」
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

    // p149 直线上最多的点数
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) return n;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (res > n / 2 || res >= n - i) {
                break;
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                int x = points[j][0] - points[i][0];
                int y = points[j][1] - points[i][1];
                if (x == 0) {
                    y = 1;
                } else if (y == 0) {
                    x = 1;
                } else {
                    if (y < 0) {
                        x = -x;
                        y = -y;
                    }
                    int gca = gca(Math.abs(x), Math.abs(y));
                    x /= gca;
                    y /= gca;
                }
                int index = 20001 * x + y;
                map.put(index, map.getOrDefault(index, 0) + 1);
            }
            int max = 0;
            for (Integer value : map.values()) {
                max = Math.max(max, value + 1);
            }
            res = Math.max(max, res);
        }
        return res;
    }

    public int gca(int a, int b) {
        return b == 0 ? a : gca(b, a % b);
    }

    // p150 逆波兰表达式「栈」
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> set = new HashSet<>();
        set.add("+");
        set.add("-");
        set.add("/");
        set.add("*");
        for (String s : tokens) {
            if (set.contains(s)) {
                int a = stack.pop();
                int b = stack.pop();
                int c;
                switch (s) {
                    case "+":
                        c = a + b;
                        break;
                    case "-":
                        c = b - a;
                        break;
                    case "/":
                        c = b / a;
                        break;
                    default:
                        c = b * a;
                }
                stack.push(c);
            } else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }

    // p165 比较版本号
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int index = 0;
        while (index < v1.length && index < v2.length) {
            int num1 = Integer.parseInt(v1[index]);
            int num2 = Integer.parseInt(v2[index]);
            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
            index++;
        }
        if (index == v1.length) {
            while (index < v2.length) {
                if (Integer.parseInt(v2[index]) > 0) return -1;
                index++;
            }
        } else if (index == v2.length) {
            while (index < v1.length) {
                if (Integer.parseInt(v1[index]) > 0) return 1;
                index++;
            }
        }
        return 0;
    }

    // p187 重复的DNA序列
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if (n < 10) return new ArrayList<>();
        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 10; i < n; i++) {
            String substring = s.substring(i - 10, i);
            if (set.contains(substring)) res.add(substring);
            else set.add(substring);
        }
        return new ArrayList<>(res);
    }
}
