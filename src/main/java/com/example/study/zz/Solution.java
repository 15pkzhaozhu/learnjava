package com.example.study.zz;


import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {

    public static ListNode mergeKLists(ListNode[] lists) {
        // PriorityQueue 是Java中的一个优先队列数据结构。优先队列是一种特殊的队列，其中的元素按照优先级被逐个移出队列，
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        // 遍历lists，将每个节点添加到minHeap中
        for (ListNode node : lists)  {
            if (node != null) {
                //
                minHeap.offer(node);
            }
        }

        // 创建一个虚拟头节点,先初始化一个值为-1的dummy节点，并将 current 指针指向 dummy。
        ListNode dummy = new ListNode(-1), current = dummy;
        // 当minHeap不为空时，从minHeap中取出一个节点，并将该节点与虚拟头节点连接起来
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            // 通过将新的节点插入到 current.next，并将 current 指针移动到新插入的节点
            current.next = node;
            current = current.next;
            // 如果取出的节点下一个节点不为空，将该节点下一个节点添加到minHeap中
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }

        // 返回虚拟头节点的下一个节点
        return dummy.next;
    }

    public static void main(String[] args) {
        //测试
        ListNode l1 = new ListNode(1),
                l3 = new ListNode(3),
                l5 = new ListNode(5);
        l1.next = l3;
        l3.next = l5;

        ListNode l2 = new ListNode(2),
                l4 = new ListNode(4),
                l6 = new ListNode(6);
        l2.next = l4;
        l4.next = l6;

        ListNode[] lists = {l1, l2};

        ListNode result = mergeKLists(lists);
    }
}
