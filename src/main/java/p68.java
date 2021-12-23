import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Javen
 * @create 2021-09-09
 * @Description 文本左右对齐
 */
public class p68 {

    @Test
    public void test() {
        int i = strStr("leetcode", "etco");
        System.out.println(i);
    }

    // p28 实现strStr()「KMP算法」
    public int strStr(String haystack, String needle) {
        int n = haystack.length(), m = needle.length();
        int[] next = new int[m]; // next[i]表示字符串s[0:i]的最长的相等的真前缀和真后缀的长度
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) next[i] = ++j;
        }
        for (int i = 0, point = 0; i < n; i++) {
            while (point > 0 && haystack.charAt(i) != needle.charAt(point)) {
                point = next[point - 1];
            }
            if (haystack.charAt(i) == needle.charAt(point)) point++;
            if (point == m) return i - m + 1;
        }
        return -1;
    }

    // p66 加一
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                for (int j = i + 1; j < n; j++) {
                    digits[j] = 0;
                }
                digits[i] += 1;
                return digits;
            }
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }

    // p68 文本左右对齐
    public List<String> fullJustify(String[] words, int maxWidth) {
        int n = words.length;
        int[] len = new int[n];
        for (int i = 0; i < n; i++) {
            len[i] = words[i].length();
        }
        int count = 0, num = 0;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if ((count + len[i] + num) <= maxWidth) {
                count += len[i];
                num++;
            } else {
                String assemble;
                if (num == 1) {
                    assemble = assembleLeft(words[i - 1], maxWidth - count);
                } else {
                    int mod = (maxWidth - count) % (num - 1);
                    int space = (maxWidth - count) / (num - 1);
                    assemble = assemble(words, num, i - num, mod, space);
                }
                res.add(assemble);
                count = len[i];
                num = 1;
            }
        }
        String last = assemble(words, num, n - num, 0, 1);
        res.add(assembleLeft(last, maxWidth - last.length()));
        return res;
    }

    public String assembleLeft(String str, int s) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        while (s > 0) {
            sb.append(' ');
            s--;
        }
        return sb.toString();
    }

    public String assemble(String[] words, int num, int start, int mod, int s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder space = new StringBuilder();
        for (int i = 0; i < s; i++) {
            space.append(' ');
        }
        for (int i = 0; i < num; i++) {
            sb.append(words[start + i]);
            if (i != num - 1) sb.append(space);
            if (i < mod) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

}
