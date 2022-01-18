package swordToOffer;

/**
 * @author Javen
 * @create 2022-01-18
 * @Description 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 */
public class offer56 {

    // 011 011 011 100
    public int singleNumber(int[] nums) {
        int res = 0;
        int[] count = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                count[i] += num & 1;
                num >>= 1;
            }
        }
        for (int i = 31; i >= 0; i--) {
            res <<= 1;
            res |= count[i] % 3;
        }
        return res;
    }
}
