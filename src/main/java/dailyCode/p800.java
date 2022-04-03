package dailyCode;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/**
 * @author Javen
 * @create 2021-08-26
 * @Description 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 */
public class p800 {

    // p807 保持城市天际线
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            int maxX = 0;
            int maxY = 0;
            for (int j = 0; j < n; j++) {
                maxX = Math.max(maxX, grid[i][j]);
                maxY = Math.max(maxY, grid[j][i]);
            }
            x[i] = maxX;
            y[i] = maxY;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res += Math.min(x[i], y[j]) - grid[i][j];
            }
        }
        return res;
    }

    // p842 将数组拆分成斐波那契数列
    public List<Integer> splitIntoFibonacci(String num) {
        int n = num.length();
        return null;
    }

    // p850 矩形面积「线段树」
    public int rectangleArea(int[][] rectangles) {
        // 扫描线按照纵坐标递增排序
        int OPEN = 1, CLOSE = -1;
        int[][] lines = new int[rectangles.length * 2][];
        Set<Integer> Xvals = new HashSet<>();
        int t = 0;
        for (int[] rec : rectangles) {
            lines[t++] = new int[]{rec[1], OPEN, rec[0], rec[2]};
            lines[t++] = new int[]{rec[3], CLOSE, rec[0], rec[2]};
            Xvals.add(rec[0]);
            Xvals.add(rec[2]);
        }
        Arrays.sort(lines, Comparator.comparingInt(o -> o[0]));

        // 离散化x坐标
        Integer[] X = Xvals.toArray(new Integer[0]);
        Arrays.sort(X);
        Map<Integer, Integer> Xi = new HashMap<>();
        for (int i = 0; i < X.length; ++i) {
            Xi.put(X[i], i);
        }

        // 构建线段树
        SegTree segTree = new SegTree();
        SegNode root = segTree.build(0, X.length - 1, X);

        // 计算面积
        long ans = 0;
        long cur_x_sum = 0;
        int cur_y = lines[0][0];
        for (int[] line : lines) {
            int y = line[0], type = line[1], x1 = line[2], x2 = line[3];
            ans += cur_x_sum * (y - cur_y);
            segTree.update(root, Xi.get(x1), Xi.get(x2), type);
            cur_x_sum = root.len;
            cur_y = y;
        }

        ans %= 1_000_000_007;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        writer.write(1);
//        writer.newLine();
//        writer.flush();
//        List<String> list = new ArrayList<>();
//        list.toArray()
        return (int) ans;
    }

    class SegTree {
        public void pushUp(SegNode node) {
            if (node.count > 0) { // 是一条完整的线段
                node.len = node.X[node.end] - node.X[node.start];
                return;
            }
            if (node.end == node.start) { // 不是线段
                node.len = 0;
            } else { // 线段由左右两部分组成
                node.len = node.lc.len + node.rc.len;
            }
        }

        /**
         * 构建线段树
         * 此处的线段树区间和常规的线段树有点区别
         * 通常的线段树区间是[left, mid], [mid + 1, right], 此处时是[left, mid], [mid, right]
         * 因此构建线段树的终止条件要适当修改
         */
        public SegNode build(int start, int end, Integer[] X) {
            SegNode node = new SegNode(start, end, X);
            SegNode lc, rc;
            if (start + 1 == end) {
                lc = new SegNode(start, start, X);
                rc = new SegNode(end, end, X);
            } else {
                int mid = node.getMid();
                lc = build(start, mid, X);
                rc = build(mid, end, X);
            }
            node.lc = lc;
            node.rc = rc;
            return node;
        }

        public void update(SegNode node, int x1, int x2, int lineType) {
            if (x2 <= node.start || x1 >= node.end) { // 要更新的线段与当前区间无交集
                return;
            }
            if (x1 <= node.start && x2 >= node.end) { // 要更新的线段完全覆盖当前区间
                node.count += lineType;
                pushUp(node);
                return;
            }
            int mid = node.getMid();
            if (x2 <= mid) { // 只更新左孩子
                update(node.lc, x1, x2, lineType); // 更新左孩子
            } else if (x1 > mid) { // 只更新右孩子
                update(node.rc, x1, x2, lineType); // 更新右孩子
            } else {
                update(node.lc, x1, x2, lineType); // 更新左孩子
                update(node.rc, x1, x2, lineType); // 更新右孩子
            }
            pushUp(node); // 更新父节点的值
        }
    }

    class SegNode {
        int start, end; // 起始点
        SegNode lc, rc; // 左右子树
        int count; // 被查寻区间覆盖次数
        long len; // 区间长度
        Integer[] X; // X实际坐标

        public SegNode(int start, int end, Integer[] X) {
            this.start = start;
            this.end = end;
            this.X = X;
            lc = null;
            rc = null;
            count = 0;
            len = 0;
        }

        public int getMid() {
            return (start + end) >> 1;
        }
    }


    // p851 喧闹和富有「dfs + 剪枝」
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        List<List<Integer>> list = new ArrayList<>();
        int n = quiet.length;
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] item : richer) {
            list.get(item[1]).add(item[0]);
        }
        int[] res = new int[n];
        Arrays.fill(res, -1);
        for (int i = 0; i < n; i++) {
            res[i] = dfs(list, i, quiet, res);
        }
        return res;
    }

    int dfs(List<List<Integer>> root, int index, int[] quiet, int[] ans) {
        if (ans[index] != -1) return ans[index]; // 代表已经遍历过，已有结果
        int res = index;
        for (int richer : root.get(index)) {
            int temp = dfs(root, richer, quiet, ans);
            if (quiet[res] > quiet[temp]) {
                res = temp;
            }
        }
        return res;
    }

    // p859 亲密字符串
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) {
            int[] list = new int[26];
            for (int i = 0; i < s.length(); i++) {
                list[s.charAt(i) - 'a']++;
                if (list[s.charAt(i) - 'a'] > 1) return true;
            }
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1) first = i;
                    else if (second == -1) second = i;
                    else return false;
                }
            }
            return second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first);
        }
    }

    // p869 重新排序得到2的幂「词频统计」
    public boolean reorderedPowerOf2(int n) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 1e9; i <<= 1) {
            set.add(countDigit(i));
        }
        return set.contains(countDigit(n));
    }

    public String countDigit(int n) {
        char[] chars = new char[10];
        while (n > 0) {
            chars[n % 10]++;
            n /= 10;
        }
        return new String(chars);
    }

    // p881 救生艇「二分法」
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0;
        int left = 0, right = people.length - 1;
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            res++;
        }
        return (left == right) ? res + 1 : res;
    }
}