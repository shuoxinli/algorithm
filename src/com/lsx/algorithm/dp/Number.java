package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划
 * 题目：给一个数组，找出其中构成等差数列的子串的个数。
 * 例子：A = [0, 1, 2, 3, 4] 
 * 思路：dp[i] 表示以 A[i] 为结尾的等差递增子区间的个数。
 * 当 A[i] - A[i-1] == A[i-1] - A[i-2]，那么 [A[i-2], A[i-1], A[i]] 构成一个等差递增子区间。
 * 而且在以 A[i-1] 为结尾的递增子区间的后面再加上一个 A[i]，一样可以构成新的递增子区间。
 * 分析：
 * dp[2] = 1
    [0, 1, 2]
	dp[3] = dp[2] + 1 = 2
    [0, 1, 2, 3], // [0, 1, 2] 之后加一个 3
    [1, 2, 3]     // 新的递增子区间
	dp[4] = dp[3] + 1 = 3
    [0, 1, 2, 3, 4], // [0, 1, 2, 3] 之后加一个 4
    [1, 2, 3, 4],    // [1, 2, 3] 之后加一个 4
    [2, 3, 4]        // 新的递增子区间
    综上，在 A[i] - A[i-1] == A[i-1] - A[i-2] 时，dp[i] = dp[i-1] + 1。
 */
public class Number {

	//查找等差数列的个数,就是以每个位置元素结束的能构成等差的所有和
	/*public static int numberOfArithmeticSlices(int[] A) {
		if(A == null || A.length==0) {
			return 0;
		}
		int n = A.length;
		//dp[i] 表示以 A[i] 为结尾的等差递增子区间的个数。
		int[] dp = new int[n];
		//从下标为2开始遍历，因为等差数列至少要有三个数
		for(int i=2;i<n;i++) {
			if(A[i]-A[i-1] == A[i-1]-A[i-2]) {
				//判断i-2,i-1,i是否为等差数列
				dp[i] = dp[i-1]+1;  //找规律得出公式
			}
		}
		//遍历dp数组，将所有值加起来就是总的等差数列的个数
		int total = 0;
		for(int count : dp) {
			total += count;
		}
		return total;
	}*/
	
	//1
	public static int numberOfArithmeticSlices(int[] num) {
		if(num == null || num.length == 0) {
			return 0;
		}
		int n = num.length;
		int[] dp = new int[n];
		for(int i=2;i<n;i++) {
			if(num[i]-num[i-1] == num[i-1]-num[i-2]) {
				//如果该位置能构成等差，则在上一个位置i-1的等差个数+1
				dp[i] = dp[i-1] + 1;
			}
		}
		
		int total=0;
		for(int count : dp) {
			total+=count;
		}
		return total;
	}
	
	public static void main(String[] args) {
		int[] A = {0,1,2,3,4};
		int numberOfArithmeticSlices = numberOfArithmeticSlices(A);
		System.out.println(numberOfArithmeticSlices);
	}
}
