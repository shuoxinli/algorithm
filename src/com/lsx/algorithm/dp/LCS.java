package com.lsx.algorithm.dp;
/*
 * 题目归属：动态规划
 * 
 * dp就是要列网格来算，遍历所有网格！利用前一步得到的数据，来填这一步的dp。
 * 
 * 题目：求两个序列的最长公共子序列。
 * 解析：定义一个二维数组dp用来存储最长公共子序列的长度，d[i][j]代表S1的前i个字符和S2的前j个字符
 * 		的最长公共子序列的长度，不一定包含S1i或者S2j：
 * 		当S1i == S2j时，dp[i][j] = dp[i-1][j-1]+1  (1代表最后的S1i)
 * 		当S1i != S2j时，dp[i][j] = max{ dp[i-1][j], dp[i][j-1] },代表S1i前进一格或S2j前进一格的最大长度。
 */
public class LCS {

	public static int lengthOfLCS(int[] nums1,int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		int[][] dp = new int[n1+1][n2+1]; //最后的索引n1 n2要存nums1 和nums2最后一个的最长子串
		//从第二个开始遍历，因为每次都要看前一个，最后要到n1 n2 看两个num的最后一个
		for(int i=1;i<=n1;i++) {
			for(int j=1;j<=n2;j++) {
				if(nums1[i-1] == nums2[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); //两者前移一个的最大
				}
			}
		}
		return dp[n1][n2];
	}
	
	/*public static int lengthOfLCS(int[] nums1,int[] nums2) {
		int n1 = nums1.length;
		int n2 = nums2.length;
		int[][] dp = new int[n1+1][n2+1];
		for(int i=1;i<n1;i++) {
			for(int j=1;j<n2;j++) {
				if(nums1[i-1] == nums2[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[n1][n2];
	}*/
	
	public static void main(String[] args) {
		int[] nums1 = {'a','f','e','b','q'};
		int[] nums2 = {'a','e','b','o','q'};
		int lengthOfLCS = lengthOfLCS(nums1, nums2);
		System.out.println(lengthOfLCS);
	}
}
