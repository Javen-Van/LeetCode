import org.junit.Test;

/**
 * @author Javen
 * @create 2021-08-07
 * @Description
 */
public class p200 {

    // p202 快乐数「快慢指针」
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);
        return slow == 1;
    }

    int getNext(int n) {
        int res = 0;
        while (n > 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }

    // p233 数字1的个数
    public int countDigitOne(int n) {
        int[] dp = new int[n + 1];
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 10 == 1) dp[i] = dp[i / 10] + 1;
            else dp[i] = dp[i / 10];
            res += dp[i];
        }
        return res;
    }

    public int count(int n) {
        int res = 0;
        while (n > 0) {
            if (n % 10 == 1) res++;
            n /= 10;
        }
        return res;
    }

    // p264 丑数II
    public int nthUglyNumber(int n) {
        if (n <= 6) return n;
        int[] dp = new int[n];
        int[] dp2 = new int[n];
        int[] dp3 = new int[n];
        int[] dp5 = new int[n];
        for (int i = 0; i < 6; i++) {
            dp[i] = i + 1;
            dp2[i] = (i + 1) * 2;
            dp3[i] = (i + 1) * 3;
            dp5[i] = (i + 1) * 5;
        }
        for (int i = 6; i < n; i++) {
            int target = dp[i - 1];
            dp[i] = Math.min(binarySearch(dp2, i - 1, target), Math.min(binarySearch(dp3, i - 1, target), binarySearch(dp5, i - 1, target)));
            dp2[i] = 2 * dp[i];
            dp3[i] = 3 * dp[i];
            dp5[i] = 5 * dp[i];
        }
        return dp[n - 1];
    }

    int binarySearch(int[] nums, int end, int target) {
        int low = 0, high = end;
        int medium;
        while (low < high) {
            medium = low + (high - low) / 2;
            if (nums[medium] <= target) low = medium + 1;
            else high = medium;
        }
        return nums[low];
    }
}
