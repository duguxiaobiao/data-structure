package com.lonely.映射;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/7/17 10:27
 * @Description 基于链表实现映射
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    /**
     * 记录链表长度
     */
    private int size;

    /**
     * 虚拟头节点
     */
    private Node dummyHeaderNode;

    public LinkedListMap() {
        this.dummyHeaderNode = new Node();
        this.size = 0;
    }

    /**
     * 根据key查询对应的节点
     *
     * @param key
     * @return
     */
    private Node getNode(K key) {
        Node currNode = this.dummyHeaderNode.next;
        while (currNode != null) {
            if (currNode.key.equals(key)) {
                return currNode;
            }
            currNode = currNode.next;
        }
        return null;
    }

    /**
     * 添加key-value映射，如果指定key已存在，则覆盖value值
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {

        Node node = this.getNode(key);

        if (node != null) {
            //覆盖
            node.value = value;
        } else {
            //新增
            this.dummyHeaderNode.next = new Node(key, value, dummyHeaderNode.next);
            this.size++;
        }

    }

    /**
     * 删除指定key对应的节点
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        Node preNode = this.dummyHeaderNode;
        while (preNode.next != null) {
            if (preNode.next.key.equals(key)) {
                break;
            }
            preNode = preNode.next;
        }

        if (preNode.next != null) {
            Node delNode = preNode.next;
            preNode.next = delNode.next;
            delNode.next = null;
            this.size--;
            return delNode.value;
        }

        return null;
    }

    /**
     * 判断当前链表中是否存在指定key
     *
     * @param key
     * @return
     */
    @Override
    public boolean container(K key) {
        return this.getNode(key) != null;
    }

    /**
     * 获取指定key对应的value值
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        Node node = this.getNode(key);
        return node != null ? node.value : null;
    }

    /**
     * 更新指定key对应的value值，如果不存在key，抛出异常
     *
     * @param key
     * @param value
     */
    @Override
    public void set(K key, V value) {
        Node node = this.getNode(key);
        if (node == null) {
            throw new RuntimeException(MessageFormat.format("指定key：{0}不存在!", key));
        }
        //存在，设置新的值
        node.value = value;

    }

    /**
     * 判断当前链表是是否存在元素
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 获取当前链表中节点长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("***LinkedListMap:{");
        Node currNode = this.dummyHeaderNode.next;
        while (currNode != null) {
            message.append(currNode);
            currNode = currNode.next;
        }
        message.append("}***");
        return message.toString();
    }

    private class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return MessageFormat.format("{0} : {1} ", key.toString(), value.toString());
        }
    }
}
