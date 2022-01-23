package lccup;

import java.util.*;

/**
 * @author Javen
 * @create 2022-01-23
 * @Description
 */
public class week277 {

    //Q1
    public int countElements(int[] nums) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        while (l < nums.length - 1 && nums[l] == nums[l + 1]) l++;
        while (r > 0 && nums[r] == nums[r - 1]) r--;
        if (r <= l) return 0;
        return r - l - 1;
    }

    // Q2
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] s = new int[n / 2];
        int[] o = new int[n / 2];
        int idx1 = 0, idx2 = 0;
        for (int num : nums) {
            if (num > 0) {
                s[idx1] = num;
                idx1++;
            } else {
                o[idx2] = num;
                idx2++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                res[i] = s[i / 2];
            } else {
                res[i] = o[i / 2];
            }
        }
        return res;
    }

    // Q3
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int cur = map.getOrDefault(num, 0);
            cur++;
            map.put(num, cur);
        }
        for (int num : nums) {
            if (map.get(num) == 1 && !map.containsKey(num - 1) && !map.containsKey(num + 1)) {
                res.add(num);
            }
        }
        return res;
    }
}
