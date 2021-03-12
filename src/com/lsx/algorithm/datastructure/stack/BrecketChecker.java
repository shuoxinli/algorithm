package com.lsx.algorithm.datastructure.stack;
/*
 * 利用栈的后进先出来实现分隔符的匹配。
 * 例子：a{b(c[d]e)f}
 */
public class BrecketChecker {

	private String input; //存储待检查的字符串
	
	//构造函数
	public BrecketChecker(String in) {
		this.input = in;
	}
	
	//检查分隔符匹配的方法
	public void check() {
		int strLength = input.length();
		//定义一个栈
		Stack stack = new Stack(strLength);
		
		//遍历存储字符串
		for(int i=0;i<strLength;i++) {
			//获取每个字符
			char ch = input.charAt(i);
			System.out.println(ch);
			switch(ch) {
				case '{':
				case '[':
				case '(':
					//如果是左分隔符，直接进栈
					stack.push(ch);
					break;
				case '}':
				case ']':
				case ')':
					//如果是右分隔符，与栈顶元素进行匹配
					if(!stack.isEmpty()) {
						char chx = (char) stack.pop();
						if((chx=='{' && ch!='}')||(chx=='[' && ch!=']')||(chx=='(' && ch!=')')) {
							System.out.println("匹配出错！");
							return;
						}
					}else {
						System.out.println("匹配出错！");
					}
				default:
					break;
			}
		}
		if(stack.isEmpty()) {
			System.out.println("匹配成功");
		}else {
			System.out.println("匹配出错，有括号没有关闭！");
		}
	}
	
	public static void main(String[] args) {
		BrecketChecker bc = new BrecketChecker("a[b(c[d]e]f}");
		bc.check();
	}
}
