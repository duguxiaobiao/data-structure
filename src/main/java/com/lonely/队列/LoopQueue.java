package com.lonely.队列;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 15:46
 * @Description 循环队列
 */
public class LoopQueue<T> implements Queue<T> {

    /**
     * 队列数据
     */
    private T[] datas;

    /**
     * 开始的指针
     */
    private int front;

    /**
     * 末尾的下一个的指针
     */
    private int tail;

    /**
     * 队列中实际长度
     */
    private int size;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capcity) {
        //故意多留一格，是用于区分 front == tail 是相等还是满了的情况
        this.datas = (T[]) new Object[capcity + 1];
        front = tail = size = 0;
    }

    /**
     * 入队
     *
     * @param t
     */
    @Override
    public void enqueue(T t) {
        //判断是否已经满了
        if (this.front == (this.tail + 1) % this.datas.length) {
            //队列已满,扩容
            resize(2 * getCapcity());
        }

        //赋值
        this.datas[tail] = t;
        this.tail = (tail + 1) % this.datas.length;
        size++;

    }


    /**
     * 出队
     *
     * @return
     */
    @Override
    public T dequeue() {

        //判断是否没有数据了
        if (this.isEmpty()) {
            throw new RuntimeException("都没数据了，没法出队!");
        }

        //将队首的指针向后移动
        T frontData = this.datas[front];
        front = (front + 1) % this.datas.length;
        size--;

        //判断是否需要缩容
        if (getSize() <= getCapcity() / 2) {
            resize(getCapcity() / 2);
        }

        return frontData;
    }

    /**
     * 获取队首的数据
     *
     * @return
     */
    @Override
    public T getFront() {
        return this.datas[front];
    }

    /**
     * 获取队列实际长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 格式化输出
     *
     * @return
     */
    @Override
    public String toString() {

        StringBuilder message = new StringBuilder("【LoopQueue front{");

        for (int i = 0; i < getSize(); i++) {
            T t = this.datas[(front + i) % this.datas.length];
            message.append(t);
            if (i != getSize() - 1) {
                message.append(",");
            }
        }
        message.append("}tail】");
        return message.toString();
    }

    /**
     * 扩容
     *
     * @param newCapcity
     */
    private void resize(int newCapcity) {
        T[] newDatas = (T[]) new Object[newCapcity + 1];
        for (int i = 0; i < getSize(); i++) {
            newDatas[i] = this.datas[(front + i) % this.datas.length];
        }
        this.datas = newDatas;
        this.front = 0;
        this.tail = getSize();
    }


    /**
     * 获取实际容量
     *
     * @return
     */
    @Override
    public int getCapcity() {
        return this.datas.length - 1;
    }
}
