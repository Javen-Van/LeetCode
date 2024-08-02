package lccup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class week408 {

    public boolean canAliceWin(int[] nums) {
        int sum1 = 0, sum2 = 0, total = 0;
        for (int num : nums) {
            if (num / 10 == 0) sum1 += num;
            else if (num / 100 == 0) sum2 += num;
            total += num;
        }
        return sum1 > total - sum1 || sum2 > total - sum2;
    }

    public int nonSpecialCount(int l, int r) {
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < Math.sqrt(1000000000); i++) {
            if (isSqrt(i)) list.add(i * i);
        }
        int idx1 = -1, idx2 = list.size();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < l) idx1 = i;
            if (list.get(i) > r) {
                idx2 = i;
                break;
            }
        }
        return r - l + 1 - (idx2 - idx1 - 1);
    }

    private boolean isSqrt(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private boolean isUnion(int x, int y, int r1, int r2) {
        return (long) x * x + (long) y * y <= (r1 + (long) r2) * (r1 + (long) r2);
    }

    private int findParent(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent, parent[i]);
        }
        return parent[i];
    }

    private void union(int[] parent, int i, int j) {
        parent[findParent(parent, i)] = findParent(parent, j);
    }

    @Test
    public void test() {
        nonSpecialCount(4, 16);
    }
}
