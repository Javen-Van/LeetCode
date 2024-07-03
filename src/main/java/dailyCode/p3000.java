package dailyCode;

public class p3000 {

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
