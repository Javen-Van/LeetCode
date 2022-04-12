package dailyCode;

import bean.TreeNode;

import java.util.*;

/**
 * @author Javen
 * @create 2021-08-24
 * @Description 有 n 个城市通过一些航班连接。给你一个数组flights ，其中flights[i] = [fromi, toi, pricei] ，表示该航班都从城市
 * fromi 开始，以价格 toi 抵达 pricei。
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k站中转的路线，使得从 src 到 dst 的
 * 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。
 */
public class p700 {

    // dailyCode.p700 二叉搜索树的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (root.val < val) return searchBST(root.right, val);
        else return searchBST(root.left, val);
    }

    // p717 1比特与2比特「简单模拟」
    public boolean isOneBitCharacter(int[] bits) {
        int n = bits.length;
        for (int i = 0; i < n; i++) {
            if (bits[i] == 1) {
                i++;
                continue;
            }
            if (i == n - 1) return true;
        }
        return false;
    }

    // p720 词典中最长的单词
    public String longestWord(String[] words) {
        Arrays.sort(words);
        String res = "";
        Set<String> set = new HashSet<>();
        set.add(res);
        for (String word : words) {
            if (set.contains(word.substring(0, word.length() - 1))) {
                res = word.length() > res.length() ? word : res;
                set.add(word);
            }
        }
        return res;
    }

    // p735 行星碰撞「栈」
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int x : asteroids) {
            while (!stack.isEmpty() && stack.peek() * x < 0) {
                if (Math.abs(stack.peek()) < Math.abs(x)) stack.pop();
                else if (Math.abs(stack.peek()) == Math.abs(x)) {
                    stack.pop();
                    break;
                } else {
                    x = stack.pop();
                }
            }
            stack.push(x);
        }
        Integer[] integers = stack.toArray(new Integer[0]);
        int[] res = new int[integers.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = integers[i];
        }
        return res;
    }

    // p744 寻找比目标字母大的最小字母「二分查找」
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if (target >= letters[n - 1]) return letters[0];
        int l = 0, r = n - 1, median;
        while (l < r) {
            median = (l + r) / 2;
            if (letters[median] <= target) l = median + 1;
            else r = median;
        }
        return letters[l];
    }

    // p748 最短补全词「easy」
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] license = new int[26];
        for (int i = 0; i < licensePlate.length(); i++) {
            char c = licensePlate.charAt(i);
            if (Character.isLetter(c)) {
                license[Character.toLowerCase(c) - 'a']++;
            }
        }
        int len = Integer.MAX_VALUE;
        String res = "";
        for (String word : words) {
            int[] collect = new int[26];
            for (int i = 0; i < word.length(); i++) {
                collect[word.charAt(i) - 'a']++;
            }
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (license[i] > collect[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag && word.length() < len) {
                res = word;
                len = word.length();
            }
        }
        return res;
    }

    // p762 二进制表示中质数个计算置位「位运算」
    public int countPrimeSetBits(int left, int right) {
        Set<Integer> set = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31));
        int res = 0;
        while (left <= right) {
            int num = left, count = 0;
            while (num != 0) {
                count += num & 1;
                num >>= 1;
            }
            res += set.contains(count) ? 1 : 0;
            left++;
        }
        return res;
    }

    // p780 到达终点
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (tx > 0 && ty > 0) {
            if (tx == sx && ty == sy) return true;
            if (sx > tx || sy > ty) return false;
            if (tx > ty) {
                if (ty == sy) {
                    return tx % ty == sx % sy;
                } else {
                    tx %= ty;
                }
            } else {
                if (tx == sx) {
                    return ty % tx == sy % sx;
                } else {
                    ty %= tx;
                }
            }
        }
        return false;
    }

    // p784 字母大小写全排列，bfs超出时间限制
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] map = new int[n][n];
        for (int[] flight : flights) {
            map[flight[0]][flight[1]] = flight[2];
        }
        int res = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        while (k >= 0 && !queue.isEmpty()) {
            k--;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (cur[0] == dst) {
                    res = Math.min(res, cur[1]);
                    continue;
                }
                for (int j = 0; j < n; j++) {
                    if (map[cur[0]][j] > 0) {
                        queue.offer(new int[]{j, cur[1] + map[cur[0]][j]});
                    }
                }
            }
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;
    }

    // dp
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[k + 2][n];
        int INF = 1010000;
        for (int i = 0; i <= k + 1; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][src] = 0;
        for (int i = 1; i <= k + 1; i++) {
            for (int[] flight : flights) {
                dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
            }
        }
        int res = INF;
        for (int i = 0; i <= k + 1; i++) {
            res = Math.min(res, dp[i][dst]);
        }
        return (res == INF) ? -1 : res;
    }

    // p786 第K个最小素数分数「多路归并」
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingDouble(o -> arr[o[0]] * 1.0 / arr[o[1]]));
        for (int i = 1; i < arr.length; i++) {
            queue.offer(new int[]{0, i});
        }
        while (k > 1) {
            k--;
            int[] poll = queue.poll();
            if (poll[0] + 1 < poll[1]) queue.offer(new int[]{poll[0] + 1, poll[1]});
        }
        int[] res = queue.poll();
        return new int[]{arr[res[0]], arr[res[1]]};
    }

    // p794 有效的井字游戏「分类讨论」
    public boolean validTicTacToe(String[] board) {
        int countX = 0, countO = 0;
        for (String s : board) {
            for (int i = 0; i < 3; i++) {
                if (s.charAt(i) == 'X') countX++;
                if (s.charAt(i) == 'O') countO++;
            }
        }
        if (countX != countO && countX != countO + 1) return false;
        if (win(board, 'X') && countX != countO + 1) return false;
        return !win(board, 'O') || countX == countO;
    }

    public boolean win(String[] board, char winner) {
        for (int i = 0; i < 3; i++) {
            if (winner == board[i].charAt(0) && winner == board[i].charAt(1) && winner == board[i].charAt(2))
                return true;
            if (winner == board[0].charAt(i) && winner == board[1].charAt(i) && winner == board[2].charAt(i))
                return true;
        }
        if (winner == board[0].charAt(0) && winner == board[1].charAt(1) && winner == board[2].charAt(2)) return true;
        return winner == board[0].charAt(2) && winner == board[1].charAt(1) && winner == board[2].charAt(0);
    }

    // p798 得分最高的最小论调「查分数组」
    public int bestRotation(int[] nums) {
        int n = nums.length;
        int[] diffs = new int[n];
        for (int i = 0; i < n; i++) {
            int low = (i + 1) % n;
            int high = (i - nums[i] + n + 1) % n;
            diffs[low]++;
            diffs[high]--;
            if (low >= high) {
                diffs[0]++;
            }
        }
        int bestIndex = 0, maxScore = 0, score = 0;
        for (int i = 0; i < n; i++) {
            score += diffs[i];
            if (score > maxScore) {
                bestIndex = i;
                maxScore = score;
            }
        }
        return bestIndex;
    }
}
