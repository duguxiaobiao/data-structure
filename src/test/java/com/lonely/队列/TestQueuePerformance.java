package com.lonely.队列;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/6/24 14:44
 * @Description 测试队列基于数组、链表以及环形队列的性能
 */
public class TestQueuePerformance {


    @Test
    public void testPersormance() {
        int size = 100000;
        System.out.println(MessageFormat.format("测试普通队列实现 {0}数量耗时：{1}s",size,getTime(size,new ArrayQueue<>())));
        System.out.println(MessageFormat.format("测试循环队列实现 {0}数量耗时：{1}s",size,getTime(size,new LoopQueue<>())));
        System.out.println(MessageFormat.format("测试链表队列实现 {0}数量耗时：{1}s",size,getTime(size,new LinkedListQueue<>())));

        size = 10000000;
        System.out.println(MessageFormat.format("测试普通队列实现 {0}数量耗时：{1}s",size,getTime(size,new ArrayQueue<>())));
        System.out.println(MessageFormat.format("测试循环队列实现 {0}数量耗时：{1}s",size,getTime(size,new LoopQueue<>())));
        System.out.println(MessageFormat.format("测试链表队列实现 {0}数量耗时：{1}s",size,getTime(size,new LinkedListQueue<>())));
    }

    /**
     * 获取测试时长
     * @param size
     * @param queue
     * @return
     */
    private double getTime(int size, Queue<Integer> queue) {
        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            queue.enqueue((int) (Math.random() * 100));
        }
        for (int i = 0; i < size; i++) {
            queue.dequeue();
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

}
