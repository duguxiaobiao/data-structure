package com.lonely.并查集;

/**
 * @author ztkj-hzb
 * @Date 2019/8/8 11:41
 * @Description 基于树节点实现并查集, 基于树的深度优化
 */
public class TreeNodeUnionFindWithRank implements UnionFind {

    /**
     * 分组id
     */
    private int[] ids;

    /**
     * 每个集合的高度
     */
    private int[] rank;

    public TreeNodeUnionFindWithRank(int size) {
        if (size < 0) {
            throw new RuntimeException("size<0 error");
        }
        this.ids = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.ids[i] = i;
            this.rank[i] = 1;
        }
    }


    /**
     * 是否是相连
     *
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
        if (this.rank[pRootId] < this.rank[qRootId]) {
            this.ids[pRootId] = qRootId;
        } else if (this.rank[pRootId] > this.rank[qRootId]) {
            this.ids[qRootId] = pRootId;
        } else {
            this.ids[qRootId] = pRootId;
            this.rank[pRootId] += 1;
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


    public int[] getIds() {
        return this.ids;
    }

}
