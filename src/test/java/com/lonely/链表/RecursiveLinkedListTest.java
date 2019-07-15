package com.lonely.链表;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/6/25 11:16
 * @Description
 */
public class RecursiveLinkedListTest {


    private RecursiveLinkedList<Integer> dataInit() {
        RecursiveLinkedList<Integer> linkedList = new RecursiveLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i, (int) (Math.random() * 100));
        }
        return linkedList;
    }

    /**
     * 测试添加和get方法
     */
    @Test
    public void testAddAndGet() {
        RecursiveLinkedList<Integer> linkedList = this.dataInit();
        linkedList.addFirst(1);
        linkedList.addLast(2);

        System.out.println(linkedList);
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }
    }

    /**
     * 测试set方法
     */
    @Test
    public void testSet() {
        RecursiveLinkedList<Integer> linkedList = this.dataInit();
        linkedList.set(0, 123);
        linkedList.set(linkedList.getSize() - 1, 250);
        System.out.println(linkedList);
    }

    /**
     * 测试删除方法
     */
    @Test
    public void testRemove(){
        RecursiveLinkedList<Integer> linkedList = this.dataInit();
        System.out.println(linkedList);
        int size = linkedList.getSize();
        for (int i = 0; i < size; i++) {
            linkedList.removeFirst();
            System.out.println(linkedList);
        }
    }
}