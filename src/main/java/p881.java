import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Javen
 * @create 2021-08-26
 * @Description 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。
 */
public class p881 {
    @Test
    public void test() {
        int[] p = new int[]{3, 2, 2, 1};
        System.out.println(numRescueBoats(p, 3));
    }

    //p859
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) return false;
        if (s.equals(goal)) {
            int[] list = new int[26];
            for (int i = 0; i < s.length(); i++) {
                list[s.charAt(i) - 'a']++;
                if (list[s.charAt(i) - 'a'] > 1) return true;
            }
            return false;
        } else {
            int first = -1, second = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != goal.charAt(i)) {
                    if (first == -1) first = i;
                    else if (second == -1) second = i;
                    else return false;
                }
            }
            return second != -1 && s.charAt(first) == goal.charAt(second) && s.charAt(second) == goal.charAt(first);
        }
    }

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int res = 0;
        int left = 0, right = people.length - 1;
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            res++;
        }
        return (left == right) ? res + 1 : res;
    }

    // p869
    public boolean reorderedPowerOf2(int n) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i <= 1e9; i <<= 1) {
            set.add(countDigit(i));
        }
        return set.contains(countDigit(n));
    }

    public String countDigit(int n) {
        char[] chars = new char[10];
        while (n > 0) {
            chars[n % 10]++;
            n /= 10;
        }
        return new String(chars);
    }
}
