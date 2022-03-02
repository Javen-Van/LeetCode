import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @create 2021-08-14
 * @Description
 */
public class p1500 {

    // p1542 找出最长的超赞子字符串「状态压缩」
    public int longestAwesome(String s) {
        int n = s.length();
        int cur = 0, res = 1;
        Map<Integer, Integer> map = new HashMap<>(); // 用于存储状态key对应的最长子字符串的起始索引
        map.put(cur, -1);
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            cur ^= (1 << num); // 当前状态
            if (map.containsKey(cur)) res = Math.max(res, i - map.get(cur)); // 出现次数全是偶数
            else map.put(cur, i);
            for (int j = 0; j < 10; j++) { // 对cur中的每一位。依次改变后，仅有1位不同，抑或之后出现次数为奇数，都是一个超赞子字符串
                int state = cur ^ (1 << j);
                if (map.containsKey(state)) {
                    res = Math.max(res, i - map.get(state));
                }
            }
        }
        return res;
    }

    // p1583 统计不开心的朋友
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
