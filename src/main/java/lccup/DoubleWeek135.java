package lccup;

public class DoubleWeek135 {

    public String losingPlayer(int x, int y) {
        return Math.min(x, y / 4) % 2 == 0 ? "Bob" : "Alice";
    }

    public int minimumLength(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int res = 0;
        for (int num : count) {
            res += num > 2 ? (num % 2 == 0 ? 2 : 1) : num;
        }
        return res;
    }

//    public int minChanges(int[] nums, int k) {
//        int[] count = new int[100001];
//        int n = nums.length, res = 0;
//        for (int i = 0; i < n / 2; i++) {
//            count[Math.abs(nums[i] - nums[n - 1 - i])]++;
//        }
//        int max = 0, maxValue=0;
//        for (int i = 0; i < 100001; i++) {
//            if (count[i]>)
//        }
//    }
}
