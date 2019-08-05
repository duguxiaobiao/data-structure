package com.lonely.树.线段树;

/**
 * @author ztkj-hzb
 * @Date 2019/7/24 19:40
 * @Description 合并接口
 */
public interface Merger<T> {

    /**
     * 将t1 和 t2 经过某种处理合并成 T
     *
     * @param t1
     * @param t2
     * @return
     */
    T merge(T t1, T t2);

}
