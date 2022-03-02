public class p1100 {
    // p1154 一年中的第几天
    public int dayOfYear(String date) {
        int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] s = date.split("-");
        int y = Integer.parseInt(s[0]), m = Integer.parseInt(s[1]), d = Integer.parseInt(s[2]);
        // 闰年：是400的倍数或者是4的倍数且不是100的倍数，闰年的二月为29天
        if (y != 1900 && y % 4 == 0) {
            days[2] += 1;
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            res += days[i];
        }
        return res + d;
    }

    // p1189 气球的最大数量
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (int i = 0; i < text.length(); i++) {
            int idx = text.charAt(i) - 'a';
            count[idx]++;
        }
        int b = count[1], a = count[0], l = count['l' - 'a'] / 2, o = count['o' - 'a'] / 2, n = count['n' - 'a'];
        return Math.min(a, Math.min(b, Math.min(l, Math.min(o, n))));
    }
}
