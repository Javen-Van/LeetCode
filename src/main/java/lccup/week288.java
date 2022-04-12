package lccup;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-04-10
 * @Description
 */
public class week288 {

    // Q1
    public int largestInteger(int num) {
        Queue<Integer> even = new PriorityQueue<>(), odd = new PriorityQueue<>();
        Set<Integer> isOdd = new HashSet<>();
        int digit = 0;
        while (num != 0) {
            int x = num % 10;
            if ((x & 1) == 1) {
                odd.offer(x);
                isOdd.add(digit);
            } else even.offer(x);
            num /= 10;
            digit++;
        }
        int res = 0, d = 1;
        for (int i = 0; i < digit; i++) {
            if (isOdd.contains(i)) {
                res += d * odd.poll();
            } else {
                res += d * even.poll();
            }
            d *= 10;
        }
        return res;
    }

    // Q2
    public String minimizeResult(String expression) {
        String[] strings = expression.split("\\+");
        String num1 = strings[0], num2 = strings[1], res = expression;
        int min = Integer.parseInt(num1) + Integer.parseInt(num2);
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 1; j <= num2.length(); j++) {
                String a = num1.substring(0, i), b = num1.substring(i), c = num2.substring(0, j), d = num2.substring(j);
                int cal = Integer.parseInt("".equals(a) ? "1" : a) * (Integer.parseInt("".equals(b) ? "1" : b) + Integer.parseInt("".equals(c) ? "1" : c)) * Integer.parseInt("".equals(d) ? "1" : d);
                if (cal <= min) {
                    min = cal;
                    res = a + "(" + b + "+" + c + ")" + d;
                }
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {9, 7, 8};
        System.out.println(maximumProduct(nums, 9));
    }

    // Q3
    public int maximumProduct(int[] nums, int k) {
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }
        for (int i = 0; i < k; i++) {
            queue.offer(queue.poll() + 1);
        }
        long res = 1L;
        while (!queue.isEmpty()) {
            res = (res * queue.poll()) % 1000000007;
        }
        return (int) res;
    }


    // Q4
}
