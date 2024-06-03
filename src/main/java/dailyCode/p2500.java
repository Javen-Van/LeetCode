package dailyCode;

import java.util.ArrayList;
import java.util.List;

public class p2500 {

    // TODO p2569 更新数组后处理求和查询
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        List<Long> res = new ArrayList<>();
        long sum1 = 0, sum2 = 0;
        int n = nums1.length;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        for (int[] query : queries) {
            if (query[0] == 1) {
                sum1 = n - sum1;
            }
            if (query[0] == 2) {
                sum2 += query[1] * sum1;
            }
            if (query[0] == 3) {
                res.add(sum2);
            }
        }
        return new long[0];
    }
}
