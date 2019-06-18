package com.lonely.栈;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 15:04
 * @Description 测试基于数组中的栈实现的功能
 */
public class TestArrayStack {

    /**
     * 测试基础的函数
     */
    @Test
    public void testBasic(){

        Stack<String> stringStack =new ArrayStack<>();

        System.out.println("定义时是否为空："+stringStack.isEmpty());

        stringStack.push("fdafda");
        System.out.println(stringStack.toString());

        System.out.println("栈顶的值：" + stringStack.peek());
        System.out.println(stringStack.toString());

        System.out.println("弹出栈顶的值：" + stringStack.pop());
        System.out.println(stringStack.toString());

        System.out.println("栈中的长度：" + stringStack.getSize());
        System.out.println("栈中的是否为空：" + stringStack.isEmpty());

    }

}
