package com.lonely.堆;

/**
 * @author ztkj-hzb
 * @Date 2019/7/18 15:24
 * @Description 堆, 一般指定的是二叉堆, 跟二分搜索树存在区别， 底层使用动态数据实现
 * <p>
 * 特点：
 * 1. 二叉堆是一个完全二叉树
 * 2. 底层使用动态数组实现
 * 3. 可以根据不同实现快速找到最大值(最小值),针对父节点、左右子节点的索引有特定的公式可以快速查找元素
 */
public interface Heap<T> {

    /**
     * 获取堆长度
     * @return
     */
    int getSize();

    /**
     * 判断堆元素是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 添加元素
     * @param t
     */
    void add(T t);


}
