package lccup;

import java.util.*;

/**
 * @author Javen
 * @create 2021-09-11
 * @Description
 */
public class solution {

    public int minimumSwitchingTimes(int[][] source, int[][] target) {
        Map<Integer, Integer> mapS = new HashMap<>();
        Map<Integer, Integer> mapT = new HashMap<>();
        for (int[] temp : source) {
            for (int num : temp) {
                Integer i = mapS.getOrDefault(num, 0);
                mapS.put(num, i + 1);
            }
        }
        for (int[] temp : target) {
            for (int num : temp) {
                Integer i = mapT.getOrDefault(num, 0);
                mapT.put(num, i + 1);
            }
        }
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : mapT.entrySet()) {
            Integer key = entry.getKey();
            int dif = entry.getValue() - mapS.getOrDefault(key, 0);
            dif = Math.max(dif, 0);
            res += dif;
        }
        return res;
    }

    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int res = 0, n = cards.length;
        for (int i = n - 1; i >= n - cnt; i--) {
            res += cards[i];
        }
        if (res % 2 == 0) return res;
        if (n == cnt) return 0;
        if (cards[n - cnt] % 2 == 0) {
            for (int i = n - cnt - 1; i >= 0; i--) {
                if (cards[i] % 2 == 1) {
                    return res - cards[n - cnt] + cards[i];
                }
            }
            for (int i = n - cnt + 1; i < n; i++) {
                if (cards[i] % 2 == 1) {
                    return res - cards[i] + cards[n - cnt - 1];
                }
            }
        } else {
            for (int i = n - cnt - 1; i >= 0; i--) {
                if (cards[i] % 2 == 0) {
                    return res - cards[n - cnt] + cards[i];
                }
            }
            for (int i = n - cnt + 1; i < n; i++) {
                if (cards[i] % 2 == 0) {
                    return res - cards[i] + cards[n - cnt - 1];
                }
            }
        }
        return 0;
    }

    public int circleGame(int[][] toys, int[][] circles, int r) {
        int n = toys.length;
        int[] d = new int[n];
        boolean[] isInclude = new boolean[n];
        for (int i = 0; i < n; i++) {
            d[i] = r - toys[i][2];
            isInclude[i] = d[i] < 0;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (isInclude[i]) continue;
            for (int[] circle : circles) {
                if (dis(toys[i][0], toys[i][1], circle[0], circle[1]) <= d[i]) {
                    isInclude[i] = true;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    public double dis(int a, int b, int x, int y) {
        return Math.sqrt(Math.pow((a - x), 2) + Math.pow((b - y), 2));
    }

}
