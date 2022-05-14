package dailyCode;

import org.junit.Test;

import java.util.*;

/**
 * @author Javen
 * @create 2021-12-24
 * @Description
 */
public class p1700 {

    // p1705 吃苹果的最大数目「贪心 + 优先队列」
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        int n = apples.length, res = 0;
        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && queue.peek()[0] <= i) {
                queue.poll();
            }
            if (apples[i] > 0) queue.offer(new int[]{i + days[i], apples[i]});
            if (!queue.isEmpty()) {
                int[] peek = queue.peek();
                peek[1]--;
                if (peek[1] == 0) queue.poll();
                res++;
            }
        }
        Map<Character, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            while (!queue.isEmpty() && queue.peek()[0] <= n) {
                queue.poll();
            }
            if (queue.isEmpty()) break;
            int[] poll = queue.poll();
            int cur = Math.min(poll[0] - n, poll[1]);
            res += cur;
            n += cur;
        }
        return res;
    }

    // p1706 球会落在何处「简单模拟」
    public int[] findBall(int[][] grid) {
        // 1导向右侧，-1导向左侧
        int m = grid.length, n = grid[0].length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int j = 0, col = i, dir = grid[j][col];
            while (true) {
                if ((col == n - 1 && dir == 1) || (col == 0) && dir == -1) break;
                if (dir + grid[j][col + dir] == 0) break;
                j++;
                col += dir;
                if (j == m) break;
                dir = grid[j][col];
            }
            res[i] = j == m ? col : -1;
        }
        return res;
    }

    // p1728 猫和老鼠2「拓扑排序」
    static final int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int UNKNOWN = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    static final int MAX_MOVES = 1000;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int rows, cols;
    String[] grid;
    int catJump, mouseJump;
    int food;
    int[][][] degrees;
    int[][][][] results;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.rows = grid.length;
        this.cols = grid[0].length();
        this.grid = grid;
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        int startMouse = -1, startCat = -1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = grid[i].charAt(j);
                if (c == 'M') {
                    startMouse = getPos(i, j);
                } else if (c == 'C') {
                    startCat = getPos(i, j);
                } else if (c == 'F') {
                    food = getPos(i, j);
                }
            }
        }
        int total = rows * cols;
        degrees = new int[total][total][2];
        results = new int[total][total][2][2];
        Queue<int[]> queue = new ArrayDeque<>();
        // 计算每个状态的度
        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#') {
                continue;
            }
            for (int cat = 0; cat < total; cat++) {
                int catRow = cat / cols, catCol = cat % cols;
                if (grid[catRow].charAt(catCol) == '#') {
                    continue;
                }
                degrees[mouse][cat][MOUSE_TURN]++;
                degrees[mouse][cat][CAT_TURN]++;
                for (int[] dir : dirs) {
                    for (int row = mouseRow + dir[0], col = mouseCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0 && col < cols && grid[row].charAt(col) != '#' && jump <= mouseJump; row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(row, col), nextCat = getPos(catRow, catCol);
                        degrees[nextMouse][nextCat][MOUSE_TURN]++;
                    }
                    for (int row = catRow + dir[0], col = catCol + dir[1], jump = 1; row >= 0 && row < rows && col >= 0 && col < cols && grid[row].charAt(col) != '#' && jump <= catJump; row += dir[0], col += dir[1], jump++) {
                        int nextMouse = getPos(mouseRow, mouseCol), nextCat = getPos(row, col);
                        degrees[nextMouse][nextCat][CAT_TURN]++;
                    }
                }
            }
        }
        // 猫和老鼠在同一个单元格，猫获胜
        for (int pos = 0; pos < total; pos++) {
            int row = pos / cols, col = pos % cols;
            if (grid[row].charAt(col) == '#') {
                continue;
            }
            results[pos][pos][MOUSE_TURN][0] = CAT_WIN;
            results[pos][pos][MOUSE_TURN][1] = 0;
            results[pos][pos][CAT_TURN][0] = CAT_WIN;
            results[pos][pos][CAT_TURN][1] = 0;
            queue.offer(new int[]{pos, pos, MOUSE_TURN});
            queue.offer(new int[]{pos, pos, CAT_TURN});
        }
        // 猫和食物在同一个单元格，猫获胜
        for (int mouse = 0; mouse < total; mouse++) {
            int mouseRow = mouse / cols, mouseCol = mouse % cols;
            if (grid[mouseRow].charAt(mouseCol) == '#' || mouse == food) {
                continue;
            }
            results[mouse][food][MOUSE_TURN][0] = CAT_WIN;
            results[mouse][food][MOUSE_TURN][1] = 0;
            results[mouse][food][CAT_TURN][0] = CAT_WIN;
            results[mouse][food][CAT_TURN][1] = 0;
            queue.offer(new int[]{mouse, food, MOUSE_TURN});
            queue.offer(new int[]{mouse, food, CAT_TURN});
        }
        // 老鼠和食物在同一个单元格且猫和食物不在同一个单元格，老鼠获胜
        for (int cat = 0; cat < total; cat++) {
            int catRow = cat / cols, catCol = cat % cols;
            if (grid[catRow].charAt(catCol) == '#' || cat == food) {
                continue;
            }
            results[food][cat][MOUSE_TURN][0] = MOUSE_WIN;
            results[food][cat][MOUSE_TURN][1] = 0;
            results[food][cat][CAT_TURN][0] = MOUSE_WIN;
            results[food][cat][CAT_TURN][1] = 0;
            queue.offer(new int[]{food, cat, MOUSE_TURN});
            queue.offer(new int[]{food, cat, CAT_TURN});
        }
        // 拓扑排序
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int result = results[mouse][cat][turn][0];
            int moves = results[mouse][cat][turn][1];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);
            for (int[] prevState : prevStates) {
                int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];
                if (results[prevMouse][prevCat][prevTurn][0] == UNKNOWN) {
                    boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
                    if (canWin) {
                        results[prevMouse][prevCat][prevTurn][0] = result;
                        results[prevMouse][prevCat][prevTurn][1] = moves + 1;
                        queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                    } else {
                        degrees[prevMouse][prevCat][prevTurn]--;
                        if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                            int loseResult = prevTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            results[prevMouse][prevCat][prevTurn][0] = loseResult;
                            results[prevMouse][prevCat][prevTurn][1] = moves + 1;
                            queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                        }
                    }
                }
            }
        }
        return results[startMouse][startCat][MOUSE_TURN][0] == MOUSE_WIN && results[startMouse][startCat][MOUSE_TURN][1] <= MAX_MOVES;
    }

    public List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<int[]>();
        int mouseRow = mouse / cols, mouseCol = mouse % cols;
        int catRow = cat / cols, catCol = cat % cols;
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        int maxJump = prevTurn == MOUSE_TURN ? mouseJump : catJump;
        int startRow = prevTurn == MOUSE_TURN ? mouseRow : catRow;
        int startCol = prevTurn == MOUSE_TURN ? mouseCol : catCol;
        prevStates.add(new int[]{mouse, cat, prevTurn});
        for (int[] dir : dirs) {
            for (int i = startRow + dir[0], j = startCol + dir[1], jump = 1; i >= 0 && i < rows && j >= 0 && j < cols && grid[i].charAt(j) != '#' && jump <= maxJump; i += dir[0], j += dir[1], jump++) {
                int prevMouseRow = prevTurn == MOUSE_TURN ? i : mouseRow;
                int prevMouseCol = prevTurn == MOUSE_TURN ? j : mouseCol;
                int prevCatRow = prevTurn == MOUSE_TURN ? catRow : i;
                int prevCatCol = prevTurn == MOUSE_TURN ? catCol : j;
                int prevMouse = getPos(prevMouseRow, prevMouseCol);
                int prevCat = getPos(prevCatRow, prevCatCol);
                prevStates.add(new int[]{prevMouse, prevCat, prevTurn});
            }
        }
        return prevStates;
    }

    public int getPos(int row, int col) {
        return row * cols + col;
    }

    // p1765 地图中的最高点「多源广度优先搜索」
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] height = new int[m][n]; // -1表示未初始化
        int[] dif = new int[]{0, 1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                height[i][j] = -1;
                if (isWater[i][j] == 1) {
                    height[i][j] = 0;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int j = 0; j < 4; j++) {
                    int x = cur[0] + dif[j], y = cur[1] + dif[j + 1];
                    if (x >= 0 && x < m && y >= 0 && y < n && height[x][y] == -1) {
                        height[x][y] = height[cur[0]][cur[1]] + 1;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return height;
    }

}
