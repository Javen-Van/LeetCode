package lccup;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class week407 {

    public int minChanges(int n, int k) {
        int res = 0;
        while (n != 0 || k != 0) {
            int a = n & 1, b = k & 1;
            if (a == 0 && b == 1) return -1;
            if (a == 1 && b == 0) res++;
            n >>= 1;
            k >>= 1;
        }
        return res;
    }

    public boolean doesAliceWin(String s) {
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                count++;
            }
        }
        return count != 0;
    }

    public int maxOperations(String s) {
        int count = 0, res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    res += count;
                }
                count++;
            }
        }
        if (s.charAt(s.length() - 1) == '0') res += count;
        return res;
    }

    public long minimumOperations(int[] nums, int[] target) {
        long res = 0L;
        int n = nums.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = target[i] - nums[i];
        }
        int max = Math.abs(diff[0]);
        if (n == 1) return max;
        for (int i = 1; i < n; i++) {
            if ((diff[i - 1] > 0 && diff[i] < 0) || (diff[i - 1] < 0 && diff[i] > 0)) {
                res += max;
                max = Math.abs(diff[i]);
            } else {
                max = Math.max(max, Math.abs(diff[i]));
            }
        }
        return res + max;
    }

    @Test
    public void test() {
        int[] nums = {9,2,6,10,4,8,3,4,2,3}, tar = {9,5,5,1,7,9,8,7,6,5};
        minimumOperations(nums, tar);
    }
}
