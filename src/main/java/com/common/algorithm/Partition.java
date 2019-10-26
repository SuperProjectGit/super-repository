package com.common.algorithm;

import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM2;

/**
 * @author super
 * @create 2019-10-26 21:52
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 **/
public class Partition {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode t1 = new ListNode(4);
        head.next = t1;
        ListNode t2 = new ListNode(3);
        t1.next = t2;
        ListNode t3 = new ListNode(2);
        t2.next = t3;
        ListNode t4 = new ListNode(5);
        t3.next = t4;
        ListNode t5 = new ListNode(2);
        t4.next = t5;
        ListNode result = part(head, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode part(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
