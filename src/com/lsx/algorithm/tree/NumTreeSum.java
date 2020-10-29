package com.lsx.algorithm.tree;

/**
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 * <p>
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 * <p>
 * 计算从根到叶子节点生成的所有数字之和。
 * <p>
 * 输入: [1,2,3]
 * 1
 * / \
 * 2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 */
public class NumTreeSum {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public int sumNumbers(TreeNode root) {
        return DFS(root,0);
    }

    //深度遍历，i表示root之前所有路径组成的数字
    public int DFS(TreeNode root, int i) {
        if (root == null) {
            return 0;
        }
        //加上当前的root的值，且之前的数字扩大10倍
        int sum = i * 10 + root.val;

        if (root.left == null && root.right == null) {
            //如果当前root为叶子节点，直接返回sum
            return sum;
        }
        //继续遍历其左右节点，之和就是总和
        return DFS(root.left, sum) + DFS(root.right, sum);
    }
}
