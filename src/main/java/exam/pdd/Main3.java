package exam.pdd;


import java.util.Arrays;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int k = sc.nextInt();
            String b = sc.next();
            char[] chars = b.toCharArray();
            int n = b.length();
            char[] res = new char[n];
            Arrays.fill(res, '.');
            for (int i = 0; i < n; i++) {
                if (chars[i] == '0') {
                    if (i >= k) res[i - k] = '0';
                    if (i + k < n) res[i + k] = '0';
                }
            }
            for (int i = 0; i < k; i++) {
                if (chars[i] == '1') res[i + k] = '1';
                if (chars[n - i - 1] == '1') res[n - i - 1 - k] = '1';
            }
            for (int i = k; i < n - k; i++) {
                if (chars[i] == '1') {
                    if (res[i + k] == '1' || res[i - k] == '1') continue;
                    if (res[i + k] != '0') {
                        res[i + k] = '1';
                        res[i - k] = '0';
                    } else {
                        res[i - k] = '1';
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (res[i] == '.') res[i] = '0';
            }
            System.out.println(new String(res));
        }
    }
}
