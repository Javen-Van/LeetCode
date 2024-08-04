package leetcode.editor.cn;

import java.util.*;

public class MaximumPointsInsideTheSquare {

    public static void main(String[] args) {
        Solution solution = new MaximumPointsInsideTheSquare().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxPointsInsideSquare(int[][] points, String s) {
            List<Point> record = new ArrayList<>();
            for (int i = 0; i < points.length; i++) {
                record.add(new Point(points[i][0], points[i][1], s.charAt(i)));
            }
            Collections.sort(record);
            int len = record.get(0).dis, idx = 0, sum = 0;
            Set<Character> tags = new HashSet<>();
            while (true) {
                int temp = 0;
                while (idx < record.size() && len == record.get(idx).dis) {
                    if (!tags.add(record.get(idx).tag)) return sum;
                    temp++;
                    idx++;
                }
                sum += temp;
                if (idx == record.size()) break;
                len = record.get(idx).dis;
            }
            return sum;
        }

        public class Point implements Comparable<Point> {
            int x, y;
            char tag;
            int dis;

            public Point(int x, int y, char tag) {
                this.x = x;
                this.y = y;
                this.tag = tag;
                this.dis = Math.max(Math.abs(x), Math.abs(y));
            }

            @Override
            public int compareTo(Point o) {
                return this.dis - o.dis;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}