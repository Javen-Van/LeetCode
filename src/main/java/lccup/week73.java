package lccup;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author Javen
 * @create 2022-03-05
 * @Description 第73场双周赛
 */
public class week73 {

    // Q1
    public int mostFrequent(int[] nums, int key) {
        int n = nums.length, max = 0, res = nums[0];
        int[] count = new int[1001];
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == key) {
                count[nums[i + 1]]++;
                if (count[nums[i + 1]] > max) {
                    max = count[nums[i + 1]];
                    res = nums[i + 1];
                }
            }
        }
        return res;
    }

    // Q2
    public int[] sortJumbled(int[] mapping, int[] nums) {
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0])
                return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        for (int i = 0; i < nums.length; i++) {
            queue.offer(new int[] { map(mapping, nums[i]), i });
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[queue.poll()[1]];
        }
        return res;
    }

    public int map(int[] mapping, int num) {
        if (num == 0)
            return mapping[0];
        int res = 0, digit = 1;
        while (num != 0) {
            res += mapping[num % 10] * digit;
            digit *= 10;
            num /= 10;
        }
        return res;
    }

    @Test
    public void test() {
        int[][] edges = { { 0, 3 }, { 0, 4 }, { 1, 3 }, { 2, 4 }, { 2, 7 }, { 3, 5 }, { 3, 6 }, { 3, 7 }, { 4, 6 } };
        System.out.println(getAncestors(8, edges));

    }

    // Q3 BFS
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> map = new ArrayList<>();
        List<Set<Integer>> res = new ArrayList<>();
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
            res.add(new TreeSet<>());
        }
        for (int[] edge : edges) {
            List<Integer> list = map.get(edge[0]);
            list.add(edge[1]);
            count[edge[1]]++;
        }
        for (int i = 0; i < n; i++) {
            if (count[i] == 0) {
                dfs(map, res, i, -1, new boolean[n]);
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Set<Integer> re : res) {
            ans.add(new ArrayList<>(re));
        }
        return ans;
    }

    public void dfs(List<List<Integer>> map, List<Set<Integer>> res, int cur, int pre, boolean[] isVis) {
        if (pre != -1) {
            Set<Integer> temp = res.get(cur);
            temp.add(pre);
            temp.addAll(res.get(pre));
        }
        if (map.get(cur).size() == 0)
            return;
        for (int next : map.get(cur)) {
            if (!isVis[next]) {
                isVis[next] = true;
                dfs(map, res, next, cur, isVis);
            }
        }
    }

    // Q4
    public static void main(String[] args) throws IOException {
        System.out.println((char) ('a' + 1));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < 3; i++) {
            writer.write(Boolean.TRUE.toString());
            writer.newLine();
        }
        writer.flush();
    }
}
