package com.lsx.algorithm.greedy;
/*
 * 题目归属：贪心算法
 * 题目：一次股票的交易，求最大的收益。
 * 思路：记录最小价格，在差价最大的时候卖出。
 * 例子：传入一个价格数组prices[2,4,5,1,6] 最大收益为5
 */
public class Stock1 {
	public static void main(String[] args) {
		int[] prices = {2,7,5,2,6};
		int maxMoney = sellStock(prices);
		System.out.println(maxMoney);
	}
	
	public static int sellStock(int[] prices) {
		int n = prices.length;
		int minPrice = prices[0];//记录最小价格
		int maxPrice = 0;//记录最大价格
		for(int i=0;i<n;i++) {
			if(prices[i] < minPrice) {
				minPrice = prices[i];
			}else {
				maxPrice = Math.max(maxPrice, prices[i]);
			}
		}
		return maxPrice-minPrice;
	}
}
