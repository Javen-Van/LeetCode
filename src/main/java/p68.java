import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-09-09
 * @Description 文本左右对齐
 */
public class p68 {

    @Test
    public void test() {
        int i = lengthOfLongestSubstring("");
        System.out.println(i);
        System.out.println(simplifyPath("/home//././a"));
    }

    // p3 无重复的最长子串「双指针 + 哈希表 / 动态规划 + 哈希表」
    public int lengthOfLongestSubstring(String s) {
        int l = -1, r = 0, res = 0, n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (; r < n; r++) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                l = Math.max(l, map.get(c));
            }
            res = Math.max(res, r - l);
            map.put(c, r);
        }
        return res;
    }

    // p6 Z字形变换
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int row = 0;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder sb = sbs[row];
            sb.append(s.charAt(i));
            if (flag) row++;
            else row--;
            if (row == numRows - 1) flag = false;
            if (row == 0) flag = true;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : sbs) {
            res.append(sb);
        }
        return res.toString();
    }

    // p10 正则表达式匹配「动态规划」
    public boolean match(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 2; i <= n; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') dp[i + 1][j + 1] = dp[i][j];
                if (p.charAt(j) == '*')
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || (dp[i][j + 1] && (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.'));
            }
        }
        return dp[m][n];
    }

    // p15 三数之和「排序 + 双指针」
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i], l = i + 1, r = nums.length - 1;
            while (l < r) {
                if (l > i + 1 && nums[l] == nums[l - 1]) {
                    l++;
                    continue;
                }
                if (nums[l] + nums[r] == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    l++;
                    r--;
                } else if (nums[l] + nums[r] > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }

    // p18 四数之和「排序 + 双指针」
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int first = 0; first < n; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) continue;
            int sum3 = target - nums[first];
            for (int second = first + 1; second < n; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) continue;
                int sum2 = sum3 - nums[second], third = second + 1, forth = n - 1;
                while (third < forth) {
                    if ((third > second + 1 && nums[third] == nums[third - 1]) || nums[third] + nums[forth] < sum2) {
                        third++;
                        continue;
                    }
                    if (nums[third] + nums[forth] == sum2) {
                        res.add(new ArrayList<>(Arrays.asList(nums[first], nums[second], nums[third], nums[forth])));
                        third++;
                    }
                    forth--;
                }
            }
        }
        return res;
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

    // p44 通配符匹配「动态规划」打表如下：
    //   0 a b c d
    // 0 t f f f f
    // a f t f f f
    // b f f t f f
    // * f f t t t
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') dp[0][i + 1] = true;
            else break;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') dp[i + 1][j + 1] = dp[i][j];
                if (p.charAt(j) == '*') dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j];
            }
        }
        return dp[m][n];
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

    // p71 简化路径
    public String simplifyPath(String path) {
        String[] str = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : str) {
            if ("".equals(s) || ".".equals(s)) continue;
            if ("..".equals(s)) {
                if (!stack.isEmpty()) stack.pop();
            } else stack.push(s);
        }
        return "/" + String.join("/", stack);
    }

}
