package dailyCode;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

/**
 * You are given a string s that consists of lowercase English letters.
 * <p>
 * A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
 * <p>
 * Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 */
public class P2981_MaximumLength {

    public int maximumLength(String s) {
        List<Integer>[] group = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            group[i] = new ArrayList<>();
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (i + 1 == s.length() || s.charAt(i) != s.charAt(i + 1)) {
                group[s.charAt(i) - 'a'].add(count);
                count = 0;
            }
        }
        int res = -1;
        for (List<Integer> gp : group) {
            if (gp.isEmpty()) continue;
            gp.sort(Comparator.reverseOrder());
            gp.add(0);
            gp.add(0);
            res = Math.max(res, Math.max(gp.get(0) - 2, Math.max(gp.get(2), Math.min(gp.get(1), gp.get(0) - 1))));
        }
        return res > 0 ? res : -1;
    }

}
