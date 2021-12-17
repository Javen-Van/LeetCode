import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Javen
 * @create 2021-12-17
 * @Description
 */
public class p1600 {
    // p1610 可见点的最大数目「数学 + 双指针 + 滑动窗口」
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int locationX = location.get(0), locationY = location.get(1);
        int samePoints = 0;
        List<Double> theta = new ArrayList<>();
        for (List<Integer> point : points) {
            int x = point.get(0), y = point.get(1);
            if (x == locationX && y == locationY) {
                samePoints++;
                continue;
            }
            theta.add(Math.atan2(y - locationY, x - locationX));
        }
        theta.sort(Comparator.naturalOrder());
        int m = theta.size();
        for (int i = 0; i < m; i++) {
            theta.add(theta.get(i) + 2 * Math.PI); // 循环数组
        }
        int right = 0, res = 0;
        double r = Math.toRadians(angle);
        for (int i = 0; i < m; i++) {
            while (right < theta.size() && theta.get(right) - theta.get(i) <= r) {
                right++;
            }
            res = Math.max(right - i, res);
        }
        return res + samePoints;
    }
}
