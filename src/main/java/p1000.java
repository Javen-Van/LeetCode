import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @create 2021-12-03
 * @Description
 */
public class p1000 {
    // p1005 K次取反后最大化的数组和, -100 <= A[i] <= 100
    public int largestSumAfterKNegations(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(A).forEach(value -> map.put(value, map.getOrDefault(value, 0) + 1));
        int sum = Arrays.stream(A).sum();
        for (int i = -100; i < 0; i++) {
            if (map.containsKey(i)) {
                int minus = Math.min(K, map.get(i));
                sum += (-i) * minus * 2;
                map.put(-i, map.getOrDefault(-i, 0) + minus);
                K -= minus;
                if (K == 0) break;
            }
        }
        if (K % 2 == 1 && !map.containsKey(0)) {
            for (int i = 1; i < 101; i++) {
                if (map.containsKey(i)) {
                    sum -= 2 * i;
                    break;
                }
            }
        }
        return sum;
    }
}
