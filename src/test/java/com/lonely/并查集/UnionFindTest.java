package com.lonely.并查集;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/8/8 15:10
 * @Description 并查集两种方式效率比对
 */
public class UnionFindTest {

    @Test
    public void test() {
        //元素个数
        int size = 100000;
        //操作次数
        int operationSize = 100000;

        //1.基于数组
        ArrayUnionFind arrayUnionFind = new ArrayUnionFind(size);
        //System.out.println("基于数组实现时间：" + operation(size, operationSize, arrayUnionFind) + "s");

        //2.基于树
        TreeNodeUnionFind treeNodeUnionFind = new TreeNodeUnionFind(size);
        //System.out.println("基于树实现时间：" + operation(size, operationSize, treeNodeUnionFind) + "s");

        //3.基于树 size优化效率
        TreeNodeUnionFindWithSize treeNodeUnionFindWithSize = new TreeNodeUnionFindWithSize(size);
        System.out.println("基于树Size优化实现时间：" + operation(size, operationSize, treeNodeUnionFindWithSize) + "s");

        //4,基于树 深度 优化效率
        TreeNodeUnionFindWithRank treeNodeUnionFindWithRank = new TreeNodeUnionFindWithRank(size);
        System.out.println("基于树深度优化实现时间：" + operation(size, operationSize, treeNodeUnionFindWithRank) + "s");

        //5.基于树 路径压缩效率
        TreeNodeUnionFindWithPathCompression treeNodeUnionFindWithPathCompression = new TreeNodeUnionFindWithPathCompression(size);
        System.out.println("基于树路径压缩优化实现时间：" + operation(size, operationSize, treeNodeUnionFindWithPathCompression) + "s");


    }


    private double operation(int size, int operationSize, UnionFind unionFind) {
        long startTime = System.nanoTime();

        Random random = new Random();

        //连接
        for (int i = 0; i < operationSize; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.union(a, b);
        }

        //查询
        for (int i = 0; i < operationSize; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.isConnection(a, b);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}