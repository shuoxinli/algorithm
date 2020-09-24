package com.lsx.algorithm.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 *
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 */
public class MorrisTree {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 最简单的做法：
     *      因为二叉搜索树的中序遍历就是一个有序的数组，扫描一遍数组，用curCount和maxCount统计当前元素的次数，和最多元素的次数
     * 这里利用了数组来存储元素，需要额外空间O（n）
     *
     * 优化：利用Morris遍历，把节点的一些没用的指针充分利用起来，达到遍历空间为O（1）
     * Morris 中序遍历的一个重要步骤就是寻找当前节点的前驱节点，并且 Morris 中序遍历寻找下一个点始终是通过转移到 right 指针指向的位置来完成的。
     *
     * 1，如果当前节点没有左子树，则遍历这个点，然后跳转到当前节点的右子树。
     * 2，如果当前节点有左子树，那么它的前驱节点一定在左子树上，我们可以在左子树上一直向右行走，找到当前点的前驱节点。
     *      - 如果前驱节点没有右子树，就将前驱节点的 right 指针指向当前节点。
     *        这一步是为了在遍历完前驱节点后能找到前驱节点的后继，也就是当前节点。
     *      - 如果前驱节点的右子树为当前节点，说明前驱节点已经被遍历过并被修改了 right 指针，
     *        这个时候我们重新将前驱的右孩子设置为空，遍历当前的点，然后跳转到当前节点的右子树。
     */

    int curVal,count,maxCount;
    List<Integer> answer = new ArrayList<>();

    public int[] findMode(TreeNode root) {
        TreeNode cur = root;
        TreeNode pre = null; // 前驱指针

        while (cur!=null){
            // 如果当前节点没有左子树，直接跳到右子树
            if (cur.left==null){
                // 遍历当前节点
                update(cur.val);
                cur = cur.right;
                continue;
            }
            // 如果当前节点有左子树，找到它的前驱节点
            pre = cur.left;
            // 一直向右走，且最右节点的right指针为空，并没被指向过当前节点
            while(pre.right!=null && pre.right!=cur){
                pre = pre.right;
            }
            // 如果最右节点的right指针为空，没有标记过，则修改指向当前节点，为它的前驱节点
            if (pre.right == null){
                pre.right = cur;
                cur = cur.left;
            }else{
                //否则，已经被标记过了，则遍历当前节点，并恢复前驱节点的right指针为null，跳到当前节点的右子树
                update(cur.val);
                pre.right = null;
                cur = cur.right;
            }
        }

        //将answer结果返回
        Integer[] res = answer.stream().toArray(Integer[]::new);
        // Integer[] 转 int[]
        int[] result = Arrays.stream(res).mapToInt(Integer::intValue).toArray();
        return result;
    }

    //统计当前元素的个数以及更新元素的最大次数
    public void update(int x){
        if (curVal == x){
            count++;
        }else{
            curVal = x;
            count=1;
        }
        //如果当前次数 == 最大次数，先加入进来
        if (count == maxCount){
            answer.add(curVal);
        }
        //当前次数 已超过最大次数，清空列表中的元素，只剩下当前元素，并更新最大次数
        if (count > maxCount){
            answer.clear();
            answer.add(curVal);
            maxCount = count;
        }
    }


}
