package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;

public class BaseballGame {
    public static void main(String[] args) {
        Solution solution = new BaseballGame().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int calPoints(String[] operations) {
            int[] stack = new int[operations.length];
            int idx = 0, sum = 0;
            for (String operation : operations) {
                switch (operation) {
                    case "D":
                        stack[idx] = stack[idx - 1] * 2;
                        sum += stack[idx];
                        idx++;
                        break;
                    case "C":
                        idx--;
                        sum -= stack[idx];
                        break;
                    case "+":
                        stack[idx] = stack[idx - 1] + stack[idx - 2];
                        sum += stack[idx];
                        idx++;
                        break;
                    default:
                        stack[idx] = Integer.parseInt(operation);
                        sum += stack[idx];
                        idx++;
                }
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}