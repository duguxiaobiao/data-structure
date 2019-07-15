package com.lonely.链表;

/**
 * @author ztkj-hzb
 * @Date 2019/6/25 11:47
 * @Description 链表接口
 */
public interface ILinkedList<T> {


    /**
     * 添加元素
     *
     * @param index
     * @param t
     */
    void add(int index, T t);

    /**
     * 查询元素
     *
     * @param index
     * @return
     */
    T get(int index);

    /**
     * 修改元素
     *
     * @param index
     * @param t
     */
    void set(int index, T t);

    /**
     * 删除元素
     *
     * @param index
     * @return
     */
    T remove(int index);

    /**
     * 判断是否包含某元素
     *
     * @param t
     * @return
     */
    boolean container(T t);

    /**
     * 获取链表长度
     *
     * @return
     */
    int getSize();

    /**
     * 判断链表是否为空
     *
     * @return
     */
    boolean isEmpty();
}
