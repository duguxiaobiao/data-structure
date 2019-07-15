package com.lonely.树.二分搜索树;

import org.junit.Test;

import java.text.MessageFormat;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/6/27 11:34
 * @Description
 */
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    private void init(){
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 10);
            System.out.println(MessageFormat.format("添加元素：{0}", random));
            tree.add(random);
        }

    }

    @Test
    public void add() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        for (int i = 0; i < 10; i++) {
            int containerNum = (int) (Math.random() * 10);
            boolean container = tree.container(containerNum);
            System.out.println(MessageFormat.format("指定随机数：{0} 是否存在树中:{1}? ", containerNum, container));
        }

    }

    /**
     * 测试前序输出
     */
    @Test
    public void testPreOrder(){
        this.init();

        System.out.println(tree);
    }


    /**
     * 测试中序输出
     */
    @Test
    public void testInOrder(){
        this.init();
        tree.inOrder();
    }

    /**
     * 测试后序输出
     */
    @Test
    public void testPostOrder(){
        this.init();
        tree.postOrder();
    }

    @Test
    public void testLevelOrder(){
        this.init();
        tree.levelOrder();
    }


    /**
     * 测试 查询当前树中的最大值和最小值
     */
    @Test
    public void testFindMaxAndMin(){
        this.init();
        System.out.println("最大值：" + tree.maxData());
        System.out.println("最小值：" + tree.minData());
    }

    @Test
    public void testRemoveMinAndMax(){
        this.init();
        System.out.println("最大值:" + tree.maxData());
        System.out.println("最小值:" + tree.minData());

        System.out.println("删除最大值后:" + tree.removeMax());
        tree.inOrder();

        System.out.println("删除最小值后:" + tree.removeMin());
        tree.inOrder();
    }


    @Test
    public void testRemoveEle(){
        this.init();
        int size = tree.getSize();
        tree.inOrder();
        System.out.println("开始删除------------");
        for (int i = 0; i < size; i++) {
            int randomNum = (int)(Math.random()*10);
            System.out.println("随机待删除的元素：" +randomNum);
            tree.remove(randomNum);
            tree.inOrder();
            System.out.println("--------------------------------------");
        }
    }


}