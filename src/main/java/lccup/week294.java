package lccup;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Javen
 * @create 2022-05-22
 * @Description
 */
public class week294 {

    // Q1
    public int percentageLetter(String s, char letter) {
        int count = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == letter)
                count++;
        }
        return count * 100 / n;
    }

    // Q2
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length, res = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            capacity[i] -= rocks[i];
            if (capacity[i] == 0)
                res++;
            else
                queue.offer(capacity[i]);
        }
        while (!queue.isEmpty() && additionalRocks > 0) {
            int num = queue.poll();
            if (additionalRocks >= num)
                res++;
            additionalRocks -= num;
        }
        return res;
    }

    // Q3
    public int minimumLines(int[][] stockPrices) {
        int n = stockPrices.length, res = 1;
        if (n == 1)
            return 0;
        Arrays.sort(stockPrices, Comparator.comparingInt(o -> o[0]));
        int[] k = commonDivisor(stockPrices[1][1] - stockPrices[0][1], stockPrices[1][0] - stockPrices[0][0]);
        for (int i = 2; i < n; i++) {
            int[] cur = commonDivisor(stockPrices[i][1] - stockPrices[i - 1][1],
                    stockPrices[i][0] - stockPrices[i - 1][0]);
            if (cur[0] != k[0] || cur[1] != k[1]) {
                res++;
                k[0] = cur[0];
                k[1] = cur[1];
            }
        }
        return res;
    }

    public int[] commonDivisor(int a, int b) {
        int x = gcd(a, b);
        return new int[] { a / x, b / x };
    }

    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Q4
    public int totalStrength(int[] strength) {
        int n = strength.length, MOD = 1000000007, min = strength[0];
        long res = 0, pre = 0;
        for (int i = 1; i <= n; i++) {
            if (strength[i - 1] < min) {
                res = pre / min;
                min = strength[i - 1];
                res *= min;
            }
            res = (res + (long) min * i * strength[i - 1]) % MOD;
            pre = res;
        }
        return (int) res;
    }

}
