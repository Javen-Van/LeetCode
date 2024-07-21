package dailyCode;

public class p3000 {

    /**
     * p3096 得到更多分数的最少关卡数目
     *
     * @param possible
     * @return
     */
    public int minimumLevels(int[] possible) {
        int sum = 0, preFix = 0;
        for (int score : possible) {
            sum += score == 1 ? 1 : -1;
        }
        for (int i = 0; i < possible.length - 1; i++) {
            preFix += possible[i] == 1 ? 1 : -1;
            if (preFix > sum - preFix) return i + 1;
        }
        return -1;
    }

    /**
     * p3099 哈沙德数
     *
     * @param x
     * @return
     */
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0, temp = x;
        while (temp != 0) {
            sum += temp % 10;
            temp /= 10;
        }
        return x % sum == 0 ? sum : -1;
    }
}
