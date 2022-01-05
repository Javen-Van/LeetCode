package swordToOffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author Javen
 * @create 2021-12-25
 * @Description n个骰子的点数：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 */
public class offer60 {

    @Test
    public void test() {
        double[] doubles = dicesProbability(2);
        System.out.println(Arrays.toString(doubles));
    }

    // 动态规划 + 条件概率
    public double[] dicesProbability(int n) {
        double[][] dp = new double[n + 1][]; // 仅用到上一轮dp结果，可以做空间优化，用一维数组代替二维数组
        dp[0] = new double[]{1.0};
        for (int i = 1; i <= n; i++) {
            int len = 5 * i + 1;
            dp[i] = new double[len];
            for (int j = 0; j < dp[i - 1].length; j++) {
                for (int k = 0; k < 6; k++) {
                    dp[i][j + k] += dp[i - 1][j] / 6;
                }
            }
        }
        return dp[n];
    }
}
