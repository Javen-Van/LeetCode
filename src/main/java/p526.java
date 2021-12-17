import com.sun.org.apache.bcel.internal.generic.SWAP;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Javen
 * @create 2021-08-16
 * @Description 假设有从 1 到 N 的N个整数，如果从这n个数字中成功构造出一个数组，使得数组的第 i位 (1 <= i <= N) 满足如下两个条件中的
 * 一个，我们就称这个数组为一个优美的排列。条件：
 * 1 第i位的数字能被i整除
 * 2 i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * 1 2 3 4
 * 2 1 3 4
 * 3 2 1 4
 * <p>
 * 4 2 3 1
 * 1 4 3 2
 * 3 4 1 2
 * 4 1 3 2
 * 2 4 3 1
 */
public class p526 {

    int res = 0;
    List<Integer>[] match;
    boolean[] visit;

    public int countArrangement(int n) {
        visit = new boolean[n + 1];
        match = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            match[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    match[i].add(j);
                }
            }
        }
        dfs(1, n);
        return res;
    }

    void dfs(int index, int n) {
        if (index == n + 1) {
            res++;
            return;
        }
        for (int x : match[index]) {
            if (!visit[x]) {
                visit[x] = true;
                dfs(index + 1, n);
                visit[x] = false;
            }
        }
    }

}
