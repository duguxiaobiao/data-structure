package com.lonely.并查集;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/8/8 14:43
 * @Description
 */
public class ArrayUnionFindTest {

    @Test
    public void test(){

        int size  =10;
        ArrayUnionFind arrayUnionFind = new ArrayUnionFind(size);

        //联合
        arrayUnionFind.union(0,1);
        System.out.println(buildString(arrayUnionFind.getIds()));

        //是否联合
        System.out.println(arrayUnionFind.isConnection(0,1));
        System.out.println(arrayUnionFind.isConnection(0,2));

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