package dailyCode;

public class p3110 {

    public int maximumPrimeDifference(int[] nums) {
        int start = -1, end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                if (start == -1) {
                    start = i;
                }
                end = i;
            }
        }
        return end - start;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
