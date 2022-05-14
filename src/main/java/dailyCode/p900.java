package dailyCode;

import java.util.*;

public class p900 {

    // p905 按奇偶排序数组「双指针」
    public int[] sortArrayByParity(int[] nums) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        return nums;
    }

    // p908 最小差值
    public int smallestRangeI(int[] nums, int k) {
        int min = 100000, max = 0;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        return Math.max(max - min - 2 * k, 0);
    }

    // p913 猫和老鼠「拓扑排序」
    static final int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    int[][] graph;
    int[][][] degrees;
    int[][][] results;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        this.graph = graph;
        this.degrees = new int[n][n][2];
        this.results = new int[n][n][2];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                degrees[i][j][MOUSE_TURN] = graph[i].length;
                degrees[i][j][CAT_TURN] = graph[j].length;
            }
        }
        for (int node : graph[0]) {
            for (int i = 0; i < n; i++) {
                degrees[i][node][CAT_TURN]--;
            }
        }
        for (int j = 1; j < n; j++) {
            results[0][j][MOUSE_TURN] = MOUSE_WIN;
            results[0][j][CAT_TURN] = MOUSE_WIN;
            queue.offer(new int[]{0, j, MOUSE_TURN});
            queue.offer(new int[]{0, j, CAT_TURN});
        }
        for (int i = 1; i < n; i++) {
            results[i][i][MOUSE_TURN] = CAT_WIN;
            results[i][i][CAT_TURN] = CAT_WIN;
            queue.offer(new int[]{i, i, MOUSE_TURN});
            queue.offer(new int[]{i, i, CAT_TURN});
        }
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int result = results[mouse][cat][turn];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);
            for (int[] prevState : prevStates) {
                int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];
                if (results[prevMouse][prevCat][prevTurn] == DRAW) {
                    boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
                    if (canWin) {
                        results[prevMouse][prevCat][prevTurn] = result;
                        queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                    } else {
                        degrees[prevMouse][prevCat][prevTurn]--;
                        if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                            int loseResult = prevTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            results[prevMouse][prevCat][prevTurn] = loseResult;
                            queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                        }
                    }
                }
            }
        }
        return results[1][2][MOUSE_TURN];
    }

    public List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<>();
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        if (prevTurn == MOUSE_TURN) {
            for (int prev : graph[mouse]) {
                prevStates.add(new int[]{prev, cat, prevTurn});
            }
        } else {
            for (int prev : graph[cat]) {
                if (prev != 0) {
                    prevStates.add(new int[]{mouse, prev, prevTurn});
                }
            }
        }
        return prevStates;
    }

    // p917 仅仅反转字母「双指针」
    public String reverseOnlyLetters(String s) {
        char[] arr = s.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            while (i < arr.length && !Character.isLetter(arr[i]))
                i++;
            while (j >= 0 && !Character.isLetter(arr[j]))
                j--;
            if (i == arr.length || j == -1)
                break;
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return new String(arr);
    }

    // p942 增减字符串匹配「贪心」
    public int[] diStringMatch(String s) {
        int n = s.length(), low = 0, high = n;
        int[] res = new int[n + 1];
        for (int i = 0; i < n; i++) {
            res[i] = s.charAt(i) == 'I' ? low++ : high--;
        }
        res[n] = low;
        return res;
    }

    // p944 删列造序「简单模拟」
    public int minDeletionSize(String[] strs) {
        int n = strs.length, len = strs[0].length(), res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j < n; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    // p954 二倍数对数组
    public boolean canReorderDoubled(int[] arr) {
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        int n = arr.length, index = -1;
        while (index < n - 1 && arr[index + 1] < 0)
            index++;
        for (int i = index, j = index - 1; i >= 0; i--) {
            if (set.contains(i))
                continue;
            while (j >= 0 && arr[j] > 2 * arr[i])
                j--;
            if (j == -1 || arr[j] != 2 * arr[i])
                return false;
            set.add(j--);
        }
        set.clear();
        for (int i = index + 1, j = i + 1; i < n; i++) {
            if (set.contains(i))
                continue;
            while (j < n && arr[j] < 2 * arr[i])
                j++;
            if (j == n || arr[j] != 2 * arr[i])
                return false;
            set.add(j++);
        }
        return true;
    }
}
