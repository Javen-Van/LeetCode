package bean;

import java.util.*;

// p911 在线选举
public class TopVotedCandidate {

    int[] find;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.find = new int[times.length];
        this.times = times;
        Map<Integer, Integer> map = new HashMap<>();
        int top = -1;
        map.put(-1, -1);
        for (int i = 0; i < times.length; i++) {
            int cur = persons[i];
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            if (map.get(cur) >= map.get(top)) {
                top = cur;
            }
            find[i] = top;
        }
    }

    public int q(int t) {
        int low = 0, high = this.times.length - 1;
        int median = 0;
        while (low < high) {
            median = (low + high + 1) / 2;
            if (times[median] <= t) {
                low = median;
            } else {
                high = median - 1;
            }
        }
        return this.find[low];
    }
}
