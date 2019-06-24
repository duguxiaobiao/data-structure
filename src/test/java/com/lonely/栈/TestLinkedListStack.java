package com.lonely.栈;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/6/20 11:50
 * @Description
 */
public class TestLinkedListStack {


    @Test
    public void test() {
        LinkedListStack<Integer> stack = new LinkedListStack();
        for (int i = 0; i < 10; i++) {
            stack.push((int)(1+Math.random()*(100-1)));
        }
        System.out.println(stack);

        int size = stack.getSize();

        //弹栈
        for (int i = 0; i < size; i++) {
            //查看栈顶的值
            System.out.println("栈顶的值："+stack.peek());

            System.out.println("弹出栈顶的值：" +stack.pop());
            System.out.println(stack);
            System.out.println("--------------------------");
        }


    }

}