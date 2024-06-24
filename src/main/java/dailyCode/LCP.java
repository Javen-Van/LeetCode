package dailyCode;

public class LCP {

    /**
     * LCP61 气温变化趋势
     *
     * @param temperatureA
     * @param temperatureB
     * @return
     */
    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        for (int i = 0; i < n - 1; i++) {
            if (temperatureA[i] < temperatureA[i + 1]) {
                temperatureA[i] = 1;
            } else if (temperatureA[i] == temperatureA[i + 1]) {
                temperatureA[i] = 0;
            } else {
                temperatureA[i] = -1;
            }
            if (temperatureB[i] < temperatureB[i + 1]) {
                temperatureB[i] = 1;
            } else if (temperatureB[i] == temperatureB[i + 1]) {
                temperatureB[i] = 0;
            } else {
                temperatureB[i] = -1;
            }
        }
        int count = 0, res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (temperatureA[i] == temperatureB[i]) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 0;
            }
        }
        return res;
    }

}
