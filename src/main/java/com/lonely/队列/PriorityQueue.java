package com.lonely.队列;

import com.lonely.堆.MaxHeap;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/7/19 16:02
 * @Description 优先队列，基于 二叉堆数据结构实现
 */
public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {


    private MaxHeap<T> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    /**
     * 入队
     *
     * @param t
     */
    @Override
    public void enqueue(T t) {
        this.maxHeap.add(t);
    }

    /**
     * 出队
     *
     * @return
     */
    @Override
    public T dequeue() {
        return this.maxHeap.extraceMax();
    }

    /**
     * 获取队首的值
     *
     * @return
     */
    @Override
    public T getFront() {
        return this.maxHeap.findMax();
    }

    /**
     * 获取队列长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.maxHeap.getSize();
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.maxHeap.isEmpty();
    }

    @Override
    public int getCapcity() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("***************PriorityQueue[");
        message.append(this.maxHeap.toString());
        message.append("]***************");
        return message.toString();
    }
}
