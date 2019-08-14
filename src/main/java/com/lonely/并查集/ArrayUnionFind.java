package com.lonely.并查集;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/8/7 17:20
 * @Description 基于数组实现的并查集
 */
public class ArrayUnionFind implements UnionFind {

    private int[] ids;

    public ArrayUnionFind(int size) {
        if (size < 0) {
            throw new RuntimeException("size不能小于0");
        }
        this.ids = new int[size];
        for (int i = 0; i < size; i++) {
            ids[i] = i;
        }
    }

    /**
     * 判断两个指定索引对应的元素是否相连，即是否属于同一个集合
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnection(int p, int q) {
        return this.findPId(p) == this.findPId(q);
    }


    /**
     * 合并
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pId = this.findPId(p);
        int qId = this.findPId(q);

        if(pId == qId){
            //已经是在同一个集合中，直接返回
            return;
        }

        //从头开始循环
        for (int i = 0; i < this.ids.length; i++) {
            if(this.findPId(i) == pId){
                this.ids[i] = qId;
            }
        }

    }

    /**
     * 获取指定索引元素对应的分组
     *
     * @param p
     * @return
     */
    private int findPId(int p) {
        if (p < 0 || p >= ids.length) {
            throw new RuntimeException(MessageFormat.format("索引越界，范围在{0}~{1}中", 0, this.ids.length - 1));
        }
        return this.ids[p];
    }


    public int[] getIds(){
        return this.ids;
    }
}
