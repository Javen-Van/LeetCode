package exam.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Javen
 * @create 2022-03-22
 * @Description
 */
public class Main2 {

    public static int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] str = reader.readLine().split(" ");
        int n = Integer.parseInt(str[0]), m = Integer.parseInt(str[1]), k = Integer.parseInt(str[2]), s = Integer.parseInt(str[3]) - 1;
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = MAX_VALUE;
            }
        }
        for (int i = 0; i < m; i++) {
            String[] temp = reader.readLine().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]), w = Integer.parseInt(temp[2]);
            map[u - 1][v - 1] = Math.min(map[u - 1][v - 1], w);
        }
        for (int i = 0; i < k; i++) {
            String[] temp = reader.readLine().split(" ");
            int u = Integer.parseInt(temp[0]), v = Integer.parseInt(temp[1]), w = Integer.parseInt(temp[2]);
            map[u - 1][v - 1] = Math.min(map[u - 1][v - 1], w);
            map[v - 1][u - 1] = Math.min(map[v - 1][u - 1], w);
        }
        String[] str2 = reader.readLine().split(" ");
        int a = Integer.parseInt(str2[0]), b = Integer.parseInt(str2[1]), q = Integer.parseInt(str2[2]);
        int[] d = new int[q];
        String[] str3 = reader.readLine().split(" ");
        for (int i = 0; i < q; i++) {
            d[i] = Integer.parseInt(str3[i]) - 1;
        }
        int time = 0, from = s;
        Map<Integer, int[]> record = new HashMap<>();
        for (int x : d) {
            int[] dist = record.getOrDefault(from, dijkstra(map, from));
            record.put(from, dist);
            time += dist[x];
            if ((time & 1) == 1) time += a;
            else time += b;
            from = x;
        }
        int[] last = record.getOrDefault(d[q - 1], dijkstra(map, d[q - 1]));
        time += last[s];
        System.out.println(time);
    }

    public static int[] dijkstra(int[][] map, int src) {
        int n = map.length;
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = MAX_VALUE;
        }
        dist[src] = 0;
        boolean[] isVis = new boolean[n];
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] != o2[1]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        queue.offer(new int[]{src, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int k = cur[0], v = cur[1];
            if (isVis[k]) continue;
            isVis[k] = true;
            for (int i = 0; i < n; i++) {
                if (!isVis[i] && map[k][i] != MAX_VALUE && dist[i] > v + map[k][i]) {
                    dist[i] = v + map[k][i];
                    queue.offer(new int[]{i, dist[i]});
                }
            }
        }
        return dist;
    }
}
