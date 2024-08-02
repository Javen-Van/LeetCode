package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoubleModularExponentiation {
    public static void main(String[] args) {
        Solution solution = new DoubleModularExponentiation().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getGoodIndices(int[][] variables, int target) {
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < variables.length; i++) {
                int a = variables[i][0], b = variables[i][1], c = variables[i][2], m = variables[i][3];
                if (pow(pow(a, b, 10), c, m) == target) {
                    res.add(i);
                }
            }
            return res;
        }

        private int pow(int x, int n, int mod) {
            int res = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    res = res * x % mod;
                }
                x = x * x % mod;
                n >>= 1;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}