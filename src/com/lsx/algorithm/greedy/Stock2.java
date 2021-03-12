package com.lsx.algorithm.greedy;
/*
 * 题目归属：贪心算法
 * 题目：可进行多次股票交易，但不能交叉交易，求最大收益
 * 思路：从头到尾遍历，只要与前一个的差大于0就计入收益中。贪心就是做稳赚不赔的生意。
 * 例子:：prices[2,5,6,1] 5-2=3>0计入 6-5=1>0 计入 1-6<0不计入
 */
public class Stock2 {
	public static void main(String[] args) {
		int[] prices = {2,7,6,9};
		int maxMoney = sellStock(prices);
		System.out.println(maxMoney);
	}
	
	public static int sellStock(int[] prices) {
		int maxMoney = 0; //存最大收益
		for(int i=1;i<prices.length;i++) {
			if(prices[i]-prices[i-1]>0) {
				maxMoney += prices[i] - prices[i-1];
			}
		}
		return maxMoney;
	}
}
