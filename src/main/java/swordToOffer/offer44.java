package swordToOffer;

import org.junit.Test;

/**
 * @author Javen
 * @create 2022-01-18
 * @Description 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，
 * 第13位是1，第19位是4，等等。
 */
public class offer44 {

    public int findNthDigit(int n) {
        long digit = 1; // 大数越界问题
        int pow = 1; // pow * digit * 9
        while (digit * pow * 9 < n) {
            n -= digit * pow * 9;
            digit *= 10; // 个十百千
            pow++; // 位数
        }
        int div = n / pow, mod = n % pow;
        long num = digit + div + (mod == 0 ? -1 : 0);
        mod = mod == 0 ? 0 : pow - mod;
        while (mod > 0) {
            num /= 10;
            mod--;
        }
        return (int) num % 10;
    }

    @Test
    public void test() {
        System.out.println(findNthDigit(1000000000));
    }
}
