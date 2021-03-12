package com.lsx.algorithm.datastructure.link;

public class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        long a = l1.val;
        l1 = l1.next;
        long i = 10;
        while(l1!=null){
            a = a + i*l1.val;
            i*=10;
            l1 = l1.next;
        }
        
        long b = l2.val;
        l2 = l2.next;
        long j = 10;
        while(l2!=null){
            b = b + j*l2.val;
            j*=10;
            l2 = l2.next;
        }
        
        
        long c = a + b;
        ListNode l3 = new ListNode((int)c%10);
        ListNode p = l3;
        ListNode q;
        ListNode k;
        c = c/10;
        while(c!=0){
            q = new ListNode((int)c%10);
            p.next = q;
            p = q;
            c = c/10;
        }
        p.next = null;
        return l3;
    }

	public static void main(String[] args) {
		System.out.println((long)10000000000l%10);
	}
}
