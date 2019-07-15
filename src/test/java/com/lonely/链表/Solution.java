package com.lonely.链表;

/**
 * @author ztkj-hzb
 * @Date 2019/6/24 15:09
 * @Description 力扣题库的第203题 删除链表中等于给定值 val 的所有节点。
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeElements3(new ListNode(1),1).val);


    }


    //第一种，正常处理
    public ListNode removeElements(ListNode head, int val) {
        //如果链表中连续开始的值都跟入参val一致，则先删除
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }

        //在中间部分寻找删除
        ListNode preNode = head;
        while (preNode.next != null) {
            if (preNode.next.val == val) {
                //找到了
                preNode.next = preNode.next.next;
            } else {
                //没找到，则下一个
                preNode = preNode.next;
            }
        }
        return head;
    }


    //第二种，使用虚拟头节点
    public ListNode removeElements2(ListNode head, int val) {

        //定义一个虚拟头节点
        ListNode dymnNode = new ListNode(-1);
        dymnNode.next = head;

        //判断节点
        ListNode preNode = dymnNode;
        while (preNode.next != null) {
            if (preNode.next.val == val) {
                preNode.next = preNode.next.next;
            } else {
                //没找到
                preNode = preNode.next;
            }
        }
        return dymnNode.next;
    }

    //第三种，使用递归函数处理
    public ListNode removeElements3(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode currNode = removeElements3(head.next,val);
        if(head.val == val){
            head = currNode;
        }else{
            head.next = currNode;
        }
        return head;
    }




    private static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
}
