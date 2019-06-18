package com.lonely.队列;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 15:36
 * @Description 测试基于数组的队列实现
 */
public class TestArrayQueue {

    @Test
    public void testBasic(){

        Queue<String> stringQueue = new ArrayQueue<>();
        stringQueue.enqueue("sfasfd");
        stringQueue.enqueue("sfasfdfdafaf");

        System.out.println(stringQueue);

        System.out.println("队首数据：" + stringQueue.getFront());

        System.out.println("出队数据：" + stringQueue.dequeue());
        System.out.println(stringQueue);
        System.out.println("是否为空"+stringQueue.isEmpty());
        System.out.println("长度"+stringQueue.getSize());

        System.out.println("出队数据：" + stringQueue.dequeue());
        System.out.println(stringQueue);
        System.out.println("是否为空"+stringQueue.isEmpty());
        System.out.println("长度"+stringQueue.getSize());





    }

}
