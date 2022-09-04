package lccup;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-09-12
 * @Description
 */
public class week258 {

    @Test
    public void test() {
        int[][] r = new int[][] { { 1, 3 }, { 2, 6 }, { 3, 9 } };
        System.out.println(interchangeableRectangles(r));
    }

    public long interchangeableRectangles(int[][] rectangles) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] rectangle : rectangles) {
            int gcd = gcd(rectangle[0], rectangle[1]);
            List<Integer> temp = Arrays.asList(rectangle[0] / gcd, rectangle[1] / gcd);
            Integer count = map.getOrDefault(temp, 0);
            res += count;
            map.put(temp, count + 1);
        }
        return res;
    }

    public int gcd(int n1, int n2) {
        int gcd = 0;
        if (n1 < n2) {// 交换n1、n2的值
            n1 = n1 + n2;
            n2 = n1 - n2;
            n1 = n1 - n2;
        }

        if (n1 % n2 == 0) {
            gcd = n2;
        }

        while (n1 % n2 > 0) {
            n1 = n1 % n2;

            if (n1 < n2) {
                n1 = n1 + n2;
                n2 = n1 - n2;
                n1 = n1 - n2;
            }

            if (n1 % n2 == 0) {
                gcd = n2;
            }
        }
        return gcd;
    }
}
