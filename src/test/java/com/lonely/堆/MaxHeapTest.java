package com.lonely.堆;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/7/18 17:44
 * @Description 测试最大堆的实现
 */
public class MaxHeapTest {

    /**
     * 获取堆的元素长度
     */
    @Test
    public void getSize() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(1);

        System.out.println(maxHeap.getSize());
    }


    /**
     * 判断堆是否为空
     */
    @Test
    public void isEmpty() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        System.out.println(maxHeap.isEmpty());
        maxHeap.add(1);
        System.out.println(maxHeap.isEmpty());
    }

    /**
     * 添加元素
     */
    @Test
    public void add() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(1);
        System.out.println(maxHeap);
        maxHeap.add(2);
        System.out.println(maxHeap);
        maxHeap.add(3);
        System.out.println(maxHeap);
    }

    /**
     * 查询堆中的最大值
     */
    @Test
    public void findMax() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        maxHeap.add(1);
        System.out.println(maxHeap);
        maxHeap.add(2);
        System.out.println(maxHeap);
        maxHeap.add(3);
        System.out.println(maxHeap);
        System.out.println(maxHeap.findMax());
    }


    /**
     * 移出最大值,重组
     */
    @Test
    public void extraceMax() {

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 0; i < 10; i++) {
            maxHeap.add((int) (Math.random() * 100));
        }
        System.out.println(maxHeap);

        System.out.println("最大值：" + maxHeap.extraceMax());
        System.out.println(maxHeap);
    }


    @Test
    public void repalceTest() {
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 0; i < 10; i++) {
            maxHeap.add((int) (Math.random() * 100));
        }
        System.out.println(maxHeap);

        System.out.println("替换出来的最大值：" + maxHeap.replace(20));
        System.out.println(maxHeap);

    }


    @Test
    public void heapifyTest(){

        Integer[] arrs = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arrs[i] = (int)(Math.random()*10);
        }

        MaxHeap<Integer> maxHeap = new MaxHeap<>(arrs);
        System.out.println(maxHeap.toString());

    }


}