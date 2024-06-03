package dailyCode.halveArray;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.halveArray(new int[]{3, 8, 20}));
    }

    public int halveArray(int[] nums) {
        double sum = 0, res = 0;
        PriorityQueue<Double> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (double num : nums) {
            queue.offer(num);
            sum += num;
        }
        sum /= 2;
        while (sum > 0 && !queue.isEmpty()) {
            double max = queue.poll();
            max /= 2;
            sum -= max;
            queue.offer(max);
            res++;
        }
        return (int) res;
    }

}
