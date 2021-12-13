import java.util.*;

public class p100 {

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

    // p150 逆波兰表达式
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
}
