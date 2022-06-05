package dailyCode;

import org.junit.Test;

/**
 * @author Javen
 * @create 2021-08-07
 * @Description
 */
public class p5193 {

    public String makeFancyString(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (i==n-1 || c != s.charAt(i + 1)) {
                sb.append(c);
            } else {
                sb.append(c);
                sb.append(c);
                while (i < n-1 && s.charAt(i+1) == c) i++;
            }
        }
        return sb.toString();
    }

}
