import bean.TreeNode;

import java.util.*;

/**
 * @author Javen
 * @create 2021-12-17
 * @Description
 */
public class p1600 {

    // p1609 奇偶树「BFS」
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sort = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    int cur = node.val;
                    if (level % 2 == 0) {
                        if (cur % 2 != 1 || cur <= sort) return false;
                    } else {
                        if (cur % 2 != 0 || cur >= sort) return false;
                    }
                    sort = cur;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return true;
    }

    // p1610 可见点的最大数目「数学 + 双指针 + 滑动窗口」
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int locationX = location.get(0), locationY = location.get(1);
        int samePoints = 0;
        List<Double> theta = new ArrayList<>();
        for (List<Integer> point : points) {
            int x = point.get(0), y = point.get(1);
            if (x == locationX && y == locationY) {
                samePoints++;
                continue;
            }
            theta.add(Math.atan2(y - locationY, x - locationX));
        }
        theta.sort(Comparator.naturalOrder());
        int m = theta.size();
        for (int i = 0; i < m; i++) {
            theta.add(theta.get(i) + 2 * Math.PI); // 循环数组
        }
        int right = 0, res = 0;
        double r = Math.toRadians(angle);
        for (int i = 0; i < m; i++) {
            while (right < theta.size() && theta.get(right) - theta.get(i) <= r) {
                right++;
            }
            res = Math.max(right - i, res);
        }
        return res + samePoints;
    }

    // p1636 按照频率将数组升序排序，-100 <= nums[i] <= 100
    public int[] frequencySort(int[] nums) {
        int n = nums.length;
        int[] bucket = new int[201];
        for (int num : nums) {
            bucket[num + 100]++;
        }
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) return o2[0] - o1[0];
            return o1[1] - o2[1];
        });
        for (int i = 0; i < 201; i++) {
            if (bucket[i] != 0) {
                queue.offer(new int[]{i - 100, bucket[i]});
            }
        }
        int[] res = new int[n];
        int index = 0;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            for (int i = 0; i < poll[1]; i++) {
                res[index] = poll[0];
                index++;
            }
        }
        return res;
    }
}
