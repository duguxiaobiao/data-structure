package com.lonely.队列;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 15:21
 * @Description 队列 先进先出
 */
public interface Queue<T> {

    /**
     * 入队
     */
    void enqueue(T t);

    /**
     * 出队
     *
     * @return
     */
    T dequeue();

    /**
     * 获取队首的数据
     *
     * @return
     */
    T getFront();

    /**
     * 获取队列中的长度
     *
     * @return
     */
    int getSize();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 获取队列中容量
     *
     * @return
     */
    int getCapcity();
}
