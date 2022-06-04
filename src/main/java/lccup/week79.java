package lccup;

import java.util.*;

/**
 * @author Javen
 * @create 2022-05-28
 * @Description
 */
public class week79 {

    // Q1
    public boolean digitCount(String num) {
        int[] count = new int[10];
        int n = num.length();
        for (int i = 0; i < n; i++) {
            count[num.charAt(i) - '0']++;
        }
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) - '0' != count[i]) return false;
        }
        return true;
    }

    // Q2
    public String largestWordCount(String[] messages, String[] senders) {
        Map<String, Integer> map = new HashMap<>();
        int n = messages.length, max = 0;
        String res = "";
        for (int i = 0; i < n; i++) {
            int len = messages[i].split(" ").length;
            String name = senders[i];
            int count = map.getOrDefault(name, 0);
            map.put(name, count + len);
        }
        for (Map.Entry<String, Integer> item : map.entrySet()) {
            String k = item.getKey();
            int v = item.getValue();
            if (v == max) res = res.compareTo(k) > 0 ? res : k;
            if (v > max) {
                max = v;
                res = k;
            }
        }
        return res;
    }

    // Q3
    public long maximumImportance(int n, int[][] roads) {
        long res = 0L;
        int[] count = new int[n];
        for (int[] road : roads) {
            count[road[0]]++;
            count[road[1]]++;
        }
        Arrays.sort(count);
        for (int i = 0; i < n; i++) {
            res += (i + 1L) * count[i];
        }
        return res;
    }
}

class BookMyShow {

    int[] left;
    int n;
    int m;
    int start;

    public BookMyShow(int n, int m) {
        this.left = new int[n];
        Arrays.fill(this.left, m);
        this.n = n;
        this.m = m;
        this.start = 0;
    }

    public int[] gather(int k, int maxRow) {
        if (k > m) return new int[0];
        for (int i = start; i <= maxRow; i++) {
            if (left[i] >= k) {
                left[i] -= k;
                return new int[]{i, m - left[i] - k};
            }
        }
        return new int[0];
    }

    public boolean scatter(int k, int maxRow) {
        long sum = 0;
        for (int i = start; i <= maxRow; i++) {
            sum += left[i];
        }
        if (sum < k) return false;
        while (k > 0) {
            int minus = Math.min(left[start], k);
            k -= minus;
            left[start] -= minus;
            if (left[start] == 0) start++;
        }
        while (start < n && left[start] == 0) start++;
        return true;
    }
}
