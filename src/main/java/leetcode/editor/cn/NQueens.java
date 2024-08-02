package leetcode.editor.cn;

import java.util.*;

public class NQueens {
    public static void main(String[] args) {
        Solution solution = new NQueens().new Solution();
        solution.solveNQueens(4);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            Set<Integer> column = new HashSet<>();
            Set<Integer> diagonal1 = new HashSet<>(), diagonal2 = new HashSet<>();
            int[] record = new int[n];
            List<List<String>> res = new ArrayList<>();
            dfs(res, column, diagonal1, diagonal2, 0, n, record);
            return res;
        }

        private void dfs(List<List<String>> res, Set<Integer> column, Set<Integer> diagonal1, Set<Integer> diagonal2, int idx, int n, int[] record) {
            if (idx == n) {
                res.add(generateRes(record, n));
                return;
            }
            for (int i = 0; i < n; i++) {
                if (column.contains(i)) continue;
                int d1 = i - idx, d2 = i + idx;
                if (diagonal1.contains(d1)) continue;
                if (diagonal2.contains(d2)) continue;
                column.add(i);
                diagonal1.add(d1);
                diagonal2.add(d2);
                record[idx] = i;
                dfs(res, column, diagonal1, diagonal2, idx + 1, n, record);
                column.remove(i);
                diagonal1.remove(d1);
                diagonal2.remove(d2);
            }
        }

        private List<String> generateRes(int[] record, int n) {
            List<String> res = new ArrayList<>();
            char[] arr = new char[n];
            Arrays.fill(arr, '.');
            for (int i = 0; i < n; i++) {
                arr[record[i]] = 'Q';
                res.add(new String(arr));
                arr[record[i]] = '.';
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}