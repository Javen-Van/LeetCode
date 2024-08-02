package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class MinimumRectanglesToCoverPoints {
    public static void main(String[] args) {
        Solution solution = new MinimumRectanglesToCoverPoints().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minRectanglesToCoverPoints(int[][] points, int w) {
            List<Integer> list = new ArrayList<>();
            for (int[] point : points) {
                list.add(point[0]);
            }
            list.sort(Integer::compareTo);
            int res = 0, start = list.get(0);
            for (Integer idx : list) {
                if (idx - start > w) {
                    res++;
                    start = idx;
                }
            }
            return res + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}