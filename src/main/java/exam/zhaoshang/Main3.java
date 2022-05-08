package exam.zhaoshang;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] target = new int[]{sc.nextInt(), sc.nextInt()};
            int[][] order = new int[n][2];
            for (int i = 0; i < n; i++) {
                order[i] = new int[]{sc.nextInt(), sc.nextInt()};
            }
        }
    }
}
