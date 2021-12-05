import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Javen
 * @create 2021-09-14
 * @Description
 */
public class p524 {
    @Test
    public void test() {
        System.out.println(containsSubstring("apple", "ple"));
    }

    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o1.length() != o2.length()) return o2.length() - o1.length();
            else return o1.compareTo(o2);
        });
        for (String str : dictionary) {
            if (containsSubstring(s, str)) return str;
        }
        return "";
    }

    public boolean containsSubstring(String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) j++;
            i++;
        }
        return j == b.length();
    }

    // p520
    public boolean detectCapitalUse(String word) {
        boolean flag1 = Character.isUpperCase(word.charAt(0));
        char[] chars = word.toCharArray();
        return true;
    }
}
