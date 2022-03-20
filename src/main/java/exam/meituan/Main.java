package exam.meituan;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            for (int num : nums) {
                System.out.println(method(num));
            }
        }
    }

    public static String method(int num) {
        if (num % 11 == 0) return "yes";
        int count = 0;
        while (num != 0) {
            count += num % 10 == 1 ? 1 : 0;
            num /= 10;
        }
        return count > 1 ? "yes" : "no";
    }
}
