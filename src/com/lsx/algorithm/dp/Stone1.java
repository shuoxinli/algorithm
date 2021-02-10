package com.lsx.algorithm.dp;

/**
 * @Author LiShuoXin
 * @Date 2021/2/9 15:46
 * 动态规划：博弈问题
 */
public class Stone1 {

    /**
     * 题目描述：
     *      亚历克斯和李用几堆石子在做游戏。偶数堆石子排成一行，每堆都有正整数颗石子 piles[i] 。
     *      游戏以谁手中的石子最多来决出胜负。石子的总数是奇数，所以没有平局。
     *      亚历克斯和李轮流进行，亚历克斯先开始。 每回合，玩家从行的开始或结束处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中石子最多的玩家获胜。
     *      假设亚历克斯和李都发挥出最佳水平，当亚历克斯赢得比赛时返回 true ，当李赢得比赛时返回 false 。
     *
     * 示例：
     *      输入：[5,3,4,5]
     *      输出：true
     * 解释：
     *      亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
     *      假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
     *      如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
     *      如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
     *      这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
     */

    /**
     * 定义一个类，来存放dp数组的结果，fir 表示先手拿到的石子，sec 表示后手拿到的石子
     */
    class Pair{
        int fir;
        int sec;
        public Pair(int fir, int sec) {
            this.fir = fir;
            this.sec = sec;
        }
    }

    /**
     * 定义 dp 数组：
     *      dp[i][j].fir 表⽰，对于 piles[i...j] 这部分⽯头堆，先⼿能获得的最⾼分数。
     *      dp[i][j].sec 表⽰，对于 piles[i...j] 这部分⽯头堆，后⼿能获得的最⾼分数。
     *
     * 明确【状态】：开始的索引 i，结束的索引 j，当前轮到的人。
     *      dp[i][j][fir or sec]
     *
     * 【选择】：当前先手选择后，变成下一步的后手，当前后手变成下一步的先手。
     *      dp[i][j].fir = max(piles[i] + dp[i+1][j].sec, piles[j] + dp[i][j-1].sec)
     *      dp[i][j].fir = max( 选择最左边的⽯头堆 , 选择最右边的⽯头堆 )
     *
     *      if 先手选择左边:
     * 	        dp[i][j].sec = dp[i+1][j].fir
     *      if 先手选择右边:
     * 	        dp[i][j].sec = dp[i][j-1].fir
     *
     * 初始化base case：
     *      当 i == j
     *      dp[i][j].fir = piles[i]
     *      dp[i][j].sec = 0
     *     其他都初始化为0
     *
     * 要求的目标：dp[0][n-1].fir - dp[0][n-1].sec
     *
     * 所以需要斜向遍历。
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // 定义dp
        Pair[][] dp = new Pair[n][n];
        // 初始化base case
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                dp[i][j] = new Pair(0,0);
            }
        }
        for (int i=0;i<n;i++){
            // 只有一堆石子，都被先手拿了
            dp[i][i].fir = piles[i];
            dp[i][i].sec = 0;
        }
        // 斜向遍历
        for (int l=1;l<n;l++){
            for (int i=0;i<n-l;i++){
                int j = i+l;

                // 先手拿最左边 还是 最右边的石子 的结果
                int left = piles[i]+ dp[i+1][j].sec;
                int right = piles[j]+dp[i][j-1].sec;

                // 取最大，当前步后手变成下一步的先手
                if (left> right){
                    // 拿左边
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i+1][j].fir;
                }else{
                    // 拿右边
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j-1].fir;
                }
            }
        }
        return dp[0][n-1].fir > dp[0][n-1].sec;
    }
}
