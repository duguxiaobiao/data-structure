package com.lonely.栈;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 14:48
 * @Description 栈接口
 */
public interface Stack<T> {

    /**
     * 压栈
     *
     * @param t
     */
    void push(T t);

    /**
     * 获取栈顶的值
     *
     * @return
     */
    T peek();

    /**
     * 弹出栈顶的值
     *
     * @return
     */
    T pop();

    /**
     * 获取栈中实际长度
     *
     * @return
     */
    int getSize();

    /**
     * 判断栈中是否为空
     *
     * @return
     */
    boolean isEmpty();
}
