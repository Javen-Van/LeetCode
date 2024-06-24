package dailyCode;

import org.junit.Test;

public class p2600 {

    /**
     * p2663 字典序最小的美丽字符串
     *
     * @param s
     * @param k
     * @return
     */
    public String smallestBeautifulString(String s, int k) {
        int n = s.length(), index = -1;
        char[] sCharArray = s.toCharArray();
        outer:
        for (int i = n - 1; i >= 0; i--) {
            char c = sCharArray[i], temp = c;
            while (++temp < 'a' + k) {
                boolean flag = true;
                if (i >= 1) {
                    flag &= temp != sCharArray[i - 1];
                }
                if (i >= 2) {
                    flag &= temp != sCharArray[i - 2];
                }
                if (flag) {
                    sCharArray[i] = temp;
                    index = i;
                    break outer;
                }
            }
        }
        if (index != -1) {
            for (int i = index + 1; i < n; i++) {
                char temp = 'a';
                while (temp < 'a' + k) {
                    boolean flag = true;
                    if (i >= 1) {
                        flag &= temp != sCharArray[i - 1];
                    }
                    if (i >= 2) {
                        flag &= temp != sCharArray[i - 2];
                    }
                    if (flag) break;
                    temp++;
                }
                sCharArray[i] = temp;
            }
            return new String(sCharArray);
        }
        return "";
    }

    @Test
    public void test() {
        smallestBeautifulString("abcz", 26);
    }
}
