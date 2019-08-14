package com.lonely.哈希表;

import java.util.TreeMap;

/**
 * @author ztkj-hzb
 * @Date 2019/8/14 15:08
 * @Description hash表实现 基于 java底层的红黑树实现
 */
public class HashTable<K extends Comparable<K>, V> {

    /**
     * 每个索引对应的红黑树
     */
    private TreeMap<K, V>[] treeMaps;

    /**
     * 素数
     */
    private int m;

    /**
     * hash表中元素大小
     */
    private int size;

    public HashTable(int m) {
        this.m = m;
        this.size = 0;
        treeMaps = new TreeMap[m];
        for (int i = 0; i < m; i++) {
            treeMaps[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }


    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     * 添加元素
     *
     * @param k
     * @param v
     */
    public void put(K k, V v) {
        //获取指定key的索引值
        TreeMap<K, V> treeMap = this.getTreeMapByKey(k);
        if (treeMap.containsKey(k)) {
            treeMap.put(k, v);
        } else {
            treeMap.put(k, v);
            this.size++;
        }
    }

    /**
     * 更新指定key的值
     *
     * @param k
     * @param v
     */
    public void set(K k, V v) {
        //获取指定key的索引值
        TreeMap<K, V> treeMap = this.getTreeMapByKey(k);
        if (!treeMap.containsKey(k)) {
            throw new RuntimeException("不存在k对应的数据,无法更新");
        }
        treeMap.put(k, v);
    }


    /**
     * 移出指定key对应的值
     *
     * @param k
     * @return
     */
    public V remove(K k) {
        TreeMap<K, V> treeMap = this.getTreeMapByKey(k);
        if (treeMap.containsKey(k)) {
            this.size--;
            return treeMap.remove(k);
        }
        return null;
    }


    /**
     * 判断指定k在索引中是否存在
     *
     * @param k
     * @return
     */
    public boolean container(K k) {
        TreeMap<K, V> treeMap = this.getTreeMapByKey(k);
        return treeMap.containsKey(k);
    }

    /**
     * 获取指定key对应的索引的treemap
     *
     * @param k
     * @return
     */
    private TreeMap<K, V> getTreeMapByKey(K k) {
        int hash = this.hash(k);
        return treeMaps[hash];
    }


    /**
     * 获取指定key对应的hash值
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % m;
    }
}
