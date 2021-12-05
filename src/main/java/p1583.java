/**
 * @author Javen
 * @create 2021-08-14
 * @Description
 */
public class p1583 {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int[] pair = new int[n];
        int[][] preference = new int[n][n];
        int res = 0;
        for (int[] p : pairs) {
            pair[p[0]] = p[1];
            pair[p[1]] = p[0];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                preference[i][preferences[i][j]] = j + 1;
            }
        }
        // 遍历pair数组，第i个朋友
        for (int i = 0; i < n; i++) {
            for (int temp : preferences[i]) {
                if (preference[temp][i] < preference[temp][pair[temp]] && preference[i][temp] < preference[i][pair[i]]) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}
