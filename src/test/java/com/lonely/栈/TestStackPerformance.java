package com.lonely.栈;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/6/24 14:01
 * @Description 测试栈基于数组和链表的性能
 */
public class TestStackPerformance {

    @Test
    public void performanceTest() {
        //测试数量少时性能
        int size = 10000;
        System.out.println(MessageFormat.format("arrayStack {0}数量耗时：{1} s", size, testStack(size, new ArrayStack<>())));
        System.out.println(MessageFormat.format("linkedListStack {0}数量耗时：{1} s", size, testStack(size, new LinkedListStack<>())));

        size = 10000000;
        System.out.println(MessageFormat.format("arrayStack {0}数量耗时：{1} s", size, testStack(size, new ArrayStack<>())));
        System.out.println(MessageFormat.format("linkedListStack {0}数量耗时：{1} s", size, testStack(size, new LinkedListStack<>())));
    }

    private double testStack(int size, Stack<Integer> stack) {
        long start = System.nanoTime();
        for (int i = 0; i < size; i++) {
            stack.push((int) (1 + Math.random() * (100 - 1)));
        }
        for (int i = 0; i < size; i++) {
            stack.pop();
        }
        long end = System.nanoTime();
        return (end - start) / 1000000000.0;
    }

}
