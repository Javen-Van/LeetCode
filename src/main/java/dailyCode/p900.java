package dailyCode;

import java.util.*;

public class p900 {

    // p905 按奇偶排序数组「双指针」
    public int[] sortArrayByParity(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        return nums;
    }

    // p908 最小差值
    public int smallestRangeI(int[] nums, int k) {
        int min = 100000, max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return Math.max(max - min - 2 * k, 0);
    }

    // p917 仅仅反转字母｛双指针｝
    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < arr.length && !Character.isLetter(arr[i]))
                i++;
            while (j >= 0 && !Character.isLetter(arr[j]))
                j--;
            if (i == arr.length || j == -1)
                break;
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

    // p942 增减字符串匹配「贪心」
    public int[] diStringMatch(String s) {
        int n = s.length(), low = 0, high = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            res[i] = s.charAt(i) == 'I' ? low++ : high--;
        }
        res[n] = low;
        return res;
    }

    // p954 二倍数对数组
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        int n = arr.length, index = -1;
        while (index < n - 1 && arr[index + 1] < 0)
            index++;
        for (int i = index, j = index - 1; i >= 0; i--) {
            if (set.contains(i))
                continue;
            while (j >= 0 && arr[j] > 2 * arr[i])
                j--;
            if (j == -1 || arr[j] != 2 * arr[i])
                return false;
            set.add(j--);
        }
        set.clear();
        for (int i = index + 1, j = i + 1; i < n; i++) {
            if (set.contains(i))
                continue;
            while (j < n && arr[j] < 2 * arr[i])
                j++;
            if (j == n || arr[j] != 2 * arr[i])
                return false;
            set.add(j++);
        }
        return true;
    }
}
