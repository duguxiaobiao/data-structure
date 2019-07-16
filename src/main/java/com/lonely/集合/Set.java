package com.lonely.集合;

/**
 * @author ztkj-hzb
 * @Date 2019/7/15 9:50
 * @Description 集合----特点：无序，不重复
 */
public interface Set<T> {

    /**
     * 添加元素
     *
     * @param t
     */
    void add(T t);

    /**
     * 删除元素
     *
     * @param t
     */
    void remove(T t);

    /**
     * 判断是否存在某元素
     *
     * @param t
     * @return
     */
    boolean container(T t);

    /**
     * 返回集合长度
     *
     * @return
     */
    int getSize();

    /**
     * 判断当前集合中是否为空
     *
     * @return
     */
    boolean isEmpty();
}
