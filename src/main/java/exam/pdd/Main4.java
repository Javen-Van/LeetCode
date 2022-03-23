package exam.pdd;

import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int T = sc.nextInt();
            for (int i = 0; i < T; i++) {
                int n = sc.nextInt(), k = sc.nextInt();
                String s = sc.next();
                if (n % k != 0) {
                    System.out.println(-1);
                    continue;
                }

            }
        }
    }
}
