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
}
