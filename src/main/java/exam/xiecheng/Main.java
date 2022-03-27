package exam.xiecheng;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long n = sc.nextLong();
            System.out.println(method(n));
        }
    }

    public static long method(long n) {
        long a = n, digit = 1;
        while (digit <= n) {
            for (int i = 0; i < 10; i++) {
                if ((n + 9 * a + i) % 7 == 0) return n + 9 * a + i;
            }
            digit *= 10;
            a = n / digit * digit;
        }
        return n;
    }
}
