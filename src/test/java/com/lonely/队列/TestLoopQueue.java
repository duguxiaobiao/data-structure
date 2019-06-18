package com.lonely.队列;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 16:32
 * @Description 测试循环队列
 */
public class TestLoopQueue {

    @Test
    public void testFull(){
        LoopQueue<Integer> loopQueue = new LoopQueue<>(3);
        loopQueue.enqueue(1);
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());
        loopQueue.enqueue(2);
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());
        loopQueue.enqueue(3);
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());
        loopQueue.enqueue(4);
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());

        System.out.println(loopQueue);

        System.out.println("出队：" + loopQueue.dequeue());
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());

        System.out.println("出队：" + loopQueue.dequeue());
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());

        System.out.println("出队：" + loopQueue.dequeue());
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());

        System.out.println("出队：" + loopQueue.dequeue());
        System.out.println("容量：" + loopQueue.getCapcity() + ",长度:" + loopQueue.getSize());
        System.out.println(loopQueue);

    }


}
