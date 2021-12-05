import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @create 2021-08-21
 * @Description
 */
public class p443 {
    public int compress(char[] chars) {
        int start = 0, count = 0, index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != chars[start]) {
                chars[index] = chars[start];
                index = handle(chars, index + 1, i - start);
                start = i;
            }
            if (i == chars.length - 1) {
                chars[index] = chars[start];
                index = handle(chars, index + 1, i - start + 1);
            }
        }
        System.out.println(chars);
        return index;
    }

    public int handle(char[] c, int index, int num) {
        if (num == 1) return index;
        int count = 0;
        while (num > 0) {
            c[index++] = (char) (num % 10 + '0');
            num /= 10;
            count++;
        }
        int left = index - count, right = index - 1;
        while (left < right) {
            char temp = c[left];
            c[left] = c[right];
            c[right] = temp;
            left++;
            right--;
        }
        return index;
    }

    // p446
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int res = 0;
        Map<Long, Integer>[] maps = new Map[n];
        for (int i = 0; i < n; i++) {
            maps[i] = new HashMap<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                long d = (long) nums[i] - nums[j];
                Integer count = maps[j].getOrDefault(d, 0);
                res += count;
                maps[i].put(d, maps[i].getOrDefault(d, 0) + count + 1);
            }
        }
        return res;
    }

    @Test
    public void te() {
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'd'}));
    }
}
