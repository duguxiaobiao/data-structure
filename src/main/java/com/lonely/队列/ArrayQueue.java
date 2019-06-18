package com.lonely.队列;

import com.lonely.数组.MyArray;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 15:27
 * @Description 基于数组实现的队列, 每次出队后，需要将后续的所有元素移动位置，数据量大的情况下很影响性能
 */
public class ArrayQueue<T> implements Queue<T> {

    /**
     * 基于二次封装数组实现
     */
    private MyArray<T> myArray;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capcity) {
        this.myArray = new MyArray<>(capcity);
    }

    /**
     * 入队
     */
    @Override
    public void enqueue(T t) {
        this.myArray.addLast(t);
    }

    /**
     * 出队
     *
     * @return
     */
    @Override
    public T dequeue() {
        return this.myArray.removeFirst();
    }


    /**
     * 获取队首数据
     *
     * @return
     */
    @Override
    public T getFront() {
        return this.myArray.get(0);
    }

    /**
     * 获取队列长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.myArray.getSize();
    }

    /**
     * 判断队列长度是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.myArray.isEmpty();
    }

    /**
     * 获取队列的实际容量
     *
     * @return
     */
    @Override
    public int getCapcity() {
        return this.myArray.getCapcity();
    }


    /**
     * 格式化输出
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("ArrayQueue{");

        for (int i = 0; i < this.myArray.getSize(); i++) {
            if (i == this.myArray.getSize() - 1) {
                //最后一个
                message.append(this.myArray.get(i));
            } else {
                message.append(this.myArray.get(i)).append(",");
            }
        }

        message.append("}");
        return message.toString();
    }
}
