package com.lonely.并查集;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/8/8 14:55
 * @Description
 */
public class TreeNodeUnionFindTest {

    @Test
    public void test() {
        int size = 10;
        TreeNodeUnionFind treeNodeUnionFind = new TreeNodeUnionFind(size);

        //联合
        treeNodeUnionFind.union(0,1);
        treeNodeUnionFind.union(1,2);
        treeNodeUnionFind.union(9,8);
        treeNodeUnionFind.union(9,0);
        System.out.println(this.buildString(treeNodeUnionFind.getIds()));

        //判断是否关联
        System.out.println(treeNodeUnionFind.isConnection(0,1));
        System.out.println(treeNodeUnionFind.isConnection(1,2));
        System.out.println(treeNodeUnionFind.isConnection(9,1));
        System.out.println(treeNodeUnionFind.isConnection(9,4));
    }


    private String buildString(int[] array){
        StringBuilder sb = new StringBuilder();
        sb.append("[0,1,2,3,4,5,6,7,8,9]").append("\r\n").append("---------------------\r\n");
        sb.append("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if(i != array.length-1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }


}