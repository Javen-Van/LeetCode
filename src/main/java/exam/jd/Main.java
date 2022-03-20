package exam.jd;

import java.util.Scanner;

/**
 * @author Javen
 * @create 2022-03-19
 * @Description
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt(); // 坦克数
            int b = sc.nextInt(), c = sc.nextInt(), d = sc.nextInt(); // b点生命，炸c个坦克，共d个碉堡
            System.out.println(method(a, b, c, d));
        }
    }

    public static int method(int a, int b, int c, int d) {
        int total = b * d, res = 0;
        while (a > 0 && d > 0) {
            res++;
            total -= a;
            d = (int) Math.ceil(1.0 * total / b);
            a -= d * c;
        }
        return d <= 0 ? res : -1;
    }
}


