package exam.baidu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt(), k = sc.nextInt();
            int[][] map = new int[n*k][n*k];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int x = sc.nextInt();
                    for (int l = 0; l < k; l++) {
                        for (int m = 0; m < k; m++) {
                            map[i * k + l][j * k + m] = x;
                        }
                    }
                }
            }
            for (int i = 0; i < n * k; i++) {
                for (int j = 0; j < n * k; j++) {
                    System.out.print(map[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }
}
