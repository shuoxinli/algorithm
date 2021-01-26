package com.lsx.algorithm.dp;

/**
 * @Author LiShuoXin
 * @Date 2021/1/26 19:10
 * <p>
 * 题目：
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 * <p>
 * 示例：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class MinDistance {


    public int minDistance(String word1, String word2) {
        if (word1.length() == 0){
            return word2.length();
        }
        if (word2.length() == 0){
            return word1.length();
        }
        return dp(word1.toCharArray(),word2.toCharArray());
    }

    /**
     * 字符串动规：定义两个指针，从头或尾开始比较，然后取最优操作，继续下一步。
     * <p>
     * 本题：定义两个指针，从头开始往下走，定义二维dp[][]。
     * <p>
     * base case：如果其中一个字符串走完了，操作数 = 之前的操作数 + 另一个字符串的剩余长度
     * <p>
     * 状态转移：四种情况（跳过，插入，删除，替换）
     * 跳过：两个字符相等，两个指针直接往后跳，操作数不变
     * 其他三种：dp（取三种操作中的操作数最小的那个）
     *
     * w1 变 w2， i为w1索引，j为w2索引
     * 插入：i 不变，j前进
     * 替换：i前进，j前进
     * 删除：i前进，j不变
     *
     * @param w1
     * @param w2
     * @return
     */
    public int dp(char[] w1, char[] w2) {
        int n1 = w1.length;
        int n2 = w2.length;

        // 定义dp[][] 整体往后移一位，dp[n1+1][n2+1] 代表w1和w2走到最后的最少编辑操作数
        int[][] dp = new int[n1 + 1][n2 + 1];

        // 初始化 base case dp索引从1开始，w1[0] 对应 dp[1]
        for (int i = 1; i <= n1; i++) {
            // dp[i][0] 代表 w2 为空
            dp[i][0] = i;
        }
        for (int j = 1; j <= n2; j++) {
            // w1为空
            dp[0][j] = j;
        }

        // 进行状态转移，自底向上,w1[0] 对应 dp[1]
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (w1[i-1] == w2[j-1]) {
                    // 相等，操作数不变，共同前进
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 三种操作取最少+1
                    dp[i][j] = min(
                            dp[i - 1][j - 1],   // 替换
                            dp[i - 1][j],     //删除
                            dp[i][j - 1]      //插入
                    ) + 1;
                }
            }
        }
        return dp[n1][n2];

    }

    public int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


}
