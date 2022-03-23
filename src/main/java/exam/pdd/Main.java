package exam.pdd;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums1 = new int[n], nums2 = new int[n];
            for (int i = 0; i < n; i++) {
                nums1[i] = sc.nextInt();
            }
            for (int i = 0; i < n; i++) {
                nums2[i] = sc.nextInt();
            }
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            long res = 0;
            for (int i = 0; i < n; i++) {
                res += (long) (nums1[i] - nums2[i]) * (nums1[i] - nums2[i]);
            }
            System.out.println(res);
        }
    }
}
