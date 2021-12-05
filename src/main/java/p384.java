import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * @author Javen
 * @create 2021-08-07
 * @Description
 */
public class p384 {
    int[] nums;
    int[] init;

    public p384(int[] nums) {
        this.nums = nums;
        this.init = Arrays.copyOf(nums, nums.length);
    }

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return init;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        for (int i = 0; i < nums.length; i++) {
            swap(i, randRange(i, nums.length));
        }
        return nums;
    }

    public void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //p397
    public int integerReplacement(int n) {
        int res = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                res++;
                n /= 2;
            } else if (n % 4 == 1) {
                res += 2;
                n /= 2;
            } else {
                if (n == 3) {
                    res = 2;
                    n = 1;
                } else {
                    res += 2;
                    n = n / 2 + 1;
                }
            }
        }
        return res;
    }
}
