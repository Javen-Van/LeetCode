package exam.microsoft;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class interview2 {

    public static List<String> method(int n) {
        List<String> res = new ArrayList<>();
        if (n < 0) return res;
        int mod = n % 3;
        if (mod != 0) {
            res.add("A " + mod);
        }
        int round = n / 3;
        for (int i = 0; i < round; i++) {
            Random rn = new Random();
            int bStick = rn.nextInt(2) + 1, aStick = 3 - bStick;
            res.add("B " + bStick);
            res.add("A " + aStick);
        }
        res.add("A wins");
        return res;
    }

    public static void main(String[] args) {
        int[] test = {0, 9, 22, 35, 100, 200, 256};
        for (int i : test) {
            System.out.println(method(i));
        }
    }
}
