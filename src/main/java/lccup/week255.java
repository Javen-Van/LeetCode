package lccup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javen
 * @create 2021-08-22
 * @Description
 */
public class week255 {
    // 第一题
    public int findGCD(int[] nums) {
        int max = 0, min = 1001;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return gcd(max, min);
    }

    int gcd(int a, int b) {
        if (a % b == 0) return b;
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    // 第二题
    // 给你一个字符串数组 nums ，该数组由 n 个 互不相同 的二进制字符串组成，且每个字符串长度都是 n 。
    // 请你找出并返回一个长度为 n 且 没有出现 在 nums 中的二进制字符串。如果存在多种答案，只需返回 任意一个 即可。
    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>(Arrays.asList(nums));
        char[] c = nums[0].toCharArray();
        for (int i = 0; i < nums.length; i++) {
            char temp = c[i];
            if (temp == '0') c[i] = '1';
            else c[i] = '0';
            if (!set.contains(new String(c))) return new String(c);
            c[i] = temp;
        }
        return null;
    }

    // 第三题
    // 给你一个大小为 m x n 的整数矩阵 mat 和一个整数 target 。
    //返回 最小的绝对差 。
    //a 和 b 两数字的 绝对差 是 a - b 的绝对值。
    public int minimizeTheDifference(int[][] mat, int target) {
        return 0;
    }
}
