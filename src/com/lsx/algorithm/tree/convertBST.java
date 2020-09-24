package com.lsx.algorithm.tree;

import java.util.Stack;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * <p>
 * 例子
 * 输入: 原始二叉搜索树:
 * 5
 * /   \
 * 2     13
 * <p>
 * 输出: 转换为累加树:
 * 18
 * /   \
 * 20     13
 */
public class convertBST {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode convertBSTToCT(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode node = root;
        // 循环逆向中序遍历，从右到左
        Stack<TreeNode> stack = new Stack<>();
        // 定义一个累加和
        int sum = 0;
        while (node != null || !stack.isEmpty()) {
            // 如果节点不为空，不断向右深入
            while (node != null) {
                stack.push(node);
                node = node.right;
            }
            // 到达最右，输出
            node = stack.pop();
            // 该节点加上当前累加和，且更新累加和加上该节点原先的值
            int temp = node.val;
            node.val += sum;
            sum += temp;

            // 向左走
            node = node.left;
        }
        return root;
    }
}
