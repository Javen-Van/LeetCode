import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-09-01
 * @Description 比较版本号，1.0.0 和 1.0相等，1.01和1.001相等，忽略前导0
 */
public class p165 {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int index = 0;
        while (index < v1.length && index < v2.length) {
            int num1 = Integer.parseInt(v1[index]);
            int num2 = Integer.parseInt(v2[index]);
            if (num1 < num2) return -1;
            if (num1 > num2) return 1;
            index++;
        }
        if (index == v1.length) {
            while (index < v2.length) {
                if (Integer.parseInt(v2[index]) > 0) return -1;
                index++;
            }
        } else if (index == v2.length) {
            while (index < v1.length) {
                if (Integer.parseInt(v1[index]) > 0) return 1;
                index++;
            }
        }
        return 0;
    }

    // p187
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if (n < 10) return new ArrayList<>();
        Set<String> set = new HashSet<>();
        Set<String> res = new HashSet<>();
        for (int i = 10; i < n; i++) {
            String substring = s.substring(i - 10, i);
            if (set.contains(substring)) res.add(substring);
            else set.add(substring);
        }
        return new ArrayList<>(res);
    }
}
