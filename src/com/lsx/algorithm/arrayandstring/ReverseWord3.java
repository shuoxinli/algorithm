package com.lsx.algorithm.arrayandstring;

import java.util.Stack;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 */
public class ReverseWord3 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (i == len-1){
                sb.append(s.charAt(i));
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }else if (s.charAt(i) == ' ') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(s.charAt(i));
            }else{
                stack.push(s.charAt(i));
            }
        }
        return sb.toString();
    }

}
