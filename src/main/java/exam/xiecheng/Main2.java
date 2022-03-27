package exam.xiecheng;


import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            System.out.println(method(s));
        }
    }

    public static long method(String s) {
        long one = 0L, zero = 0L;
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                if (s.charAt(i) == '0') one += i + 1;
                else zero += i + 1;
            } else {
                if (s.charAt(i) == '0') zero += i + 1;
                else one += i + 1;
            }
        }
        return Math.min(one, zero);
    }
}
