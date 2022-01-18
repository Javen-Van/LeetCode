package swordToOffer;

/**
 * @author Javen
 * @create 2022-01-18
 * @Description 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 */
public class offer65 {

    public int add(int a, int b) {
        int res = a ^ b, carry = (a & b) << 1;
        while (carry != 0) {
            int temp = res;
            res ^= carry;
            carry = (temp & carry) << 1;
        }
        return res;
    }
}
