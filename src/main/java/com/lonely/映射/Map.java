package com.lonely.映射;

/**
 * @author ztkj-hzb
 * @Date 2019/7/17 9:54
 * @Description
 */
public interface Map<K, V> {

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * 删除指定key对应的映射
     *
     * @param key
     */
    V remove(K key);

    /**
     * 判断是否存在指定key
     *
     * @param key
     * @return
     */
    boolean container(K key);

    /**
     * 获取指定key对应的value值
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 设置指定key对应的value值
     *
     * @param key
     * @param value
     */
    void set(K key, V value);

    /**
     * 判断当前映射中是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 获取当前key的数量
     *
     * @return
     */
    int getSize();
}
