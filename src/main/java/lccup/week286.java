package lccup;

import java.util.*;

/**
 * @author Javen
 * @create 2022-03-27
 * @Description
 */
public class week286 {

    // Q1
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        Set<Integer> res1 = new HashSet<>(), res2 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        for (int i : nums2) {
            if (!set1.contains(i))
                res2.add(i);
            set2.add(i);
        }
        for (int i : nums1) {
            if (!set2.contains(i))
                res1.add(i);
        }
        res.add(new ArrayList<>(res1));
        res.add(new ArrayList<>(res2));
        return res;
    }

    // Q2
    public int minDeletion(int[] nums) {
        int n = nums.length, res = 0, index = 0, i = index + 1;
        while (index < n) {
            while (i < n && nums[index] == nums[i]) {
                res++;
                i++;
            }
            if (i == n) {
                res++;
                break;
            }
            index = i + 1;
            i = index + 1;
        }
        return res;
    }

    // Q3
    public long[] kthPalindrome(int[] queries, int intLength) {
        long[] res = new long[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = query(queries[i], intLength);
        }
        return res;
    }

    public long query(int k, int len) {
        StringBuilder sb = new StringBuilder();
        int digit = len / 2 - (len % 2 == 0 ? 1 : 0);
        if (k > 9 * Math.pow(10, digit))
            return -1;
        sb.append((int) Math.pow(10, digit) - 1 + k);
        StringBuilder temp = new StringBuilder(sb.substring(0, len / 2));
        sb.append(temp.reverse());
        return Long.parseLong(sb.toString());
    }

    // Q4
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();
        int[][] dp = new int[n + 1][k + 1];
        int[][] pre = new int[n][];
        for (int i = 0; i < n; i++) {
            List<Integer> pile = piles.get(i);
            int[] temp = new int[pile.size() + 1];
            for (int j = 1; j < temp.length; j++) {
                temp[j] = temp[j - 1] + pile.get(j - 1);
            }
            pre[i] = temp;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int l = 0; l <= j; l++) {
                    if (l < pre[i - 1].length)
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - l] + pre[i - 1][l]);
                    else
                        break;
                }
            }
        }
        return dp[n][k];
    }

}
