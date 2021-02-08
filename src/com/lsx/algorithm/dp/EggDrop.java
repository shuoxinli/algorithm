package com.lsx.algorithm.dp;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @Author LiShuoXin
 * @Date 2021/1/27 19:31
 * <p>
 * 动态规划之：高楼扔鸡蛋
 * 你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
 * <p>
 * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
 * <p>
 * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
 * <p>
 * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
 * <p>
 * 你的目标是确切地知道 F 的值是多少。
 * <p>
 * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
 */
public class EggDrop {

    /**
     * 【最坏情况】：递归到最后才知道。
     * 【至少移动】：多种方法能得到 F 值，最少那次操作。
     * <p>
     * 动规：
     * 【状态】：K个鸡蛋，N层楼
     * 【选择】：选择在哪层楼扔鸡蛋。
     * 【状态转移】：在i层扔鸡蛋，
     * 鸡蛋碎了，楼层搜索范围从[1,N]缩小为 [1,i-1]，鸡蛋数K减 1
     * 鸡蛋没碎，楼层搜索范围从[1,N]缩小为 [i+1,N]，鸡蛋数K不变
     * 取最坏情况，碎没碎，取结果数最大 + 1
     * <p>
     * 【base case】：
     * K == 1，移动数为 N，只能一层一层试
     * N == 0，移动数为0
     */
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return dp(K, N, dp);
    }

    /**
     * 线性搜索：一层一层搜
     * 算法复杂度：O(KN^2)
     */
    public int dp(int K, int N, int[][] dp) {
        if (K == 1) {
            return N;
        }
        if (N == 0) {
            return 0;
        }
        if (dp[K][N] != 0) {
            return dp[K][N];
        }
        int res = Integer.MAX_VALUE;
        // 一层一层试
        for (int i = 1; i <= N; i++) {
            res = Math.min(res,
                    Math.max(
                            dp(K, N - i, dp),
                            dp(K - 1, i - 1, dp)
                    ) + 1);
        }
        dp[K][N] = res;
        return res;
    }

    /**
     * 使用二分搜索加速查找交点
     *
     * @param K  鸡蛋数
     * @param N  楼层数
     * @param dp
     * @return
     */
    public int dp2(int K, int N, int[][] dp) {
        if (K == 1) {
            return N;
        }
        if (N == 0) {
            return 0;
        }
        if (dp[K][N] != 0) {
            return dp[K][N];
        }
        int res = Integer.MAX_VALUE;
        int l = 1, h = N;
        while (l <= h) {
            // 取中点
            int mid = (l + h) / 2;
            // 碎的结果，找中点前面
            int broken = dp2(K - 1, mid - 1, dp);
            // 没碎的结果，找中点后面
            int not_broken = dp2(K, N - mid, dp);
            // 取最坏结果，即最大
            if (broken > not_broken) {
                // 碎大于没碎，说明交点在中点前,前移
                h = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                // 否则，说明交点在中点后面，后移，并取最坏结果
                l = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }
        // 记录K和N时的结果，并返回
        dp[K][N] = res;
        return res;
    }

    /**
     * 重定义状态转移方程：
     * dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1
     * <p>
     * 1. 无论你在哪层楼扔鸡蛋，鸡蛋只可能碎或没碎，碎了测楼下，没碎则测楼上。
     * 2. 无论你上楼还是下楼，总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前楼层）。
     * <p>
     * dp[k][m - 1] 就是楼上的楼层数，因为鸡蛋个数 k 不变，也就是鸡蛋没 碎，扔鸡蛋次数 m 减⼀；
     * <p>
     * dp[k - 1][m - 1] 就是楼下的楼层数，因为鸡蛋个数 k 减⼀，也就是鸡 蛋碎了，同时扔鸡蛋次数 m 减⼀。
     */
    public int drop(int K, int N) {
        // m 最多不会超过 N 次（线性扫描）
        int[][] dp = new int[K + 1][N + 1];
        // base case:
        // dp[0][..] = 0
        // dp[..][0] = 0
        // Java 默认初始化数组都为 0
        int m = 0;
        while (dp[K][m] < N) {
            m++; // 上界次数不断累加
            for (int k = 1; k <= K; k++)
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
        }
        // 最后得到的就是最坏情况扔鸡蛋的上界次数
        return m;
    }
}
