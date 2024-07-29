package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class MinimumOperationsToMakeASpecialNumber {
    public static void main(String[] args) {
        Solution solution = new MinimumOperationsToMakeASpecialNumber().new Solution();
        solution.minimumOperations("2908305");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minimumOperations(String num) {
            boolean find0 = false, find5 = false;
            int n = num.length();
            for (int i = 0; i < n; i++) {
                char c = num.charAt(n - i - 1);
                if (c == '0' || c == '5') {
                    if (find0) {
                        return i - 1;
                    }
                    if (c == '0') find0 = true;
                    if (c == '5') find5 = true;
                } else if (c == '2' || c == '7') {
                    if (find5) return i - 1;
                }
            }
            if (find0) return n - 1;
            return n;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}