package exam.meituan;

import java.util.Scanner;

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(method(nums));
        }
    }

    public static long method(int[] nums) {
        int n = nums.length;
        long res = 0;
        int[] pre = new int[n + 1];
        pre[0] = 1;
        for (int i = 0; i < n; i++) {
            pre[i + 1] = nums[i] * pre[i];
            for (int j = 0; j <= i; j++) {
                if (pre[j] == pre[i + 1]) res++;
            }
        }
        return res;
    }

}
