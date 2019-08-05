package com.lonely.队列;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/7/19 16:08
 * @Description 优先队列测试
 */
public class PriorityQueueTest {

    /**
     * 测试入队的
     */
    @Test
    public void enqueue() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            priorityQueue.enqueue((int)(Math.random()*100));
            System.out.println(priorityQueue.toString());
        }

    }

    /**
     * 测试出队
     */
    @Test
    public void dequeue() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            priorityQueue.enqueue((int)(Math.random()*100));
        }
        System.out.println(priorityQueue.toString());

        for (int i = 0; i < 10; i++) {
            priorityQueue.dequeue();
            System.out.println(priorityQueue.toString());
        }



    }

    /**
     * 获取队首
     */
    @Test
    public void getFront() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            priorityQueue.enqueue((int)(Math.random()*100));
        }
        System.out.println(priorityQueue.toString());

        System.out.println(priorityQueue.getFront());
    }

    /**
     * 获取队列长度
     */
    @Test
    public void getSize() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < 10; i++) {
            priorityQueue.enqueue((int)(Math.random()*100));
        }
        System.out.println(priorityQueue.toString());
        System.out.println(priorityQueue.getSize());
    }

    /**
     * 判断队列是否为空
     */
    @Test
    public void isEmpty() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        System.out.println(priorityQueue.isEmpty());

        for (int i = 0; i < 10; i++) {
            priorityQueue.enqueue((int)(Math.random()*100));
        }
        System.out.println(priorityQueue.toString());
        System.out.println(priorityQueue.isEmpty());

    }

    @Test
    public void getCapcity() {
    }

}