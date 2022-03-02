import java.util.Arrays;

public class p1800 {

    // p1816 截断句子
    public String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && --k == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    // p2029 石子游戏IX
    // a赢：b拿的石子后总价值为3x
    // b赢：a拿了石子后总价值为3x，或当前没有剩余石子
    // 5 5 1 2 4 3 -> 2 2 1 2 1 -> 0 2 3
    // 1 1 2 2 a
    // b: 1 -> 1 0, 2 -> 2 0
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        if (count[0] % 2 == 0) return count[1] >= 1 && count[2] >= 1;
        return Math.abs(count[1] - count[2]) > 2;
    }

    // p2047 句子中的有效单词数
    public int countValidWords(String sentence) {
        int res = 0;
        String[] s = sentence.split(" ");
        for (String str : s) {
            if ("".equals(str)) continue;
            if (isValid(str)) res++;
        }
        return res;
    }

    public boolean isValid(String s) {
        boolean hasConnect = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) return false;
            if (c == '-') {
                if (hasConnect || i == 0 || i == s.length() - 1 || !Character.isLetter(s.charAt(i - 1)) || !Character.isLetter(s.charAt(i + 1)))
                    return false;
                hasConnect = true;
            } else if (c == ',' || c == '.' || c == '!') {
                if (i != s.length() - 1) return false;
            }
        }
        return true;
    }
}
