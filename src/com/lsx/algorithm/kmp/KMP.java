package com.lsx.algorithm.kmp;
/*
 * KMP算法：用来求一个字符串是否包含另一个字符串
 */
public class KMP {

	//求str2的next数组，即每个字符的前缀和后缀的最大匹配长度
	private static int[] getNextArray(char[] str2) {
//		if(str2.length==1) {
//			return new int[] {-1};
//		}
//		//规定第一个为-1，第二个为0
//		int[] next = new int[str2.length];
//		next[0]=-1;
//		next[1]=0;
//		int i=2; //移动指针从2开始
//		int cn=0; //最大匹配长度；
//		while(i<next.length) {
//			if(str2[i-1]==str2[cn]) {
//				//第i个要取决于第i-1个和第cn个的比较
//				next[i++] = ++cn;
//			}else if(cn>0){
//				cn = next[cn];
//			}else {
//				next[i++]=0;  //找不到匹配的
//			}
//		}
//		return next;
		
		if(str2.length == 1) {
			return new int[] {-1};
		}
		int[] next = new int[str2.length];
		next[0] = -1;
		next[1] = 0;
		int cn = 0;
		int i=2;
		while(i<str2.length) {
			if(str2[i-1] == str2[cn]) {
				next[i++] = ++cn;
			}else if(cn > 0) {
				cn = next[cn];
			}else {
				next[i++] = 0;
			}
		}
		return next;
	}
	
	//获得str2在str1的起始位置
	public static int getIndexOf(String s1,String s2) {
		/*if(s1 == null || s2 == null || s2.length() < 1 || s1.length() < s2.length()) {
			return -1;
		}
		//转为字符数组
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int i1=0;
		int i2=0;
		//获得str2的next数组
		int[] next = getNextArray(str2);
		while(i1<str1.length && i2<str2.length) {
			if(str1[i1]==str2[i2]) {
				i1++;
				i2++;
			}else if(next[i2]==-1) { //则str1和str2的第一个都不匹配，移动str1
				i1++;  //前缀都没有与之匹配，舍弃i1这个字符
			}else {
				i2 = next[i2];  //令i2=j，移动str2前缀距离
			}
		}
		return i2==str2.length ? i1-i2 : -1;*/
		
		if(s1 == null || s2 == null || s1.length() == 0 || s1.length() < s2.length()) {
			return -1;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		int[] next = getNextArray(str2);
		int i1=0,i2=0;
		while(i1<str1.length && i2<str2.length) {
			if(str1[i1] == str2[i2]) {
				i1++;
				i2++;
			}else if(next[i2] == -1) {
				i1++;
			}else {
				i2 = next[i2];
			}
		}
		return i2 == str2.length ? i1-i2 : -1;
	}
	
	public static void main(String[] args) {
		String s1 = "abcabck";
		String s2 = "cabc";
		int index = getIndexOf(s1, s2);
		System.out.println(index);
	}
}
