package com.lsx.algorithm;

import java.util.PriorityQueue;

/**
 * 合并K个有序链表：
 *      给你一个链表数组，每个链表都已经按升序排列。
 *      请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 */
public class KList {

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     *  暴力方法：
     *      两个合并，得到的结果再与另外一个合并，直到合并完k个。
     *  优化：分治算法，对半分
     *
     *  利用优先队列，遍历所有数据，一遍进队，一遍出队，两个遍历。
     *
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }

        int k = lists.length;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0;i<k;i++){
            ListNode node = lists[i];
            while(node!=null){
                priorityQueue.add(node.val);
                node = node.next;
            }
        }

        // 将优先队列中的数据填入链表
        ListNode headNode = new ListNode(-1);
        ListNode cur = headNode;
        while (!priorityQueue.isEmpty()){
            Integer val = priorityQueue.poll();
            cur.next = new ListNode(val);
            cur = cur.next;
        }
        return headNode.next;
    }
}
