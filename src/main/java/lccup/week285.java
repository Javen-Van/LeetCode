package lccup;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Javen
 * @create 2022-03-20
 * @Description
 */
public class week285 {

    // Q1
    public int countHillValley(int[] nums) {
        int res = 0, n = nums.length;
        for (int i = 1; i < n - 1; i++) {
            int l = i - 1, r = i + 1;
            while (l >= 0 && nums[i] == nums[l]) l--;
            while (r < n && nums[i] == nums[r]) r++;
            if (l < 0 || r >= n || nums[i] == nums[i - 1]) continue;
            if (nums[l] > nums[i] && nums[r] > nums[i]) res++;
            if (nums[l] < nums[i] && nums[r] < nums[i]) res++;
        }
        return res;
    }

    // Q2
    public int countCollisions(String directions) {
        int res = 0;
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < directions.length(); i++) {
            char c = directions.charAt(i);
            if (!stack.isEmpty()) {
                if (stack.peekLast() == 'R' && c == 'L') {
                    res += 2;
                    stack.pollLast();
                    while (!stack.isEmpty() && stack.peekLast() == 'R') {
                        res++;
                        stack.pollLast();
                    }
                    stack.offerLast('S');
                    continue;
                }
                if (stack.peekLast() == 'R' && c == 'S') {
                    while (!stack.isEmpty() && stack.peekLast() == 'R') {
                        res++;
                        stack.pollLast();
                    }
                    stack.offerLast('S');
                    continue;
                }
                if (stack.peekLast() == 'S' && c == 'L') {
                    res++;
                    stack.offerLast('S');
                    continue;
                }
            }
            stack.offerLast(c);
        }
        return res;
    }

    @Test
    public void test() {
        String s = "SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR";
        System.out.println(countCollisions(s));
    }

    // Q3
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        int[] bobArrows = new int[12];
        int max = 0, mask = 1 << 12, left = 0;
        for (int i = 0; i < mask; i++) {
            int n = numArrows, score = 0;
            int[] temp = new int[12];
            for (int j = 0; j < 12; j++) {
                if (((i >> j) & 1) == 1) {
                    score += j;
                    n -= (aliceArrows[j] + 1);
                    temp[j] = aliceArrows[j] + 1;
                }
            }
            if (n >= 0 && score > max) {
                max = score;
                System.arraycopy(temp, 0, bobArrows, 0, 12);
                left = n;
            }
        }
        if (left > 0) {
            bobArrows[0] += left;
        }
        return bobArrows;
    }

    // Q4
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = queryIndices.length, len = s.length();
        int[] res = new int[n];
        int[] dp = new int[len];
        dp[0] = 1;
        char[] str = s.toCharArray();
//        for (int i = 1; i < len; i++) {
//            dp[i] = str[i] == str[i - 1] ? dp[i - 1] + 1 : 1;
//            max = Math.max(dp[i], max);
//        }
        for (int i = 0; i < n; i++) {
            int index = queryIndices[i];
            char change = queryCharacters.charAt(i);
            str[index] = change;
            int max = 1;
            for (int j = 1; j < len; j++) {
                dp[j] = str[j] == str[j - 1] ? dp[j - 1] + 1 : 1;
                max = Math.max(dp[j], max);
            }
//            if (index > 0 && str[index] == str[index - 1]) {
//                dp[index] = dp[index - 1] + 1;
//                max = Math.max(dp[index], max);
//            } else
//            for (int j = index + 1; j < len; j++) {
//                if (str[j] == str[j - 1]) {
//                    dp[j] = dp[j - 1] + 1;
//                    max = Math.max(dp[j], max);
//                } else break;
//            }
            res[i] = max;
        }
        return res;
    }
}
