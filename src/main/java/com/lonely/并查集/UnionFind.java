package com.lonely.并查集;

/**
 * @author ztkj-hzb
 * @Date 2019/8/7 17:18
 * @Description 并查集接口
 */
public interface UnionFind {

    /**
     * 指定索引的两个元素是否相连
     * @param p
     * @param q
     * @return
     */
    boolean isConnection(int p,int q);

    /**
     * 将指定索引的两个元素相连
     * @param p
     * @param q
     */
    void union(int p ,int q);


}
