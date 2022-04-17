package lccup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class week76 {

    // Q1
    public int findClosestNumber(int[] nums) {
        int res = Integer.MIN_VALUE, dif = Integer.MAX_VALUE;
        for (int num : nums) {
            int minus = Math.abs(num);
            if (minus < dif) {
                dif = minus;
                res = num;
            } else if (minus == dif) {
                res = Math.max(res, num);
            }
        }
        return res;
    }

    // Q2
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long res = 0;
        int count1 = total / cost1;
        for (int i = 0; i <= count1; i++) {
            int left = total - i * cost1;
            res += left / cost2 + 1;
        }
        return res;
    }

    // Q3

    // Q4
    List<List<Integer>> map = new ArrayList<>();
    int[] scores;
    int res = -1, count1 = -1, count2 = -1;
    int[][] dp;

    public int maximumScore(int[] scores, int[][] edges) {
        this.scores = scores;
        int n = scores.length;
        dp = new int[n][4];
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        for (int i = 0; i < n; i++) {
            boolean[] isVis = new boolean[n];
            isVis[i] = true;
            count1 = -1;
            count2 = -1;
            dfs(i, scores[i], isVis, 1);
            dp[i][1] = scores[i];
            dp[i][2] = count1;
            dp[i][3] = count2;
        }
        return res;
    }

    public void dfs(int cur, int sum, boolean[] isVis, int times) {
        if (times == 3) count1 = Math.max(count1, sum);
        if (times == 2) count2 = Math.max(count2, sum);
        if (times == 4) {
            res = Math.max(res, sum);
            return;
        }
        for (int next : map.get(cur)) {
            if (!isVis[next]) {
                if (dp[next][4 - times] != 0) {
                    res = Math.max(res, sum + dp[next][4 - times]);
                    continue;
                }
                isVis[next] = true;
                dfs(next, sum + scores[next], isVis, times + 1);
                isVis[next] = false;
            }
        }
    }

    @Test
    public void test() {
        int[] scores = new int[]{9, 10, 6, 4, 11, 12};
        int[][] edges = new int[][]{{0, 3}, {5, 3}, {2, 4}, {1, 3}};
        System.out.println(maximumScore(scores, edges));
    }
}

class ATM {

    long[] count;
    int[] money;

    public ATM() {
        count = new long[5];
        money = new int[]{20, 50, 100, 200, 500};
    }

    public void deposit(int[] banknotesCount) {
        for (int i = 0; i < 5; i++) {
            count[i] += banknotesCount[i];
        }
    }

    public int[] withdraw(int amount) {
        int[] res = new int[5];
        long[] temp = new long[5];
        System.arraycopy(count, 0, temp, 0, 5);
        for (int i = 4; i >= 0; i--) {
            if (amount >= money[i] && count[i] > 0) {
                int cost = amount / money[i];
                res[i] = (int) Math.min(cost, count[i]);
                amount -= res[i] * money[i];
                temp[i] -= res[i];
            }
            if (amount == 0) break;
        }
        if (amount == 0) {
            System.arraycopy(temp, 0, count, 0, 5);
            return res;
        } else {
            return new int[]{-1};
        }
    }
}