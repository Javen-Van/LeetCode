package leetcode.editor.cn;

public class LexicographicallySmallestStringAfterOperationsWithConstraint {

    public static void main(String[] args) {
        Solution solution = new LexicographicallySmallestStringAfterOperationsWithConstraint().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String getSmallestString(String s, int k) {
            char[] res = new char[s.length()];
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                int dif = Math.min(c - 'a', 'a' - c + 26);
                if (k >= dif) {
                    k -= dif;
                    res[i] = 'a';
                } else {
                    res[i] = (char) (c - k);
                    k = 0;
                }
            }
            return new String(res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}