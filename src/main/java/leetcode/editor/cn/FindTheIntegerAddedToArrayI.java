package leetcode.editor.cn;

public class FindTheIntegerAddedToArrayI {

    public static void main(String[] args) {
        Solution solution = new FindTheIntegerAddedToArrayI().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int addedInteger(int[] nums1, int[] nums2) {
            int min1 = nums1[0], min2 = nums2[0];
            for (int i = 0; i < nums1.length; i++) {
                min1 = Math.min(min1, nums1[i]);
                min2 = Math.min(min2, nums2[i]);
            }
            return min2 - min1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}