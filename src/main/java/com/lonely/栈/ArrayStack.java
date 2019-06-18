package com.lonely.栈;

import com.lonely.数组.MyArray;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 14:54
 * @Description 底层基于数组实现
 */
public class ArrayStack<T> implements Stack<T> {

    /**
     * 基于二次封装的数组实现
     */
    private MyArray<T> myArray;

    public ArrayStack() {
        this(10);
    }

    public ArrayStack(int capcity) {
        this.myArray = new MyArray<>(capcity);
    }


    /**
     * 压栈
     *
     * @param t
     */
    @Override
    public void push(T t) {
        this.myArray.addLast(t);
    }

    /**
     * 查看栈顶的值
     *
     * @return
     */
    @Override
    public T peek() {
        return this.myArray.getLast();
    }

    /**
     * 弹栈
     *
     * @return
     */
    @Override
    public T pop() {
        return this.myArray.removeLast();
    }

    /**
     * 获取栈的长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.myArray.getSize();
    }

    /**
     * 判断栈中是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.myArray.isEmpty();
    }

    /**
     * 格式化输出
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("ArrayStack{");

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
