package lccup;

import bean.TreeNode;
import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-03-06
 * @Description
 */
public class week283 {

    // Q1
    public List<String> cellsInRange(String s) {
        String[] split = s.split(":");
        char c1 = split[0].charAt(0), r1 = split[0].charAt(1), c2 = split[1].charAt(0), r2 = split[1].charAt(1);
        List<String> res = new ArrayList<>();
        for (char i = c1; i <= c2; i++) {
            for (char j = r1; j <= r2; j++) {
                res.add(i + "" + j);
            }
        }
        return res;
    }

    // Q2
    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long res = 0L;
        int pre = 0;
        for (int num : nums) {
            if (num - pre > 1) {
                int n = Math.min(num - pre - 1, k);
                res += (2L * pre + n + 1) * n / 2;
                k -= n;
                if (k == 0) break;
            }
            pre = num;
        }
        if (k > 0) {
            res += (2L * pre + k + 1) * k / 2;
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(gca(4, 6));
        int[] arr = {31, 97561, 97561, 97561, 97561, 97561, 97561, 97561, 97561};
        System.out.println(replaceNonCoprimes(arr));
    }

    // Q3
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int[] description : descriptions) {
            int parent = description[0], child = description[1], isLeft = description[2];
            TreeNode pTree = map.getOrDefault(parent, new TreeNode(parent));
            TreeNode cTree = map.getOrDefault(child, new TreeNode(child));
            if (isLeft == 1) {
                pTree.left = cTree;
            } else {
                pTree.right = cTree;
            }
            map.put(parent, pTree);
            map.put(child, cTree);
            set.add(parent);
        }
        for (int[] description : descriptions) {
            set.remove(description[1]);
        }
        for (int root : set) {
            return map.get(root);
        }
        return null;
    }

    // Q4
    public List<Integer> replaceNonCoprimes(int[] nums) {
        Deque<Integer> stack = new LinkedList<>();
        for (int num : nums) {
            while (!stack.isEmpty()) {
                int gca = gca(num, stack.peek());
                if (gca > 1) {
                    int pre = stack.pop();
                    num = num / gca * pre;
                } else break;
            }
            stack.push(num);
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) {
            res.add(stack.pollLast());
        }
        return res;
    }

    public int gca(int a, int b) {
        return b == 0 ? a : gca(b, a % b);
    }
}
