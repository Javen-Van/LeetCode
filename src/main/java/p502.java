import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-09-08
 * @Description
 */
public class p502 {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int cur = 0;
        int[][] help = new int[n][2];
        for (int i = 0; i < n; i++) {
            help[i][0] = capital[i];
            help[i][1] = profits[i];
        }
        Arrays.sort(help, Comparator.comparingInt(o -> o[0]));
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            while (cur < n && help[cur][0] <= w) {
                cur++;
                queue.offer(help[cur][1]);
            }
            if (!queue.isEmpty()) {
                w += queue.poll();
            }
        }
        return w;
    }

    // p500
    public String[] findWords(String[] words) {
        Set<Character> set1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'));
        Set<Character> set2 = new HashSet<>(Arrays.asList('a','s','d','f','g','h','j','k','l'));
        Set<Character> set3 = new HashSet<>(Arrays.asList('z','x','c','v','b','n','m'));
        List<String> res = new ArrayList<>();
        for (String str : words) {
            boolean flag = true;
            char[] chars = str.toLowerCase().toCharArray();
            if (set1.contains(chars[0])) {
                for (char c : chars) {
                    if (!set1.contains(c)){
                        flag = false;
                        break;
                    }
                }
            }
            if (set2.contains(chars[0])) {
                for (char c : chars) {
                    if (!set2.contains(c)){
                        flag = false;
                        break;
                    }
                }
            }
            if (set3.contains(chars[0])) {
                for (char c : chars) {
                    if (!set3.contains(c)){
                        flag = false;
                        break;
                    }
                }
            }
            if (flag) {
                res.add(str);
            }
        }
        String[] ans = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    @Test
    public void test(){
        String[] words = findWords(new String[]{"Hello"});
        System.out.println(Arrays.toString(words));
    }
}
