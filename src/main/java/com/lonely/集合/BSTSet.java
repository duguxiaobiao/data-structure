package com.lonely.集合;

import com.lonely.树.二分搜索树.BinarySearchTree;

/**
 * @author ztkj-hzb
 * @Date 2019/7/15 9:57
 * @Description 基于二分搜索树(忽略重复元素)实现的集合
 */
public class BSTSet<T extends Comparable<T>> implements Set<T> {

    /**
     * 二分搜索树
     */
    private BinarySearchTree<T> tree;


    public BSTSet() {
        this.tree = new BinarySearchTree<>();
    }

    /**
     * 添加元素
     *
     * @param t
     */
    @Override
    public void add(T t) {
        this.tree.add(t);
    }

    /**
     * 删除元素
     *
     * @param t
     */
    @Override
    public void remove(T t) {
        this.tree.remove(t);
    }

    /**
     * 判断是否存在指定元素
     *
     * @param t
     * @return
     */
    @Override
    public boolean container(T t) {
        return this.tree.container(t);
    }

    /**
     * 返回集合长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.tree.getSize();
    }

    /**
     * 判断集合中是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.tree.isEmpty();
    }


    @Override
    public String toString() {
        return this.tree.toString();
    }
}
