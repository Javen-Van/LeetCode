package lccup;

import org.junit.Test;

import java.util.*;

public class week291 {

    // Q1
    public String removeDigit(String number, char digit) {
        String res = "1";
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) == digit) {
                String n = number.substring(0, i) + number.substring(i + 1);
                if (n.compareTo(res) > 0) res = n;
            }
        }
        return res;
    }

    // Q2
    public int minimumCardPickup(int[] cards) {
        int res = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < cards.length; i++) {
            if (map.containsKey(cards[i])) res = Math.min(res, i - map.get(cards[i]));
            map.put(cards[i], i);
        }
        return res == Integer.MAX_VALUE ? -1 : res + 1;
    }

    // Q3
    public int countDistinct(int[] nums, int k, int p) {
        Set<String> set = new HashSet<>();
        int n = nums.length, res = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                StringBuilder sb = new StringBuilder();
                int count = 0;
                for (int l = 0; l < i; l++) {
                    if (nums[j + l] % p == 0) count++;
                    sb.append(nums[j + l]).append('-');
                }
                if (count <= k && !set.contains(sb.toString())) res++;
                set.add(sb.toString());
            }
        }
        return res;
    }

    // Q4
    public long appealSum(String s) {
        long res = 0, sum = 0;
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            sum += i - pos[index];
            res += sum;
            pos[index] = i;
        }
        return res;
    }
}
