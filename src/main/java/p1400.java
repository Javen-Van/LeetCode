/**
 * @author Javen
 * @create 2021-12-01
 * @Description
 */
public class p1400 {

    // p1446
    public int maxPower(String s) {
        int res = 1, temp = 0;
        char pre = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == pre) {
                temp++;
                if (i == s.length() - 1) res = Math.max(res, temp);
            } else {
                res = Math.max(res, temp);
                pre = s.charAt(i);
                temp = 1;
            }
        }
        return res;
    }
}
