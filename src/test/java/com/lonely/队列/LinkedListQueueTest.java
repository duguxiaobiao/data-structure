package com.lonely.队列;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/6/24 11:37
 * @Description 测试基于链表实现的队列
 */
public class LinkedListQueueTest {

    @Test
    public void test() {

        //添加元素
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            linkedListQueue.enqueue((int) (1 + Math.random() * (100 - 1)));
        }
        System.out.println(linkedListQueue);

        //弹出元素
        int size = linkedListQueue.getSize();
        for (int i = 0; i < size; i++) {
            //获取队首元素
            System.out.println("队首元素:" + linkedListQueue.getFront());
            //弹出元素
            System.out.println("弹出元素的值：" + linkedListQueue.dequeue());
            System.out.println(linkedListQueue);
            System.out.println("------------------------------------------------");
        }


    }
}