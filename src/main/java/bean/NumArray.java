package bean;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Javen
 * @create 2022-03-13
 * @Description 区间和检索「线段树」
 */
class NumArray {

    int[] tree;
    int n;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            n = nums.length;
            tree = new int[n << 1];
            buildTree(nums);
        }
    }

    private void buildTree(int[] nums) {
        for (int i = n, j = 0; i < 2 * n; i++, j++)
            tree[i] = nums[j];
        for (int i = n - 1; i > 0; --i)
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }

    public void update(int index, int val) {
        int pos = index + n;
        val -= tree[pos];
        while (pos > 0) {
            tree[pos] += val;
            pos /= 2;
        }
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        left += n;
        right += n;
        while (left <= right) {
            if ((left & 1) == 1) {
                sum += tree[left];
                left++;
            }
            if ((right & 1) == 0) {
                sum += tree[right];
                right--;
            }
            left /= 2;
            right /= 2;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 7, 8, 9};
        NumArray array = new NumArray(nums);
        System.out.println(Arrays.toString(array.tree));
    }
}
