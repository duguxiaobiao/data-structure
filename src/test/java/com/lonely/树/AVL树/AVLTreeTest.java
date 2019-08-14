package com.lonely.树.AVL树;

import com.lonely.树.二分搜索树.BinarySearchTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author ztkj-hzb
 * @Date 2019/8/9 14:21
 * @Description 平衡二叉树测试
 */
public class AVLTreeTest {


    @Test
    public void add() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        int m = 10000;
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            avlTree.add(random.nextInt(m));
        }

        avlTree.inOrder();
    }


    @Test
    public void isBstTree() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        int m = 10000;
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            avlTree.add(random.nextInt(m));
        }


        System.out.println(avlTree.isBstTree());

    }


    @Test
    public void isAvlTree() {
        AVLTree<Integer> avlTree = new AVLTree<>();

        int m = 10000;
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            avlTree.add(random.nextInt(m));
        }


        System.out.println(avlTree.isAvlTree());

    }


    /**
     * 对比二分搜索树和avl树的效率
     */
    @Test
    public void contrastEfficiency() {
        List<Integer> lists = new ArrayList<>();
        int m = 10000;
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            lists.add(random.nextInt(m));
        }
        Collections.sort(lists);

        this.bstCompare(new BinarySearchTree<>(), lists);
        this.avlCompare(new AVLTree<>(), lists);
    }

    private void avlCompare(AVLTree<Integer> avlTree, List<Integer> list) {
        long start = System.nanoTime();
        for (Integer num : list) {
            avlTree.add(num);
        }
        long end = System.nanoTime();
        System.out.println("使用AVLTree时间:" + (end - start) / 1000000000.0 + "s");
    }

    private void bstCompare(BinarySearchTree<Integer> bstTree, List<Integer> list) {
        long start = System.nanoTime();
        for (Integer num : list) {
            bstTree.add(num);
        }
        long end = System.nanoTime();
        System.out.println("使用BSTTree时间:" + (end - start) / 1000000000.0 + "s");
    }



    @Test
    public void remove(){
        List<Integer> lists = new ArrayList<>();
        int m = 10000;
        Random random = new Random();
        for (int i = 0; i < m; i++) {
            lists.add(random.nextInt(m));
        }

        AVLTree<Integer> avlTree = new AVLTree<>();

        //添加元素
        for (Integer num : lists) {
            avlTree.add( num);
        }

        avlTree.inOrder();

        //删除元素
        for (Integer num : lists) {
            avlTree.remove(num);
        }

        avlTree.inOrder();
    }
}