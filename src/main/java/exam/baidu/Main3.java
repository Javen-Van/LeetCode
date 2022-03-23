package exam.baidu;

import java.util.Scanner;


public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.next();
            int n = s.length();
            int s1, t1, s2, t2;
            int index1 = find(s, s.charAt(0));
            int temp = 1;
            while (temp < n && s.charAt(temp) == s.charAt(temp - 1)) temp++;
            if (temp == n) {
                s1 = 1;
                t1 = n - 1;
                s2 = 2;
                t2 = n;
            } else {
                int index2 = find(s, s.charAt(temp));
                if (index2 - temp > index1) {
                    s1 = temp + 1;
                    t1 = index2;
                    s2 = temp + 2;
                    t2 = index2 + 1;
                } else {
                    s1 = 1;
                    t1 = index1;
                    s2 = 2;
                    t2 = index1 + 1;
                }
            }
            System.out.println(s1 + " " + t1 + " " + s2 + " " + t2);
        }
    }

    public static int find(String s, char c) {
        for (int i = s.length() - 1; i > 0; i--) {
            if (s.charAt(i) == c) return i;
        }
        return 0;
    }
}
