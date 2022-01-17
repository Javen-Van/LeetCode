import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @create 2022-01-17
 * @Description
 */
public class p1200 {

    // p1220 统计元音字母序列的数目「动态规划」
    public int countVowelPermutation(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        int A, E, I, O, U;
        int MOD = 1000000007;
        while (n > 1) {
            A = ((e + i) % MOD + u) % MOD;
            E = (a + i) % MOD;
            I = (e + o) % MOD;
            O = i % MOD;
            U = (i + o) % MOD;
            a = A;
            e = E;
            i = I;
            o = O;
            u = U;
            n--;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int integer : map.keySet()) {

        }

        return ((((a + e) % MOD + i) % MOD + o) % MOD + u) % MOD;
    }
}
