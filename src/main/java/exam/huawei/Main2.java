package exam.huawei;


import java.util.*;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String msg = sc.next();
            int n = sc.nextInt();
            int[] tags = new int[n];
            for (int i = 0; i < n; i++) {
                tags[i] = sc.nextInt();
            }
            int[][] res = method(tags, msg);
            for (int i = 0; i < n; i++) {
                System.out.println(res[i][0] + " " + res[i][1]);
            }
            System.out.println(Integer.parseInt("10",16));
        }
    }

    public static int[][] method(int[] tags, String msg) {
        Map<Integer, int[]> record = new HashMap<>();
        int n = msg.length();
        for (int i = 0; i < n;) {
            if (i + 4 > n) break;
            int tag = Integer.parseInt(msg.substring(i, i + 2), 16);
            int len = Integer.parseInt(msg.substring(i + 2, i + 4), 16);
            if (i + 4 + 2 * len > n) break;
            record.put(tag, new int[]{len, (i + 4) / 2});
            i += (4 + 2 * len);
        }
        int m = tags.length, size = record.size();
        int[][] res = new int[m][2];
        if (size == 0) return res;
        for (int i = 0; i < m; i++) {
            int tag = tags[i];
            if (!record.containsKey(tag)) continue;
            res[i] = record.get(tag);
        }
        return res;
    }
}
