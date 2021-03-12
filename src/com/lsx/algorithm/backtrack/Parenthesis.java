package com.lsx.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 回溯算法：合法括号的生成
 */
public class Parenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder track = new StringBuilder();
        backtrack(2 * n, n, n, track, res);
        return res;
    }

    /**
     * 回溯算法
     *
     * @param n     n对括号
     * @param left  左括号还剩多少
     * @param right 右括号还剩多少
     * @param track 当前路径
     * @param res   最终结果
     */
    private void backtrack(int n, int left, int right, StringBuilder track, List<String> res) {
        // 判断合法性质，左括号剩余 小于或等于 右括号
        if (left > right)
            return;
        // 可用数量小于0，不合法
        if (left < 0 || right < 0)
            return;
        // 当左括号和右括号都恰好用完了，得到一个合法的括号组合
        if (left == 0 && right == 0) {
            res.add(track.toString());
            return;
        }

        // 尝试放入一个左括号
        track.append("(");
        // 递归，左括号-1
        backtrack(n, left - 1, right, track, res);
        // 撤销左括号
        track.deleteCharAt(track.length() - 1);

        // 尝试放入一个右括号
        track.append(")");
        backtrack(n, left, right - 1, track, res);
        track.deleteCharAt(track.length() - 1);
    }
}
