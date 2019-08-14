package com.lonely.并查集;

/**
 * @author ztkj-hzb
 * @Date 2019/8/8 11:41
 * @Description 基于树节点实现并查集,基于Size优化
 */
public class TreeNodeUnionFindWithSize implements UnionFind {

    /**
     * 分组id
     */
    private int[] ids;

    /**
     * 每个分组的元素节点个数
     */
    private int[] groupSize;

    public TreeNodeUnionFindWithSize(int size) {
        if (size < 0) {
            throw new RuntimeException("size<0 error");
        }
        this.ids = new int[size];
        this.groupSize = new int[size];
        for (int i = 0; i < size; i++) {
            this.ids[i] = i;
            this.groupSize[i] = 1;
        }
    }


    /**
     * 是否是相连
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean isConnection(int p, int q) {
        return getRootId(p) == getRootId(q);
    }

    /**
     * 将p连接到q
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        //将p的根节点跟在 q的根节点下面即可
        int pRootId = getRootId(p);
        int qRootId = getRootId(q);
        if (pRootId == qRootId) {
            return;
        }

        //判断两边的哪棵树的节点比较多，将节点少的合并到节点多的来
        if(this.groupSize[pRootId] <= this.groupSize[qRootId] ){
            this.ids[pRootId] = qRootId;
            this.groupSize[qRootId] += this.groupSize[pRootId];
        }else{
            this.ids[qRootId] = pRootId;
            this.groupSize[pRootId] += this.groupSize[qRootId];
        }
    }


    /**
     * 获取当前索引对应的父节点的索引分组
     *
     * @param p
     * @return
     */
    private int parentId(int p) {
        if (p < 0 || p >= this.ids.length) {
            throw new RuntimeException("index error");
        }
        return this.ids[p];
    }

    /**
     * 获取指定索引的元素的根节点元素对应的分组
     *
     * @param p
     * @return
     */
    private int getRootId(int p) {
        if (p < 0 || p >= this.ids.length) {
            throw new RuntimeException("index error");
        }
        int parentId = this.parentId(p);
        while (parentId != this.parentId(parentId)) {
            parentId = this.parentId(parentId);
        }
        return parentId;
    }


    public int[] getIds(){
        return this.ids;
    }

}
