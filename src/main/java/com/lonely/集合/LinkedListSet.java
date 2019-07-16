package com.lonely.集合;

import com.lonely.链表.LinkedList;

/**
 * @author ztkj-hzb
 * @Date 2019/7/15 10:36
 * @Description 基于链表实现的集合处理
 */
public class LinkedListSet<T> implements Set<T> {


    private LinkedList<T> linkedList;

    public LinkedListSet() {
        this.linkedList = new LinkedList<>();
    }

    /**
     * 添加元素
     *
     * @param t
     */
    @Override
    public void add(T t) {
        if (!this.linkedList.container(t)) {
            this.linkedList.addFirst(t);
        }
    }

    /**
     * 删除元素
     *
     * @param t
     */
    @Override
    public void remove(T t) {
        this.linkedList.removeElementByFirst(t);
    }

    /**
     * 判断指定元素是否存在集合中
     *
     * @param t
     * @return
     */
    @Override
    public boolean container(T t) {
        return this.linkedList.container(t);
    }

    /**
     * 获取当前集合中的长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.linkedList.getSize();
    }

    /**
     * 判断当前集合是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return this.linkedList.toString();
    }
}
