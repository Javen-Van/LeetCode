package bean;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2022-01-23
 * @Description p2034 股票价格波动
 */
public class StockPrice {

    int maxTime = 0;
    Map<Integer, Integer> map = new HashMap<>();
    Queue<int[]> min = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
    Queue<int[]> max = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);

    public StockPrice() {
    }

    public void update(int timestamp, int price) {
        maxTime = Math.max(timestamp, maxTime);
        map.put(timestamp, price);
        max.offer(new int[]{price, timestamp});
        min.offer(new int[]{price, timestamp});
    }

    public int current() {
        return map.get(maxTime);
    }

    public int maximum() {
        while (true) {
            int[] cur = max.peek();
            if (map.get(cur[1]) == cur[0]) return cur[0];
            max.poll();
        }
    }

    public int minimum() {
        while (true) {
            int[] cur = min.peek();
            if (map.get(cur[1]) == cur[0]) return cur[0];
            min.poll();
        }
    }

    @Test
    public void test() {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(88, 9184);
        stockPrice.update(83, 343);
        stockPrice.update(87, 693);
        System.out.println(stockPrice.map.get(88).equals(stockPrice.max.peek()));
        stockPrice.update(88, 7810);
        System.out.println(stockPrice.maximum());
    }
}
