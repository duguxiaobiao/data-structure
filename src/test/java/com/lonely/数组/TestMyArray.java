package com.lonely.数组;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Random;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 10:28
 * @Description 测试自定义数组结构
 */
public class TestMyArray {


    /**
     * 测试定义一个自定义数组
     */
    @Test
    public void testDefinitionMyArray() {
        MyArray<String> myArray = new MyArray<>();
        System.out.println("声明空间大小：" + myArray.getCapcity());
        System.out.println("开辟空间大小" + myArray.getSize());
        System.out.println("是否为空:" + myArray.isEmpty());
    }


    /**
     * 测试添加元素
     */
    @Test
    public void testAddEmployee() {
        MyArray<String> myArray = new MyArray<>(3);
        myArray.addLast("aa");
        System.out.println(MessageFormat.format("目前数组容量:{0},当前实际大小：{1}", myArray.getCapcity(), myArray.getSize()));
        myArray.addLast("bb");
        System.out.println(MessageFormat.format("目前数组容量:{0},当前实际大小：{1}", myArray.getCapcity(), myArray.getSize()));
        myArray.addLast("cc");
        System.out.println(MessageFormat.format("目前数组容量:{0},当前实际大小：{1}", myArray.getCapcity(), myArray.getSize()));
        myArray.addLast("dd");
        System.out.println(MessageFormat.format("目前数组容量:{0},当前实际大小：{1}", myArray.getCapcity(), myArray.getSize()));
        myArray.addLast("ee");
        System.out.println(MessageFormat.format("目前数组容量:{0},当前实际大小：{1}", myArray.getCapcity(), myArray.getSize()));
    }

    /**
     * 测试set 和get方法
     */
    @Test
    public void testSetAndGetEmployee() {
        MyArray<String> myArray = new MyArray<>(3);
        myArray.addLast("aa");
        myArray.addLast("bb");
        myArray.addLast("cc");
        myArray.addLast("dd");
        myArray.addLast("ee");

        System.out.println("更新前：" + myArray);

        System.out.println("更新前索引1对应的值：" + myArray.get(1));

        myArray.set(1, "风中追风");
        System.out.println("更新后：" + myArray);
    }

    /**
     * 测试获取指定元素出现的所有位置
     */
    @Test
    public void testFindAll() {
        MyArray<Integer> myArray = new MyArray<>();
        for (int i = 0; i < 38; i++) {
            if (i % 10 == 0) {
                myArray.addLast(51);
            } else {
                myArray.addLast((int) (1 + Math.random() * (100 - 1)));
            }
        }

        System.out.println(myArray);

        Integer[] all = myArray.findAll(51);
        for (Integer integer : all) {
            System.out.println(integer);
        }
    }

    /**
     * 测试包含函数，和find函数
     */
    @Test
    public void testContainerAndFind() {
        MyArray<Integer> myArray = new MyArray<>();
        for (int i = 0; i < 38; i++) {
            if (i % 10 == 0) {
                myArray.addLast(51);
            } else {
                myArray.addLast((int) (1 + Math.random() * (100 - 1)));
            }
        }

        System.out.println(myArray);


        System.out.println("是否包含51：" + myArray.container(51));
        System.out.println("数字51第一次出现的位置：" + myArray.find(51));
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testRemove() {
        MyArray<Integer> myArray = new MyArray<>();
        for (int i = 0; i < 38; i++) {
            if (i % 10 == 0) {
                myArray.addLast(51);
            } else {
                myArray.addLast((int) (1 + Math.random() * (100 - 1)));
            }
        }

        System.out.println(myArray);

        myArray.removeFirst();
        System.out.println("删除第一个:" + myArray);
        myArray.removeLast();
        System.out.println("删除最后一个:" + myArray);
        myArray.remove(2);
        System.out.println("删除索引为2的：" + myArray);

        myArray.removeElement(51);
        System.out.println("单条删除数值51的：" + myArray);

        myArray.batchRemoveElement(51);
        System.out.println("批量删除51的：" + myArray);
    }

    @Test
    public void testRemoveAll(){
        MyArray<Integer> myArray = new MyArray<>(10);
        for (int i = 0; i < 10; i++) {
            myArray.addLast((int)(Math.random()*100));
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(myArray.removeLast());
        }
    }
}
