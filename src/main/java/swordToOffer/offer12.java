package swordToOffer;

/**
 * @author Javen
 * @create 2021-12-25
 * @Description 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回
 *              false 。
 *              单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class offer12 {

    int m, n;

    // dfs解法
    public boolean exist(char[][] board, String word) {
        this.m = board.length;
        this.n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, String word, int x, int y, int index) {
        if (index == word.length())
            return true;
        if (!isInBoard(x, y) || board[x][y] != word.charAt(index))
            return false;
        char temp = board[x][y];
        board[x][y] = '.';
        boolean res = dfs(board, word, x + 1, y, index + 1) || dfs(board, word, x - 1, y, index + 1) ||
                dfs(board, word, x, y + 1, index + 1) || dfs(board, word, x, y - 1, index + 1);
        board[x][y] = temp;
        return res;
    }

    public boolean isInBoard(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
