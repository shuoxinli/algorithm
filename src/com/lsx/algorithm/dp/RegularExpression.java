package com.lsx.algorithm.dp;

/**
 * 动态规划：正则表达式匹配
 */
public class RegularExpression {

    /**
     * 普通匹配：一对一匹配
     * 处理【.】点号：pattern 为 . ,则匹配
     * 处理 【*】星号：pattern 大于2，且下个字符为*，则当前可以有两种选择：
     * 星号匹配为0：pattern跳过两个字符，text 不动
     * 当前位置匹配，星号暂定，匹配串移动一个字符，继续匹配
     * <p>
     * 动规：在于存在【重叠子问题】，可以利用备忘录，避免重复计算
     */
    public boolean isMatch(String s, String p) {
        char[] str = s.toCharArray();
        char[] pat = p.toCharArray();

        int[][] book = new int[str.length+1][pat.length+1];
        return dp(0, str, 0, pat, book);
    }

    private boolean dp(int i, char[] str, int j, char[] pat, int[][] book) {
        // 如果备忘录存在，直接返回
        if (book[i][j] != 0) {
            return book[i][j] == 1;
        }
        // 模式串结束，则匹配串也要结束
        if (j == pat.length) {
            return i == str.length;
        }
        // 判断当前位置是否匹配, j未结束，i也未结束，且要处理点号
        boolean cur = i < str.length && (pat[j] == '.' || pat[j] == str[i]);

        boolean res;
        // 处理模式串出现星号，至少还剩两个字符，且下一字符为星号
        if (j <= pat.length - 2 && pat[j+1] == '*') {
            res = dp(i, str, j + 2, pat, book)  // 匹配0次，模式串跳过两个字符
                    || (cur && dp(i + 1, str, j, pat, book));  // 当前位置匹配，星号暂定，匹配串移动一个字符，继续匹配
        } else {
            // 无星号，或长度不够,正常匹配，当前位置 和 递归下一位置
            res = cur && dp(i + 1, str, j + 1, pat, book);
        }

        // 存进备忘录，1是true，2是false
        book[i][j] = res ? 1 : 2;
        return res;
    }
}
