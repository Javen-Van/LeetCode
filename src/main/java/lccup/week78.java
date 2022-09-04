package lccup;

import java.util.Arrays;

public class week78 {

    // Q1
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int res = 0;
        for (int i = 0; i <= s.length() - k; i++) {
            int x = Integer.parseInt(s.substring(i, i + k));
            if (x != 0 && num % x == 0)
                res++;
        }
        return res;
    }

    // Q2
    public int waysToSplitArray(int[] nums) {
        int n = nums.length, res = 0;
        long pre = 0, sum = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = 0; i < n - 1; i++) {
            pre += nums[i];
            if (pre >= sum - pre)
                res++;
        }
        return res;
    }

    // Q3
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int n = tiles.length;
        int last = 0, count = 0, res = 0;
        for (int[] tile : tiles) {
            int total = tile[0] + carpetLen - 1;
            if (total <= tile[1])
                return carpetLen;
            while (last < n && total > tiles[last][1]) {
                count += tiles[last][1] - tiles[last][0] + 1;
                last++;
            }
            if (last == n)
                return Math.max(count, res);
            count += Math.max(total - tiles[last][0], 0);
            res = Math.max(res, count);
            count -= tile[1] - tile[0] + 1;
        }
        return res;
    }
}
