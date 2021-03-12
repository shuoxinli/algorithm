package com.lsx.algorithm.backtrack;

/**
 * 回溯算法：数独问题
 */
public class Sudoku {

    public boolean isValidSudoku(char[][] board) {
        return backtrack(board, 0, 0);
    }

    private boolean backtrack(char[][] board, int i, int j) {
        int n = 9, m = 9;
        if (j == m) {
            // 遍历到该行最后，换行重新开始
            return backtrack(board, i + 1, 0);
        }
        // base case
        if (i == n) {
            // 遍历完最后一行，找到一个可行解，返回 true
            return true;
        }
        // 如果该位置为预设值，不需要我们管，直接跳过
        if (board[i][j] != '.') {
            return backtrack(board, i, j + 1);
        }
        // 针对每个位置，遍历1-9
        for (char ch = '1'; ch <= '9'; ch++) {
            // 判断该位置该数字是否合法
            if (!isValid(board, i, j, ch)) {
                continue;
            }
            // 放置该数字
            board[i][j] = ch;
            // 递归，下一个位置
            backtrack(board, i, j + 1);
            // 撤销该数字
            board[i][j] = '.';
        }
        // 如果该位置遍历1-9还没找到可行解，返回false
        return false;
    }

    /**
     * 判断当前位置，放置数字是否合法
     *
     * @param board
     * @param r     当前行
     * @param c     当前列
     * @param ch    当前数字
     * @return
     */
    private boolean isValid(char[][] board, int r, int c, char ch) {
        for (int i = 0; i < 9; i++) {
            // 判断行是否重复
            if (board[r][i] == ch)
                return false;
            // 判断列是否重复
            if (board[i][c] == ch) {
                return false;
            }
            // 判断当前位置 所属的小方格里，是否已经有该数字
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + c % 3] == ch) {
                // (r/3)*3 表示所属小方格的起始行 + i/3 就是 小方格的行遍历
                // (c/3)*3 表示所属小方格的起始列 + c%3 就是 小方格的列遍历
                return false;
            }
        }
        // 没有重复
        return true;
    }
}
