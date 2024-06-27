package dailyCode;

import org.junit.Test;

import java.util.*;

public class p2700 {

    /**
     * p2713 矩阵中严格递增的单元格数
     *
     * @param mat
     * @return
     */
    public int maxIncreasingCells(int[][] mat) {
        Map<Integer, List<int[]>> treeMap = new TreeMap<>();
        int m = mat.length, n = mat[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                treeMap.putIfAbsent(mat[i][j], new ArrayList<>());
                treeMap.get(mat[i][j]).add(new int[]{i, j});
            }
        }
        int[] row = new int[m], column = new int[n];
        int res = 0;
        for (List<int[]> value : treeMap.values()) {
            int[] record = new int[value.size()]; // 临时数组，用于记录每个位置当前的dp值
            for (int k = 0; k < value.size(); k++) {
                int i = value.get(k)[0], j = value.get(k)[1];
                record[k] = Math.max(row[i], column[j]) + 1;
                res = Math.max(res, record[k]);
            }
            for (int k = 0; k < value.size(); k++) {
                int i = value.get(k)[0], j = value.get(k)[1];
                row[i] = Math.max(row[i], record[k]); // 更新i行的最大dp值
                column[j] = Math.max(column[j], record[k]); // 更新j行的最大dp值
            }
        }
        return res;
    }

    /**
     * @param nums
     * @return
     */
    public int countBeautifulPairs(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int b = nums[i] % 10;
            for (int j = 0; j < i; j++) {
                int a = nums[j];
                while (a >= 10) {
                    a /= 10;
                }
                if (gcd(a, b) == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * p2732 找到矩阵中的好子集
     *
     * @param grid
     * @return
     */
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int[] record = new int[32];
        Arrays.fill(record, -1);
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            int num = 0, digit = 1;
            for (int j = n - 1; j >= 0; j--) {
                num += grid[i][j] * digit;
                digit *= 2;
            }
            record[num] = i;
        }
        if (record[0] != -1) {
            return Arrays.asList(record[0]);
        }
        for (int i = 0; i < record.length; i++) {
            for (int j = 0; j < i; j++) {
                int a = record[i], b = record[j];
                if (a != -1 && b != -1 && (i & j) == 0) {
                    return a < b ? Arrays.asList(a, b) : Arrays.asList(b, a);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * p2734 执行子串操作后的字典序最小字符串
     * @param s
     * @return
     */
    public String smallestString(String s) {
        int start = -1, end = -1;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (charArray[i] != 'a') {
                if (start == -1) {
                    start = i;
                }
            } else {
                if (start != -1) {
                    end = i;
                    break;
                }
            }
        }
        if (start == -1) {
            charArray[s.length() - 1] = 'z';
        } else {
            end = end == -1 ? s.length() : end;
            for (int i = start; i < end; i++) {
                charArray[i] = (char) (charArray[i] - 1);
            }
        }
        return new String(charArray);
    }

    /**
     * p2741 特别的排列-状态压缩dp
     *
     * @param nums
     * @return
     */
    public int specialPerm(int[] nums) {
        int n = nums.length, MOD = 1000000007, mask = 1 << n, res = 0;
        // dp[state][i]表示选取state集合里的数，且末位为nums[i]的排列数
        int[][] dp = new int[mask][n];
        for (int i = 0; i < n; i++) {
            dp[1 << i][i] = 1;
        }
        for (int state = 0; state < mask; state++) {
            for (int i = 0; i < n; i++) {
                // 判断i是否在state中，即所选取的几个数中是否有nums[i]
                if ((state >> i & 1) == 0) {
                    continue;
                }
                // 去掉其中一个数，并判断能否从上个数中转移过来
                for (int j = 0; j < n; j++) {
                    if (i == j || (state >> j & 1) == 0) {
                        continue;
                    }
                    if (nums[i] % nums[j] != 0 && nums[j] % nums[i] != 0) {
                        continue;
                    }
                    dp[state][i] = (dp[state][i] + dp[state ^ (1 << i)][j]) % MOD;
                }
            }
        }
        // 总和为选取全部的数，即state=2^n-1，遍历所有结尾情况，求和
        for (int i = 0; i < n; i++) {
            res = (res + dp[mask - 1][i]) % MOD;
        }
        return res;
    }

    /**
     * p2786 访问数组中的位置使分数最大-动态规划
     *
     * @param nums
     * @param x
     * @return
     */
    public long maxScore(int[] nums, int x) {
        long[][] dp = new long[nums.length][2];
        long res = nums[0];
        dp[0][nums[0] % 2] = nums[0];
        dp[0][1 - nums[0] % 2] = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int mod = nums[i] % 2;
            long cur = Math.max(dp[i - 1][mod] + nums[i], dp[i - 1][1 - mod] + nums[i] - x);
            res = Math.max(cur, res);
            dp[i][mod] = Math.max(dp[i - 1][mod], cur);
            dp[i][1 - mod] = dp[i - 1][1 - mod];
        }
        return res;
    }

    /**
     * 数组的最大美丽值，解法1「排序」
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximumBeauty(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0, j = 0, res = 0;
        for (; i < nums.length; i++) {
            while (j < nums.length && nums[j] - nums[i] <= 2 * k) {
                j++;
            }
            res = Math.max(res, j - i);
            if (j == nums.length) break;
        }
        return res;
    }

    /**
     * 数组的最大美丽值，解法2「差分数组」
     *
     * @param nums
     * @param k
     * @return
     */
    public int maximumBeauty2(int[] nums, int k) {
        int max = 0, res = 0, count = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] diff = new int[max + 2 * k + 2];
        for (int num : nums) {
            diff[num]++;
            diff[num + 2 * k + 1]--;
        }
        for (int d : diff) {
            count += d;
            res = Math.max(res, count);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {85, 12};
        System.out.println(maxScore(nums, 79));
    }

}
