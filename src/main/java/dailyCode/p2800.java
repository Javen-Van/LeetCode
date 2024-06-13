package dailyCode;

import org.junit.Test;

import java.util.*;

public class p2800 {

    /**
     * p2806 取整购买后的账户余额
     *
     * @param purchaseAmount
     * @return
     */
    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int div = purchaseAmount / 10;
        return 100 - 10 * (div + (purchaseAmount % 10 < 5 ? 0 : 1));
    }

    /**
     * p2813 子序列最大优雅度
     *
     * @param items
     * @param k
     * @return
     */
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (o1, o2) -> o2[0] - o1[0]);
        Set<Integer> visited = new HashSet<>();
        // 记录重复item的profit
        Deque<Integer> stack = new LinkedList<>();
        long sum = 0L, res = 0L;
        for (int i = 0; i < items.length; i++) {
            int profit = items[i][0], category = items[i][1];
            if (i < k) {
                // 把最大的k个都先选上
                sum += profit;
                // 如果是重复的item，则入栈
                if (visited.contains(category)) {
                    stack.push(profit);
                }
                visited.add(category);
            } else if (!stack.isEmpty() && !visited.contains(category)) {
                // 对于后续n-k个，只有非重复的才考虑
                // 由于已经排序过，栈顶元素即为重复的最小元素，需要剔除
                sum = sum - stack.pop() + profit;
                visited.add(category);
            }
            res = Math.max(res, sum + (long) visited.size() * visited.size());
        }
        return res;
    }

}
