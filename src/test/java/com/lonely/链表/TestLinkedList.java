package com.lonely.链表;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/6/19 14:41
 * @Description
 */
public class TestLinkedList {

    /**
     * 准备数据
     *
     * @return
     */
    private LinkedList<Integer> buildDatas() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.addLast((int) (1 + Math.random() * (100 - 1)));
        }
        System.out.println(linkedList);
        return linkedList;
    }

    /**
     * 测试添加元素
     */
    @Test
    public void testAddEle() {

        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.addFirst(1);

        for (int i = 0; i < 10; i++) {
            linkedList.addLast((int) (1 + Math.random() * (100 - 1)));
        }

        System.out.println(linkedList);

    }

    /**
     * 获取指定索引对应的值
     */
    @Test
    public void testGet() {
        LinkedList<Integer> linkedList = this.buildDatas();
        System.out.println(linkedList.get(2));
        System.out.println("第一个：" + linkedList.getFirst());
        System.out.println("最后一个：" + linkedList.getLast());
    }

    /**
     * 测试修改指定索引的值
     */
    @Test
    public void testSet(){
        LinkedList<Integer> linkedList = this.buildDatas();
        linkedList.set(2, 100);
        System.out.println(linkedList.get(2));
        System.out.println(linkedList);
    }


    @Test
    public void testRemove(){
        LinkedList<Integer> linkedList = this.buildDatas();
        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
        linkedList.remove(3);
        System.out.println(linkedList);
    }


    @Test
    public void testRemoveEleByFirst(){
        LinkedList<Integer> linkedList = this.buildDatas();
        linkedList.addLast(120);

        System.out.println(linkedList);

        //删除元素
        linkedList.removeElementByFirst(120);
        System.out.println(linkedList);
    }

}
