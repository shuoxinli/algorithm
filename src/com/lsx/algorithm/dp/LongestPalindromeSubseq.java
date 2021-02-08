package com.lsx.algorithm.dp;

/**
 * @Author LiShuoXin
 * @Date 2021/2/8 15:24
 * 最长回文子序列
 */
public class LongestPalindromeSubseq {

    /**
     * 题目描述：
     *      给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     *
     * 例子：
     *      输入: "bbbab"
     *      输出: 4         一个可能的最长回文子序列为 "bbbb"。
     *
     *      输入: "cbbd"
     *      输出：2         一个可能的最长回文子序列为 "bb"。
     */

    /**
     * 思路：
     * 定义dp数组：
     * 在子数组arr[i..j]中，最长回文子序列的长度定义为 dp[i][j]
     * 明确base case：
     * 当只有一个字符时，最长回文子序列的长度为1，即dp[i][j]=1 (i==j)
     * 对于i > j 的情况是不存在子序列，初始化为0
     * 状态转移：
     * dp[i][j]的值取决于arr[i] 和 arr[j]：
     * 当arr[i] == arr[j]时，dp[i][j] = dp[i+1][j-1] + 2 (它们两字符）
     * 当arr[i] == arr[j]时，dp[i][j] = Math.max(dp[i+1][j],dp[i][j-1]) （取子问题中最长的那个）
     * 最后结果：求dp[0][n-1]的值
     */
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[][] dp = new int[n][n];
        // 初始化base case
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 反向遍历，因为最后是求右上角的值，只需要遍历右上三角形的区域
        for (int i = n - 1; i >= 0; i--) {     // 向上
            for (int j = i + 1; j < n; j++) {   //向右,从倒数第二行开始向右
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
