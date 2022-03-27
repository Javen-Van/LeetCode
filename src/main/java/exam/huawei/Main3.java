package exam.huawei;


import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int m = sc.nextInt(), n = sc.nextInt(), x = sc.nextInt();
            String[][] list = new String[m][];
            for (int i = 0; i < m; i++) {
                list[i] = sc.next().split(" ");
            }
            String[] interview = new String[n];
            for (int i = 0; i < n; i++) {
                interview[i] = sc.next();
            }

            System.out.println(false);
        }
    }

}
