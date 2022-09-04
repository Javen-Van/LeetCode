package lccup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javen
 * @create 2022-05-29
 * @Description
 */
public class week295 {

    // Q1
    public int rearrangeCharacters(String s, String target) {
        int[] count = new int[26], t = new int[26];
        for (int i = 0; i < target.length(); i++) {
            t[target.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int res = 200;
        for (int i = 0; i < 26; i++) {
            if (t[i] != 0)
                res = Math.min(res, count[i] / t[i]);
        }
        return res;
    }

    // Q2
    public String discountPrices(String sentence, int discount) {
        StringBuilder sb = new StringBuilder();
        String[] s = sentence.split(" ");
        for (String str : s) {
            sb.append(discount(str, discount));
            sb.append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String discount(String word, int discount) {
        if (word.startsWith("$") && word.length() > 1) {
            for (int i = 1; i < word.length(); i++) {
                if (!Character.isDigit(word.charAt(i)))
                    return word;
            }
            double res = Long.parseLong(word.substring(1)) * (100.00 - discount) / 100;
            return "$" + String.format("%.2f", res);
        }
        return word;
    }

    // Q3
    public int totalSteps(int[] nums) {
        int res = 0, pre = nums[0];
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= pre) {
                pre = nums[i];
                list.add(i);
            }
        }
        for (int i = 1; i < list.size(); i++) {
            res = Math.max(res, lengthOfLIS(nums, list.get(i - 1) + 1, list.get(i)));
        }
        return res;
    }

    public int lengthOfLIS(int[] nums, int left, int right) {
        int len = 1, n = right - left;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[left];
        for (int i = left + 1; i < left + n; ++i) {
            if (nums[i] >= d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

}
