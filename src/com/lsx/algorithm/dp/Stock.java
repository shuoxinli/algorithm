package com.lsx.algorithm.dp;

/**
 * 动态规划：股票问题
 * 【状态】有三种： 天数，交易次数，当前持有状态。定义三维数组
 * 【选择】有三种： buy，sell，rest（rest包括 持有rest 和 未持有rest）
 * 【状态转移】：
 * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
 * max( 选择 rest , 选择 sell )
 * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 * max( 选择 rest , 选择 buy )
 * 【base case】：
 * i=-1，表示还没开始，当前未持有状态的利润都为0；交易次数为0，利润也为0
 * dp[-1][k][0] = dp[i][0][0] = 0
 * 还未开始 或 交易次数为0，当前持有状态不可能有，为无穷
 * dp[-1][k][1] = dp[i][0][1] = -infinity
 */
public class Stock {

    /**
     * 当 交易次数 K=1时，
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) = max(dp[i-1][1][1], - prices[i])
     * <p>
     * 此时，K并不影响选择，可以化简。
     */
    public int maxProfit_k_1(int[] prices) {
        int len = prices.length;
        // 定义二维数组，0表示未持有，1表示持有
        int[][] dp = new int[len][2];
        // 由于索引 -1 并不存在，所以base case只能放在【状态】遍历中去处理
        for (int i = 0; i < len; i++) {
            // 处理 -1 情况
            if (i - 1 == -1) {
                // dp[0][0] = max(dp[-1][0],dp[-1][1]+prices[0]) = max(0,-infinity+prices[0])=0
                dp[i][0] = 0;
                // dp[0][1] = max(dp[-1][1],dp[-1][0]-prices[0]) = max(-infinity,-prices[0]) = -prices[0]
                dp[i][1] = -prices[i];
                continue;
            }
            // 处理正常情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * 当 k = 无穷时，
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * <p>
     * 由于k为无穷，k-1也是无穷，所以 k 并不影响选择，可以化简。
     */
    public int maxProfit_k_inf(int[] prices) {
        int len = prices.length;
        // 定义二维数组，0表示未持有，1表示持有
        int[][] dp = new int[len][2];
        // 由于索引 -1 并不存在，所以base case只能放在【状态】遍历中去处理
        for (int i = 0; i < len; i++) {
            // 处理 -1 情况
            if (i - 1 == -1) {
                // dp[0][0] = max(dp[-1][0], dp[-1][1]+prices[0]) = max(0,-infinity+prices[0]) = 0
                dp[i][0] = 0;
                // dp[0][1] = max(dp[-1][1],dp[-1][0]-prices[0]) = max(-infinity,-prices[0]) = -prices[0]
                dp[i][1] = -prices[i];
                continue;
            }
            // 处理正常情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * 当 k = 2时，
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     * 此时，k为有效正整数，k-1 与 k 明显不同，不可忽略。
     */
    public int maxProfit_k_2(int[] prices) {
        int len = prices.length;
        // 定义三维数组，0表示未持有，1表示持有，k不可忽略，因为k索引从1开始，到索引2
        int[][][] dp = new int[len][3][2];
        // 由于索引 -1 并不存在，所以base case只能放在【状态】遍历中去处理
        // 此时需要遍历k，索引从1开始
        for (int i = 0; i < len; i++) {
            for (int k = 2; k >= 1; k--) {
                // 处理 i=-1 情况
                if (i - 1 == -1) {
                    // dp[0][k][0] = max(dp[-1][k][0],dp[-1][k][1]+prices[0])=0
                    dp[i][k][0] = 0;
                    // dp[0][k][1] = max(dp[-1][k][1],dp[-1][k-1][0]-prices[0])=-prices[0]
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                // 处理正常情况
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][2][0];
    }

    /**
     * 当 k 为有效正整数时，可以直接跟 k=2 一样，用三维数组来遍历，
     * 但是 k 很大时，三维数组可能会超出内存。
     *
     * 其实，买入 和 卖出 至少就需要两天，也就按两天一次交易来算，
     * n 天 总共最多就只有 n/2 次交易，当k 大于 n/2 时，就可以当作 k 无穷限制来使用！
     *
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */
    public int maxProfit_k_any(int[] prices,int max_k) {
        int len = prices.length;
        // 如果 k 大于 n/2，就当作k为无穷来使用
        if (max_k > len/2){
            return maxProfit_k_inf(prices);
        }

        // 定义三维数组，0表示未持有，1表示持有，k不可忽略，因为k索引从1开始，到索引2
        int[][][] dp = new int[len][max_k+1][2];
        // 由于索引 -1 并不存在，所以base case只能放在【状态】遍历中去处理
        // 此时需要遍历k，索引从1开始
        for (int i = 0; i < len; i++) {
            for (int k = max_k; k >= 1; k--) {
                // 处理 i=-1 情况
                if (i - 1 == -1) {
                    // dp[0][k][0] = max(dp[-1][k][0],dp[-1][k][1]+prices[0])=0
                    dp[i][k][0] = 0;
                    // dp[0][k][1] = max(dp[-1][k][1],dp[-1][k-1][0]-prices[0])=-prices[0]
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                // 处理正常情况
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][max_k][0];
    }

    /**
     * 当 k 为 无穷时，且卖出股票有一天【冷冻期】
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 注意：第 i 天选择 buy 的时候，要从 i-2(前两天) 的状态转移，⽽不是 i-1
     */
    public int maxProfit_with_cool(int[] prices) {
        int len = prices.length;
        // 定义二维数组，0表示未持有，1表示持有
        int[][] dp = new int[len][2];
        // 由于索引 -1 并不存在，所以base case只能放在【状态】遍历中去处理
        for (int i = 0; i < len; i++) {
            // 处理 -1 情况
            if (i - 1 == -1) {
                // dp[0][0] = max(dp[-1][0], dp[-1][1]+prices[0]) = max(0,-infinity+prices[0]) = 0
                dp[i][0] = 0;
                // dp[0][1] = max(dp[-1][1],dp[-2][0]-prices[0]) = max(-infinity,-prices[0]) = -prices[0]
                dp[i][1] = -prices[i];
                continue;
            }
            // 处理正常情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if (i-1 ==0){
                // 处理 i=1 的买入情况，需要取前两天的卖出情况
                // dp[1][1] = max(dp[0][1],dp[-1][0]-prices[1]) = max(-prices[i-1],-prices[i])
                dp[i][1] = Math.max(-prices[i-1], - prices[i]);
            }else{
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
        }
        return dp[len - 1][0];
    }

    /**
     * 当 k = 无穷时，且每次买入需要交一定的【手续费】
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]-fee)
     * <p>
     * 由于k为无穷，k-1也是无穷，所以 k 并不影响选择，可以化简。
     */
    public int maxProfit_with_fee(int[] prices,int fee) {
        int len = prices.length;
        // 定义二维数组，0表示未持有，1表示持有
        int[][] dp = new int[len][2];
        // 由于索引 -1 并不存在，所以base case只能放在【状态】遍历中去处理
        for (int i = 0; i < len; i++) {
            // 处理 -1 情况
            if (i - 1 == -1) {
                // dp[0][0] = max(dp[-1][0], dp[-1][1]+prices[0]) = max(0,-infinity+prices[0]) = 0
                dp[i][0] = 0;
                // dp[0][1] = max(dp[-1][1],dp[-1][0]-prices[0]-fee) = max(-infinity,-prices[0]) = -prices[0]-fee
                dp[i][1] = -prices[i]-fee;
                continue;
            }
            // 处理正常情况
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]-fee);
        }
        return dp[len - 1][0];
    }

}
