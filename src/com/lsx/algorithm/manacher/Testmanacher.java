package com.lsx.algorithm.manacher;
/*
 * 求在一个字符串后面最少添加多少个字符，使得整个字符串为回文串。
 * 利用manacher算法，求最右边界为原字符串的最后一个字符时，得到中心C，然后
 * 用整个串长度 - pArr[i]回文半径，即得到不在该回文串的最开始的字符，将其逆序补到末尾，
 * 整个就是回文串了。
 * 例子：abc12321  包含最后一个字符的最大回文子串为12321 则在末尾添加前缀abc的逆序即可。
 */
public class Testmanacher {

	//添加虚轴
	public static char[] manacher(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length()*2+1];
		int index = 0;
		for(int i=0;i!=res.length;i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}
	
	//获得包含最后一个字符的最大回文子串的前缀
	public static int getPreLength(String str) {
		if(str == null || str.length()==0) {
			return 0;
		}
		//添加虚轴
		char[] charArr = manacher(str);
		//存放每个位置的回文半径
		int[] pArr = new int[charArr.length];
		//初始化最右边界和中心
		int c = -1;
		int R = -1;
		int preLen = 0;
		System.out.println(charArr.length);
		for(int i=0;i!=charArr.length;i++) {
			pArr[i] = R > i ? Math.min(pArr[2 * c - i], R - i) : 1;
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) {
					pArr[i]++;
				} else {
					break;
				}
			}
			if (i + pArr[i] > R) {
				R = i + pArr[i];
				c = i;
			}
			//判断R是在最后一个字符的右边
			if(R == charArr.length) {
				preLen = (charArr.length-pArr[i]*2)/2+1;
				break;
			}
		}
		return preLen;
	}
	
	public static void main(String[] args) {
		String str = "abc12321dc";
		int preLength = getPreLength(str);
		System.out.println(preLength);
	}
}
