package dailyCode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class p2900 {

    /**
     * p2970 统计移除递增子数组的数目
     *
     * @param nums
     * @return
     */
    public int incremovableSubarrayCount(int[] nums) {
        int l = 0, n = nums.length, r = n - 1;
        while (l < n - 1 && nums[l] < nums[l + 1]) {
            l++;
        }
        if (l == n) return n * (n + 1) / 2;
        int res = l + 2;
        while (r > 0) {
            if (r < n - 1 && nums[r] >= nums[r + 1]) break;
            while (l >= 0 && nums[l] >= nums[r]) l--;
            res += l + 2;
            r--;
        }
        return res;
    }

    /**
     * p2981 找出出现至少三次的最长特殊子字符串
     *
     * @param s
     * @return
     */
    public int maximumLength(String s) {
        List<Integer>[] group = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            group[i] = new ArrayList<>();
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (i + 1 == s.length() || s.charAt(i) != s.charAt(i + 1)) {
                group[s.charAt(i) - 'a'].add(count);
                count = 0;
            }
        }
        int res = -1;
        for (List<Integer> gp : group) {
            if (gp.isEmpty()) continue;
            gp.sort(Comparator.reverseOrder());
            gp.add(0);
            gp.add(0);
            res = Math.max(res, Math.max(gp.get(0) - 2, Math.max(gp.get(2), Math.min(gp.get(1), gp.get(0) - 1))));
        }
        return res > 0 ? res : -1;
    }

}
