package com.lsx.algorithm.dp.pack01;
/*
 * 题目归属：动态规划，完全背包问题，允许重复拿
 * 题目：给一些面额的硬币，要求用这些硬币来组成给定面额的钱数，并且使得硬币数量最少。硬币可以重复使用。
 * 例子：coins=[1,2,5] amount=11 return 3(5+5+1)
 * 思路：画表格！！横坐标表示容量，纵坐标表示当前物品的质量，要初始化第一行和第一列！
 * 		完全背包问题只需要将0-1背包问题的逆序遍历dp数组改为正序即可。
 */
public class Coin {

	public int coinChange(int[] coins,int amount) {
		if(amount==0 || coins==null || coins.length==0) {
			return 0;
		}
		int[] dp = new int[amount+1];
		for(int coin : coins) {
			for(int i=coin;i<=amount;i++) {//正序遍历
				if(i==coin) {
					dp[i]=1; //拿取
				}else if(dp[i]==0 && dp[i-coin]!=0) {
					dp[i] = dp[i-coin]+1;
				}else if(dp[i-coin]!=0) {
					dp[i] = Math.min(dp[i], dp[i-coin]+1); //拿这个和不拿的大小
				}
			}
		}
		return dp[amount]==0 ? -1:dp[amount]; //如果为0，则表示没有组成该面值的
	}
	
	public static void main(String[] args) {
		int[] coins = {1,2,5};
		int amount=8;
		int coinChange = new Coin().coinChange(coins, amount);
		System.out.println(coinChange);
	}
}
