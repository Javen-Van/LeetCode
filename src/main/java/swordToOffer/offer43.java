package swordToOffer;

/**
 * @author Javen
 * @create 2022-01-17
 * @Description 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 */
public class offer43 {

    // 按位计算，每个位上1出现的次数，分成当前位的数cur，高位的十进制high，低位的十进制low
    // 如果当前位 = 0，则当前位1的个数 = high * digit
    // 如果当前位 = 1，则当前位1的个数 = high * digit + low + 1
    // 否则，当前位1的个数 = (high + 1) * digit
    // 当high和cur同时为0时，跳出循环，时间复杂度log10N
    public int countDigitOne(int n) {
        int res = 0, low = 0, high = n / 10, digit = 1, cur = n % 10;
        while (high != 0 || cur != 0) {
            if (cur == 0) res += high * digit;
            else if (cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
            low = n % digit;
        }
        return res;
    }
}
