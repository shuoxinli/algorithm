package com.lsx.algorithm.kmp;
/*
 * 给定一个字符串，至少在添加多少个字符，使其至少包含两个原串。
 * 例如：abcabc  return abcabcabc 添加3个
 */
public class Testkmp {

	//获得原串的前缀和后缀的最长匹配长度
	public static int[] getNext(char[] str2) {
		if(str2.length==1) {
			return new int[] {-1};
		}
		int[] next = new int[str2.length+1];
		next[0]=-1;
		next[1]=0;
		int i=2;
		int cn=0;
		while(i<next.length) {
			if(str2[i-1] == str2[cn]) {
				next[i++] = ++cn;
			}else if(next[cn]>0) {
				cn = next[cn];
			}else {
				next[i++]=0;
			}
		}
		return next;
	}
	
	public static void main(String[] args) {
		String str = "abcabcabc";
		char[] str2= str.toCharArray();
		int[] next = getNext(str2);
		//获取整个str2的最长前缀
		int l = next[str2.length];
		//则需要再补上多少字符
		int s = str2.length-l;
		System.out.println(s);
		
	}
}
