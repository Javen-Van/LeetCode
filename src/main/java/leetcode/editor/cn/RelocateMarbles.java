package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RelocateMarbles {

    public static void main(String[] args) {
        Solution solution = new RelocateMarbles().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num);
            }
            for (int i = 0; i < moveFrom.length; i++) {
                set.remove(moveFrom[i]);
                set.add(moveTo[i]);
            }
            List<Integer> res = new ArrayList<>(set);
            res.sort(Integer::compareTo);
            return res;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)

}