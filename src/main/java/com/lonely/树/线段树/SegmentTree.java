package com.lonely.树.线段树;

import java.text.MessageFormat;
import java.util.EmptyStackException;
import java.util.Optional;

/**
 * @author ztkj-hzb
 * @Date 2019/7/24 16:41
 * @Description 线段树(区间数) -----基于数组实现
 */
public class SegmentTree<T> {

    //实现线段树的数组
    private T[] segmentTreeArr;

    //传入的数组
    private T[] data;

    private Merger<T> merger;

    public SegmentTree(T[] arr, Merger<T> merger) {
        this.data = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = arr[i];
        }

        this.segmentTreeArr = (T[]) new Object[4 * arr.length];
        this.merger = merger;

        //构建线段树
        this.buildSegmentTree(0, 0, this.data.length - 1);
    }


    /**
     * 构建指定index下的值
     *
     * @param treeIndex
     * @param left
     * @param right
     */
    private void buildSegmentTree(int treeIndex, int left, int right) {
        if (left == right) {
            this.segmentTreeArr[treeIndex] = this.data[left];
            return;
        }

        //左半边的索引信息
        int mid = this.midIndex(left, right);

        //处理左叉树
        int leftIndex = leftChildIndex(treeIndex);
        this.buildSegmentTree(leftIndex, left, mid);

        //处理右叉树
        int rightIndex = rightChildIndex(treeIndex);
        this.buildSegmentTree(rightIndex, mid + 1, right);

        //将左右叉树根据某个函数计算得到当前索引的值
        this.segmentTreeArr[treeIndex] = this.merger.merge(this.segmentTreeArr[leftIndex], this.segmentTreeArr[rightIndex]);
    }

    /**
     * 查询指定索引区间范围的值(具体的操作，由传入的merge方法决定)
     *
     * @param queryLeft
     * @param queryRight
     * @return
     */
    public T query(int queryLeft, int queryRight) {

        //检查索引
        if (queryLeft < 0 || queryLeft >= this.data.length || queryRight < 0 || queryRight >= this.data.length || queryLeft > queryRight) {
            throw new RuntimeException("index error");
        }

        return this.query(0, 0, this.data.length - 1, queryLeft, queryRight);
    }


    private T query(int treeIndex, int left, int right, int queryLeft, int queryRight) {

        if (left == right) {
            //叶子节点，直接返回
            return this.segmentTreeArr[treeIndex];
        }

        int leftChildIndex = this.leftChildIndex(treeIndex);
        int rightChildIndex = this.rightChildIndex(treeIndex);
        int midIndex = this.midIndex(left, right);

        //待查询的最小索引 都 大于 当前的根节点的右叉树，则可以忽略左叉树
        if (queryLeft >= midIndex + 1) {
            return this.query(rightChildIndex, midIndex + 1, right, queryLeft, queryRight);
        } else if (queryRight <= midIndex) {
            //待查询的最大索引 都小于 当前区间的中间部分，则忽略右叉树
            return this.query(leftChildIndex, left, midIndex, queryLeft, queryRight);
        } else {
            //待查询的区间在 指定区间之间

            //处理左叉树的值
            T leftTreeVal = this.query(leftChildIndex, left, midIndex, queryLeft, midIndex);
            //处理右叉树的值
            T rightTreeVal = this.query(rightChildIndex, midIndex + 1, right, midIndex + 1, queryRight);

            //汇总
            return this.merger.merge(leftTreeVal, rightTreeVal);
        }
    }

    /**
     * 修改制定索引位置的值
     *
     * @param index
     * @param t
     */
    public void update(int index, T t) {

        if (index < 0 || index > this.data.length - 1) {
            throw new RuntimeException(MessageFormat.format("入参index：{0}应在范围[{1}~{2}]内", index, 0, this.data.length - 1));
        }

        this.data[index] = t;
        //更新tree信息
        this.update(0, 0, this.data.length - 1, index, t);

    }

    /**
     * 修改指定索引区间中指定索引的值
     *
     * @param treeIndex
     * @param left
     * @param right
     * @param index
     * @param t
     */
    private void update(int treeIndex, int left, int right, int index, T t) {

        if (left == right) {
            //叶子节点
            this.segmentTreeArr[treeIndex] = t;
            return;
        }

        int leftChildIndex = this.leftChildIndex(treeIndex);
        int rightChildIndex = this.rightChildIndex(treeIndex);
        int midIndex = this.midIndex(left, right);

        if (index >= midIndex + 1) {
            //右子树
            this.update(rightChildIndex, midIndex + 1, right, index, t);
        } else {
            //左子树
            this.update(leftChildIndex, left, midIndex, index, t);
        }

        //设置当前index的值
        this.segmentTreeArr[treeIndex] = this.merger.merge(this.segmentTreeArr[leftChildIndex], this.segmentTreeArr[rightChildIndex]);

    }


    public T get(int index) {
        if (index >= this.data.length || index < 0) {
            throw new RuntimeException(MessageFormat.format("index:{0}越界，应该在范围:{1}~{2}之间,请检查！", index, 0, this.data.length - 1));
        }
        return this.data[index];
    }


    public int getSize() {
        return this.data.length;
    }

    /**
     * 获取指定索引的左子树索引
     *
     * @param startIndex
     * @return
     */
    private int leftChildIndex(int startIndex) {
        return 2 * startIndex + 1;
    }

    /**
     * 获取指定索引的右子树索引
     *
     * @param startIndex
     * @return
     */
    private int rightChildIndex(int startIndex) {
        return 2 * startIndex + 2;
    }

    /**
     * 获取左右两个索引的中间索引
     *
     * @param leftIndex
     * @param rightIndex
     * @return
     */
    private int midIndex(int leftIndex, int rightIndex) {
        return leftIndex + (rightIndex - leftIndex) / 2;
    }


    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("SegmentTree:{");
        for (int i = 0; i < this.segmentTreeArr.length; i++) {
            if (this.segmentTreeArr[i] == null) {
                message.append("null");
            } else {
                message.append(this.segmentTreeArr[i]);
            }
            if (i != this.segmentTreeArr.length - 1) {
                message.append(",");
            }
        }
        message.append("}");
        return message.toString();
    }
}
