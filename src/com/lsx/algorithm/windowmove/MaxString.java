package com.lsx.algorithm.windowmove;
/*
 * 给定一个字符串,请你找出其中不含有重复字符的 最长子串 的长度.
 * 例子：abcabcbb return 3
 * 思想：利用窗口滑动记录每次窗口的最大值，判断重复，用hash思想！
 */
public class MaxString {

	public static int getMaxLengthString(String s) {
		//利用hash思想，来判断元素是否重复
		int[] hash = new int[256];
		//定义左右移动指针
		int L=0;
		int R=0;
		int max=0;  //存最大不重复子串
		while(L<s.length()) {
			//R不超，且没有重复
			if(R<s.length() && hash[s.charAt(R)]==0) {
				//标记，访问过
				hash[s.charAt(R)] = 1;
				R++; //R前移
				//更新最大子串，只有R移动时，才会更新最大值
				max = R-L > max ? (R-L) : max;
			}else if(R == s.length()) {
				//当R到达最右边界时，此时的字符子串为最长，因为接下来L移动只会减少
				break;
			}else {
				//R未到最右边界，标记L的元素过期，L前移
				hash[s.charAt(L)] = 0;
				L++;
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		String s = "sdfhish";
		int maxLengthString = getMaxLengthString(s);
		System.out.println(maxLengthString);
	}
}
