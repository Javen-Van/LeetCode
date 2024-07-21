package lccup;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoubleWeek134 {

    public int numberOfAlternatingGroups(int[] colors) {
        int sum = 0;
        for (int i = 0; i < colors.length; i++) {
            if (isAlternatingGroups(colors, i)) {
                sum++;
            }
        }
        return sum;
    }

    private boolean isAlternatingGroups(int[] colors, int i) {
        int n = colors.length;
        if (colors[i] == 0) {
            return colors[(i + 1) % n] == 1 && colors[(i + 2) % n] == 0;
        } else {
            return colors[(i + 1) % n] == 0 && colors[(i + 2) % n] == 1;
        }
    }

    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        Arrays.sort(enemyEnergies);
        int min = enemyEnergies[0];
        if (currentEnergy < min) return 0;
        long sum = currentEnergy;
        for (int enemyEnergy : enemyEnergies) {
            sum += enemyEnergy;
        }
        return sum / min - 1;
    }

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int n = colors.length, res = 0;
        for (int i = 0, j = 1; i < n; ) {
            while (colors[j % n] != colors[(j - 1) % n] && j < n + k - 1) {
                j++;
            }
            res += Math.max(j - i + 1 - k, 0);
            i = j++;
        }
        return res;
    }

    private boolean isSub(int num, int k) {
        for (int i = 0; i < 32; i++) {
            if ((num >> i & 1) == 0 && (k >> i & 1) == 1) return false;
        }
        return true;
    }

    @Test
    public void test() {
        int[] colors = {0, 1, 0, 1};
        System.out.println(numberOfAlternatingGroups(colors, 3));
    }
}
