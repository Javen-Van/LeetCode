package bean;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Javen
 * @create 2022-01-10
 * @Description p384 扰乱数组
 */
public class ShuffleArray {

    int[] nums;
    int[] init;

    public ShuffleArray(int[] nums) {
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
}
