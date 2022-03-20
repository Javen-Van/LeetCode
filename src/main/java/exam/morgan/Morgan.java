package exam.morgan;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javen
 * @create 2022-03-12
 * @Description
 */
public class Morgan {

    public static List<Integer> getMinimumDifference(List<String> a, List<String> b) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            res.add(method(a.get(i), b.get(i)));
        }
        return res;
    }

    public static int method(String a, String b) {
        int n = a.length(), m = b.length();
        if (n != m) return -1;
        int res = 0;
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[a.charAt(i) - 'a']++;
            count[b.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) res += count[i];
        }
        return res;
    }
}
