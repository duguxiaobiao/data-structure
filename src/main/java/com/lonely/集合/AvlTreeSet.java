package com.lonely.集合;

import com.lonely.树.AVL树.AVLTree;

/**
 * @author ztkj-hzb
 * @Date 2019/8/13 10:16
 * @Description 基于avl树的集合
 */
public class AvlTreeSet<T extends Comparable<T>> implements Set<T> {

    private AVLTree<T> avlTree;

    public AvlTreeSet() {
        this.avlTree = new AVLTree<>();
    }

    /**
     * 添加元素
     * @param t
     */
    @Override
    public void add(T t) {
        this.avlTree.add(t);
    }

    /**
     * 删除元素
     * @param t
     */
    @Override
    public void remove(T t) {
        this.avlTree.remove(t);
    }

    /**
     * 判断是否存在某元素
     * @param t
     * @return
     */
    @Override
    public boolean container(T t) {
        return this.avlTree.container(t);
    }

    /**
     * 返回集合长度
     * @return
     */
    @Override
    public int getSize() {
        return this.avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.avlTree.isEmpty();
    }
}
