package lccup;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-03-19
 * @Description
 */
public class week74 {

    // Q1
    public boolean divideArray(int[] nums) {
        int[] count = new int[501];
        for (int num : nums) {
            count[num]++;
        }
        for (int i : count) {
            if ((i & 1) == 1) return false;
        }
        return true;
    }

    // Q2
    public long maximumSubsequenceCount(String text, String pattern) {
        Deque<Integer> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == pattern.charAt(0)) q1.offer(i);
            if (c == pattern.charAt(1)) q2.offer(i);
        }
        long res = Math.max(q1.size(), q2.size());
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peekFirst() < q2.peekFirst()) {
                res += q2.size();
                q1.pollFirst();
            } else q2.pollFirst();
        }
        return res;
    }

    // Q3
    public int halveArray(int[] nums) {
        int n = nums.length;
        if (n == 1) return 1;
        Queue<Double> queue = new PriorityQueue<>((o1, o2) -> Double.compare(o2, o1));
        double sum = 0;
        int res = 0;
        for (int num : nums) {
            queue.offer((double) num);
            sum += num / 2.0;
        }
        while (sum > 0) {
            Double head = queue.poll();
            sum -= head / 2;
            queue.offer(head / 2);
            res++;
        }
        return res;
    }

    // Q4
    public int minimumWhiteTiles(String floor, int numCarpets, int carpetLen) {
        int n = floor.length();
        int[][] dp = new int[n + 1][numCarpets + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= numCarpets; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + floor.charAt(i - 1) - '0',
                        j > 0 ? i - carpetLen < 0 ? 0 : dp[i - carpetLen][j - 1] : Integer.MAX_VALUE);
            }
        }
        return dp[n][numCarpets];
    }

    @Test
    public void test() {
        String s = "001111110000000011111111010011110100001111000000000111111110000110000000000000001010111111111011110011111111000000010011110000111101111000111101111000111101100111100111100001111001111010000000000000000000100000100000000111100000000001100011111111011111000011111100001000000001111001111111111111011110100001111111101111101111000011110011110001111001111011111111000111100111111111111111100001111000";
        System.out.println(minimumWhiteTiles(s, 68, 4));
    }
}
