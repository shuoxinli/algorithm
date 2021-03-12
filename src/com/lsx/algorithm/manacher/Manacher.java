package com.lsx.algorithm.manacher;
/*
 * Manacher算法：求一个字符串中含有最长的回文子串
 */
public class Manacher {

	//给字符串添加虚轴
	public static char[] manacherString(String str) {
		/*char[] charArr = str.toCharArray();
		char[] res = new char[str.length()*2+1]; // #1#2#3#
		int index = 0;
		for(int i=0;i!=res.length;i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++]; //偶索引放# 奇索引放字符
		}
		return res;*/
		
		char[] charArr = str.toCharArray();
		char[] res = new char[charArr.length*2+1];
		int index = 0;
		for(int i=0;i<res.length;i++) {
			res[i] = (i&1) == 0 ?'#':charArr[index++];
		}
		return res;
	}
	
	//求最长的回文子串
	public static int maxLcpsLength(String str) {
		/*if(str == null || str.length()==0) {
			return 0;
		}
		//为字符串添加虚轴，解决偶数个问题
		char[] charArr = manacherString(str);
		//定一个数组存储每个索引位置i的最大回文串半径，即最右边界
		int[] pArr = new int[charArr.length];
		//最右边界的中心
		int c = -1;
		//最右边界
		int R = -1;
		int max = Integer.MIN_VALUE;  //存pArr中的最大，即整个字符串的最长回文子串
		for(int i=0;i!=charArr.length;i++) {
			//分为两大类，i在R的外面,半径取1，i在R的里面，取i'的半径或R-i
			pArr[i] = R>i ? Math.min(charArr[2*c-i], R-i) : 1;
			//4种情况都进来,左右边界不越界
			while(i+pArr[i] < charArr.length && i-pArr[i] > -1) {
				if(charArr[i+pArr[i]] == charArr[i-pArr[i]]) {
					//第1和4种情况，R才有可能移动，半径加1
					//第1种i在R的外面，R前移
					//第4种i'的半径刚好在左边界L上，R可能移动，i的半径可能+1
					pArr[i]++;
				}else {
					//第2种i'的回文半径在左边界L里面，i的半径=i'的半径
					//第3种i'的回文半径在左边界L外面，i的半径=R-i
					break; //直接跳出
				}
			}
			//如果大于R，更新最右边界和中心
			if(i+pArr[i] > R) {
				R = i+pArr[i];
				c = i;
			}
			//更新整个字符串的最长回文
			max = Math.max(max, pArr[i]);
		}
		return max-1;*/
		
		if(str == null || str.length() ==0) {
			return 0;
		}
		char[] charArr = manacherString(str);
		int R = -1;
		int c = -1;
		int[] pArr = new int[charArr.length];
		int maxlen = Integer.MIN_VALUE;
		for(int i=0;i<charArr.length;i++) {
			pArr[i] = i<R ? Math.max(R-i, pArr[2*c-i]) : 1;
			while(i+pArr[i] < charArr.length && i-pArr[i] > -1) {
				if(charArr[i+pArr[i]] == charArr[i-pArr[i]]) {
					++pArr[i];
				}else {
					break;
				}
			}
			if(i+pArr[i] > R) {
				R = i+pArr[i];
				c = i;
			}
			maxlen = Math.max(maxlen, pArr[i]);
		}
		return maxlen-1;
	}
	
	public static void main(String[] args) {
		String str = "1231320";
		int maxLcpsLength = maxLcpsLength(str);
		System.out.println(maxLcpsLength);
	}
}
