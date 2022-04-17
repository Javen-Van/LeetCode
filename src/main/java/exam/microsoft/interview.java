package exam.microsoft;

public class interview {
    public static boolean method(int[] arr, int k) {
        if (k < 0 || arr == null) return false;
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (arr[j - 1] <= i) {
                    dp[j][i] |= dp[j - 1][i - arr[j - 1]];
                }
                dp[j][i] |= dp[j - 1][i];
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9};
        System.out.println(method(arr, 10));
    }
}
