package leetcode.editor.cn;

public class RightTriangles {

    public static void main(String[] args) {
        Solution solution = new RightTriangles().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public long numberOfRightTriangles(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            int[] countX = new int[m], countY = new int[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        countX[i]++;
                        countY[j]++;
                    }
                }
            }
            long res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        res += (long) (countX[i] - 1) * (countY[j] - 1);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}