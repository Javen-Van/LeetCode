package exam.morgan;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Javen
 * @create 2022-03-22
 * @Description
 */
public class Interview_Morgan {

    public static void main(String[] args) {
        int[] arr = {3, 1, 1, 3};
        System.out.println(method(arr));
    }

    public static int method(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int temp = n - 1;
            for (int j = 0; j < i; j++) {
                if (j + arr[j] >= i) {
                    temp = Math.min(dp[j] + 1, temp);
                }
            }
            dp[i] = temp;
        }
        return dp[n - 1];
    }

}
