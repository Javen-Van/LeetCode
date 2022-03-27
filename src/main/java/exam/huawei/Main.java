package exam.huawei;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] steps = new int[n];
            for (int i = 0; i < n; i++) {
                steps[i] = sc.nextInt();
            }
            int k = sc.nextInt();
            System.out.println(method(steps, k));
        }
    }

    public static int method(int[] steps, int k) {
        int n = steps.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (j + steps[j] >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[n - 1] > k ? -1 : dp[n - 1];
    }
}
