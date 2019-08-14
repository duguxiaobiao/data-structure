package com.lonely.树.红黑树;

import com.lonely.树.AVL树.AVLTree;
import com.lonely.集合.AvlTreeSet;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/8/14 10:45
 * @Description
 */
public class RBTreeTest {


    @Test
    public void put() {

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        AVLTree<Integer> avlTree = new AVLTree<>();

        int m = 20000000;

        Random random = new Random();


        //测试RBTree的添加元素的性能
        long start = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int nextInt = random.nextInt(m);
            rbTree.put(nextInt, nextInt);
        }
        //System.out.println(rbTree.toString());
        long end = System.nanoTime();
        System.out.println("RBTree的时间：" +(end-start)/1000000000.0 + "s");

        //测试RBTree的添加元素的性能
        start = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int nextInt = random.nextInt(m);
            avlTree.add(nextInt);
        }
        //System.out.println(avlTree.toString());
        end = System.nanoTime();
        System.out.println("AVLTree的时间：" +(end-start)/1000000000.0 + "s");



    }

}