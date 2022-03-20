package exam.jd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author Javen
 * @create 2022-03-19
 * @Description 小明在玩一款建造类的游戏。他需要为一段未开荒的地段设计路段的规划，以便起重机通过。
 * <p>
 * 游戏里每段路径都有能承重的级别，小明现在希望尽可能让能承重更大的起重机通过，这样他就可以比较快地完成建造了。
 * <p>
 * 游戏规定小明只能选一种起重机机型，小明想知道这个起重机最高的承重级别应该是多少，使得在该承重条件下，起重机可以从任何一个点出发去向任何一个点而不会损坏道路（损坏道路指的是路段上行驶了超过承重能力的起重机）。
 * <p>
 * 为了方便，我们将需要规划的建造点抽象成N个点，有M条边将他们相连。
 */
public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), m = sc.nextInt();
            int[][] dp = new int[n][n];
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt() - 1, b = sc.nextInt() - 1, c = sc.nextInt();
                dp[a - 1][b - 1] = c;
                dp[b - 1][a - 1] = c;
            }
            System.out.println(method(n, dp));
        }
    }

    public static int method(int n, int[][] dp) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (k != i && k != j && dp[i][k] != 0 && dp[k][j] != 0) {
                        dp[i][j] = Math.max(dp[i][j], Math.min(dp[i][k], dp[k][j]));
                    }
                }
                dp[j][i] = dp[i][j];
                res = Math.min(res, dp[i][j]);
            }
        }
        return res;
    }

}
