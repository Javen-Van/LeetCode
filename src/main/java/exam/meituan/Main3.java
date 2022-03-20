package exam.meituan;

/**
 * @author Javen
 * @create 2022-03-12
 * @Description
 */

import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), m = sc.nextInt();
            int[][] point = new int[n][2];
            for (int i = 0; i < n; i++) {
                point[i][0] = sc.nextInt();
                point[i][1] = sc.nextInt();
            }
            System.out.println(method(point, m));
        }
    }

    public static int method(int[][] point, int m) {
        int n = point.length;
        int mask = 1 << n, res = 0;
        for (int i = 0; i < mask; i++) {
            boolean[] isPoint = new boolean[m];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (((i >> j) & 1) == 1) {
                    int x = point[j][0] - 1, y = point[j][1] - 1;
                    if (!isPoint[x] && !isPoint[y]) {
                        count++;
                        isPoint[x] = true;
                        isPoint[y] = true;
                    } else break;
                }
            }
            res = Math.max(res, count);
        }
        return res;
    }

}
