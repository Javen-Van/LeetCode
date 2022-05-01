package dailyCode;

import bean.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-01-17
 * @Description
 */
public class p1200 {

    // p1220 统计元音字母序列的数目「动态规划」
    public int countVowelPermutation(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        int A, E, I, O, U;
        int MOD = 1000000007;
        while (n > 1) {
            A = ((e + i) % MOD + u) % MOD;
            E = (a + i) % MOD;
            I = (e + o) % MOD;
            O = i % MOD;
            U = (i + o) % MOD;
            a = A;
            e = E;
            i = I;
            o = O;
            u = U;
            n--;
        }
        return ((((a + e) % MOD + i) % MOD + o) % MOD + u) % MOD;
    }

    // p1305 两棵二叉搜索树中的所有元素
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>(), res = new ArrayList<>();
        dfs(root1, list1);
        dfs(root2, list2);
        int m = list1.size(), n = list2.size(), i = 0, j = 0;
        while (i < m && j < n) {
            if (list1.get(i) < list2.get(j)) {
                res.add(list1.get(i));
                i++;
            } else {
                res.add(list2.get(j));
                j++;
            }
        }
        while (i < m) res.add(list1.get(i++));
        while (j < n) res.add(list2.get(j++));
        return res;
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }

    // p1345 跳跃游戏IV「BFS」
    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[n];
        int res = 0;
        queue.offer(0);
        visit[0] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == n - 1) return res;
                if (map.containsKey(arr[cur])) {
                    for (int next : map.get(arr[cur])) {
                        if (!visit[next]) {
                            if (next == n - 1) return res + 1;
                            queue.offer(next);
                            visit[next] = true;
                        }
                    }
                    map.remove(arr[cur]); // 防止重复遍历，再遍历过之后删除
                }
                if (!visit[cur + 1]) {
                    queue.offer(cur + 1);
                    visit[cur + 1] = true;
                }
                if (cur > 0 && !visit[cur - 1]) {
                    queue.offer(cur - 1);
                    visit[cur - 1] = true;
                }
            }
            res++;
        }
        return -1;
    }

    @Test
    public void test() {
        int i = minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404});
        System.out.println(i);
    }
}
