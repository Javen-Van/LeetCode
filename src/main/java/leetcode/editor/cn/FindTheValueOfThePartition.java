package leetcode.editor.cn;

import java.util.Arrays;

public class FindTheValueOfThePartition {
    public static void main(String[] args) {
        Solution solution = new FindTheValueOfThePartition().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findValueOfPartition(int[] nums) {
            int min = Integer.MAX_VALUE;
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                min = Math.min(min, nums[i] - nums[i - 1]);
            }
            return min;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}