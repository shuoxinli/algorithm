package com.lsx.algorithm;

public class Mik {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 找到最近的公共祖先：
     * 遍历找到p和q节点的路径，path_p和path_q，然后比对两个路径找到最后一个公共节点
     * <p>
     * 两次遍历寻找路径，最坏结果就是O(n)。
     * 额外空间存储路径，最坏结果也是T(n)。
     * <p>
     * 优化：
     * 一次遍历，同时比较p和q，如果当前节点的值处于p和q之间，则为分叉节点，为最近的公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode ancestor = root;
        while (true) {
            if (ancestor.val < p.val && ancestor.val < q.val) {
                ancestor = ancestor.right;
            } else if (ancestor.val > p.val && ancestor.val > q.val) {
                ancestor = ancestor.left;
            } else {
                // 当前节点为分叉节点
                break;
            }
        }
        return ancestor;
    }

}
