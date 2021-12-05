import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Javen
 * @create 2021-10-04
 * @Description
 */
public class p482 {

    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder(s.replaceAll("-", ""));
        int n = sb.length();
        while (n >= 0) {
            n -= k;
            sb.insert(n, "-");
        }
        return sb.toString().toUpperCase();
    }

    public int thirdMax(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
            if (set.size() > 3) set.remove(set.first());
        }
        return set.size() == 3 ? set.last() : set.first();
    }
}
