package com.lonely.链表;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/6/19 14:13
 * @Description 链表实现 普通的带有虚拟头节点的实现
 */
public class LinkedList<T> implements ILinkedList<T> {

    /**
     * 虚拟头节点
     */
    private Node<T> dummyHead;

    /**
     * 一共节点数量
     */
    private int size;


    public LinkedList() {
        this.dummyHead = new Node<>(null, null);
        size = 0;
    }


    /**
     * 在链表头添加元素，只要新建一个新节点，设置下一个节点为当前的头节点即可
     *
     * @param t
     */
    public void addFirst(T t) {
        this.add(0, t);
    }

    /**
     * 在链表尾添加元素
     *
     * @param t
     */
    public void addLast(T t) {
        this.add(getSize(), t);
    }

    /**
     * 在指定索引位置插入数据，注意这里的索引，只能提供第几个，不能直接根据索引位置来直接获取数据，因为不同于数组，地址不是连续开辟的
     *
     * @param index
     * @param t
     */
    @Override
    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new RuntimeException(MessageFormat.format("index:{0}超出范围({1}~{2}),请检查..", index, 0, size));
        }

        //通过index和首节点依次往下找，一直找到指定index位置的前一个元素
        Node prevNode = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prevNode = prevNode.next;
        }

        //已经获取到当前索引的上一个节点了
        //注意这里需要先设置待新增的节点，然后在设置前一个节点，不能先设置前一个节点的下一个
        Node addNode = new Node(t);
        addNode.next = prevNode.next;
        prevNode.next = addNode;
        this.size++;
    }

    /**
     * 获取指定索引对应的节点的值
     *
     * @param index
     * @return
     */
    @Override
    public T get(int index) {

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(MessageFormat.format("下标越界，指定索引：{0}不在范围({1}~{2})中,请检查...", index, 0, getSize() - 1));
        }

        Node<T> currNode = this.dummyHead.next;

        //循环遍历获取对应索引的节点信息
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        return currNode.data;
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public T getFirst() {
        return this.get(0);
    }

    /**
     * 获取最后一个元素
     *
     * @return
     */
    public T getLast() {
        return this.get(getSize() - 1);
    }

    /**
     * 更新指定index对应的元素的数据
     *
     * @param index
     * @param t
     */
    @Override
    public void set(int index, T t) {

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(MessageFormat.format("下标越界，指定索引：{0}不在范围({1}~{2})中,请检查...", index, 0, getSize() - 1));
        }

        Node currNode = dummyHead.next;
        for (int i = 0; i < index; i++) {
            currNode = currNode.next;
        }

        //找到了节点，设置内容的值
        currNode.data = t;
    }

    /**
     * 测试链表中是否存在对应的元素数据
     *
     * @param t
     * @return
     */
    @Override
    public boolean container(T t) {
        Node currNode = this.dummyHead.next;
        while (currNode != null) {
            if (currNode.data.equals(t)) {
                return true;
            }
            currNode = currNode.next;
        }
        return false;
    }

    /**
     * 删除指定索引对应的元素
     *
     * @param index
     * @return
     */
    @Override
    public T remove(int index) {

        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(MessageFormat.format("下标越界，指定索引：{0}不在范围({1}~{2})中,请检查...", index, 0, getSize() - 1));
        }

        Node preNode = this.dummyHead;
        //循环找到待删除的索引的节点的前一个节点
        for (int i = 0; i < index; i++) {
            preNode = preNode.next;
        }

        //当前待删除的节点
        Node<T> currNode = preNode.next;

        //设置前一个节点的下一个节点为当前节点的下一个节点
        preNode.next = currNode.next;

        //设置当前节点的下一个为null
        currNode.next = null;

        this.size--;
        return currNode.data;
    }

    /**
     * 删除第一个元素
     *
     * @return
     */
    public T removeFirst() {
        return this.remove(0);
    }

    /**
     * 删除最后一个元素
     *
     * @return
     */
    public T removeLast() {
        return this.remove(getSize() - 1);
    }


    /**
     * 删除指定链表中第一个匹配到指定值的元素
     *
     * @param t
     */
    public void removeElementByFirst(T t) {
        Node<T> firstNode = this.dummyHead.next;
        if(firstNode == null){
            return;
        }
        if(firstNode.data.equals(t)){
            this.dummyHead.next = firstNode.next;
            firstNode.next = null;
            this.size--;
            return;
        }

        while(firstNode.next != null){
            if(firstNode.next.data.equals(t)){
                firstNode.next = firstNode.next.next;
                this.size--;
                break;
            }
            firstNode = firstNode.next;
        }
    }


    /**
     * 获取链表节点大小
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * 返回当前链表节点是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 格式化输出
     *
     * @return
     */
    @Override
    public String toString() {
        //格式  xxx-->xxxx->xxxx->xxx
        StringBuilder messagae = new StringBuilder("LinkedList{");

        Node currNode = this.dummyHead.next;
        if (currNode != null) {
            while (true) {
                messagae.append(currNode);
                currNode = currNode.next;
                if (currNode == null) {
                    break;
                } else {
                    messagae.append("-->");
                }
            }
        }

        messagae.append("}");
        return messagae.toString();
    }

    /**
     * 链表节点对象
     *
     * @param <T>
     */
    private class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T t, Node next) {
            this.data = t;
            this.next = next;
        }

        public Node(T t) {
            this(t, null);
        }

        public Node() {
            this(null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }


}
