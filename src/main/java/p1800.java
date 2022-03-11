import org.junit.Test;

import java.util.*;

public class p1800 {

    // p1816 截断句子
    public String truncateSentence(String s, int k) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && --k == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    // p2029 石子游戏IX
    // a赢：b拿的石子后总价值为3x
    // b赢：a拿了石子后总价值为3x，或当前没有剩余石子
    // 5 5 1 2 4 3 -> 2 2 1 2 1 -> 0 2 3
    // 1 1 2 2 a
    // b: 1 -> 1 0, 2 -> 2 0
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        if (count[0] % 2 == 0) return count[1] >= 1 && count[2] >= 1;
        return Math.abs(count[1] - count[2]) > 2;
    }

    // p2047 句子中的有效单词数
    public int countValidWords(String sentence) {
        int res = 0;
        String[] s = sentence.split(" ");
        for (String str : s) {
            if ("".equals(str)) continue;
            if (isValid(str)) res++;
        }
        return res;
    }

    public boolean isValid(String s) {
        boolean hasConnect = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) return false;
            if (c == '-') {
                if (hasConnect || i == 0 || i == s.length() - 1 || !Character.isLetter(s.charAt(i - 1)) || !Character.isLetter(s.charAt(i + 1)))
                    return false;
                hasConnect = true;
            } else if (c == ',' || c == '.' || c == '!') {
                if (i != s.length() - 1) return false;
            }
        }
        return true;
    }

    // p2049 统计最高分的节点数目
    int n;
    int res = 0;
    long maxScore = 0L;
    List<Integer>[] children;

    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            int parent = parents[i];
            children[parent].add(i);
        }
        dfs(0);
        return res;
    }

    public int dfs(int node) {
        long score = 1L;
        int num = n - 1; // 剩余节点总数
        for (int child : children[node]) {
            int size = dfs(child); // 子树大小
            score *= size;
            num -= size;
        }
        if (node != 0) score *= num;
        if (score == maxScore) res++;
        else if (score > maxScore) {
            maxScore = score;
            res = 1;
        }
        return n - num;
    }

    // p2055 蜡烛之间的盘子「二分 + 前缀和」
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int[] res = new int[queries.length];
        int n = s.length(), count = 0, index = 0;
        List<Integer> sit = new ArrayList<>(), nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                nums.add(sit.size() == 0 ? 0 : count);
                sit.add(i);
                count = 0;
            } else {
                count++;
            }
        }
        int[] pre = new int[sit.size()];
        for (int i = 0; i < sit.size(); i++) {
            pre[i] = i == 0 ? nums.get(i) : nums.get(i) + pre[i - 1];
        }
        for (int[] query : queries) {
            int left = binarySearch(sit, query[0], true), right = binarySearch(sit, query[1], false);
            res[index++] = left >= right ? 0 : pre[right] - pre[left];
        }
        return res;
    }

    public int binarySearch(List<Integer> arr, int target, boolean isLeft) {
        int l = 0, r = arr.size() - 1, mid;
        while (l <= r) {
            mid = l + (r - l) / 2;
            if (arr.get(mid) == target) return mid;
            if (arr.get(mid) > target) r = mid - 1;
            else l = mid + 1;
        }
        return isLeft ? l : r;
    }

    // p2100 适合打劫银行的日子
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int n = security.length;
        int[] left = new int[n], right = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = security[i] <= security[i - 1] ? left[i - 1] + 1 : 0;
            right[n - i - 1] = security[n - i - 1] <= security[n - i] ? right[n - i] + 1 : 0;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (time <= left[i] && time <= right[i]) res.add(i);
        }
        return res;
    }

    // p2104 子数组范围和
    public long subArrayRanges(int[] nums) {
        long minSum = 0L, maxSum = 0L;
        int n = nums.length;
        Deque<Integer> minStack = new LinkedList<>();
        Deque<Integer> maxStack = new LinkedList<>();
        int[] minLeft = new int[n], minRight = new int[n], maxLeft = new int[n], maxRight = new int[n];
        for (int i = 0; i < n; i++) {
            while (!minStack.isEmpty() && nums[i] < nums[minStack.peek()]) {
                minStack.pop();
            }
            minLeft[i] = minStack.isEmpty() ? -1 : minStack.peek();
            minStack.push(i);
            while (!maxStack.isEmpty() && nums[i] >= nums[maxStack.peek()]) {
                maxStack.pop();
            }
            maxLeft[i] = maxStack.isEmpty() ? -1 : maxStack.peek();
            maxStack.push(i);
        }
        minStack.clear();
        maxStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!minStack.isEmpty() && nums[i] <= nums[minStack.peek()]) {
                minStack.pop();
            }
            minRight[i] = minStack.isEmpty() ? n : minStack.peek();
            minStack.push(i);
            while (!maxStack.isEmpty() && nums[i] > nums[maxStack.peek()]) {
                maxStack.pop();
            }
            maxRight[i] = maxStack.isEmpty() ? n : maxStack.peek();
            maxStack.push(i);
        }
        for (int i = 0; i < n; i++) {
            minSum += (long) nums[i] * (i - minLeft[i]) * (minRight[i] - i);
            maxSum += (long) nums[i] * (i - maxLeft[i]) * (maxRight[i] - i);
        }
        return maxSum - minSum;
    }

    @Test
    public void test() {
        int[] arr = {1, 3, 3};
        System.out.println(subArrayRanges(arr));
    }
}
