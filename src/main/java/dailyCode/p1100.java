package dailyCode;

import bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class p1100 {

    /**
     * p1103 分糖果2
     * @param candies
     * @param num_people
     * @return
     */
    public int[] distributeCandies(int candies, int num_people) {
        int[] res = new int[num_people];
        int floor = (int) Math.floor((Math.sqrt(1 + 8L * candies) - 1) / 2);
        int n = floor / num_people + 1, mod = floor % num_people, sum = (1 + floor) * floor / 2;
        for (int i = 1; i <= num_people; i++) {
            if (i <= mod) {
                res[i - 1] = (2 * i + (n - 1) * num_people) * n / 2;
            } else if (i == mod + 1) {
                res[i - 1] = (2 * i + num_people * (n - 2)) * (n - 1) / 2 + (candies - sum);
            } else {
                res[i - 1] = (2 * i + num_people * (n - 2)) * (n - 1) / 2;
            }
        }
        return res;
    }

    /**
     * p1154 一年中的第几天
     * @param date
     * @return
     */
    public int dayOfYear(String date) {
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] s = date.split("-");
        int y = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]), d = Integer.parseInt(s[2]);
        // 闰年：是400的倍数或者是4的倍数且不是100的倍数，闰年的二月为29天
        if (y != 1900 && y % 4 == 0) {
            days[2] += 1;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += days[i];
        }
        return res + d;
    }

    public int maxLevelSum(TreeNode root) {
        int max = Integer.MIN_VALUE, res = 0, level = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(), sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (sum > max) {
                res = level;
                max = sum;
            }
            level++;
        }
        return res;
    }

    // p1189 气球的最大数量
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (int i = 0; i < text.length(); i++) {
            int idx = text.charAt(i) - 'a';
            count[idx]++;
        }
        int b = count[1], a = count[0], l = count['l' - 'a'] / 2, o = count['o' - 'a'] / 2, n = count['n' - 'a'];
        return Math.min(a, Math.min(b, Math.min(l, Math.min(o, n))));
    }
}
