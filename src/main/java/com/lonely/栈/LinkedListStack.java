package com.lonely.栈;

import com.lonely.链表.LinkedList;

/**
 * @author ztkj-hzb
 * @Date 2019/6/20 10:38
 * @Description 使用链表数据结构实现栈 由于链表结构获取第一个节点方便，刚好栈先进后出，可以在插入数据时，将数据插入到头
 */
public class LinkedListStack<T> implements Stack<T> {

    /**
     * 基于链表底层
     */
    private LinkedList<T> linkedList;

    public LinkedListStack() {
        this.linkedList = new LinkedList<>();
    }

    /**
     * 压栈
     *
     * @param t
     */
    @Override
    public void push(T t) {
        this.linkedList.addFirst(t);
    }

    /**
     * 查看栈顶的值
     *
     * @return
     */
    @Override
    public T peek() {
        return this.linkedList.getFirst();
    }

    /**
     * 出栈
     *
     * @return
     */
    @Override
    public T pop() {
        return this.linkedList.removeFirst();
    }

    /**
     * 获取栈中节点数量
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.linkedList.getSize();
    }

    /**
     * 判断当前栈中是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    @Override
    public String toString() {
        //格式  xxx-->xxxx->xxxx->xxx
        StringBuilder messagae = new StringBuilder("LinkedListStack{");
        messagae.append(this.linkedList);
        messagae.append("}");
        return messagae.toString();
    }
}
