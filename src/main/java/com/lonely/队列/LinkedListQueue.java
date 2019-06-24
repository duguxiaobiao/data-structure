package com.lonely.队列;

/**
 * @author ztkj-hzb
 * @Date 2019/6/24 11:19
 * @Description 基于链表实现队列, 如果从头那里入队，尾那里出队，会导致出队时的删除操作需要获取待删除的节点的上一个节点，该操作是一个O(n)复杂度操作，
 * 因此，使用链表尾入队，头出队，保证都是O(1)操作
 */
public class LinkedListQueue<T> implements Queue<T> {

    /**
     * 头节点
     */
    private Node<T> head;
    /**
     * 尾节点
     */
    private Node<T> tail;
    /**
     * size长度
     */
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * 入队
     *
     * @param t
     */
    @Override
    public void enqueue(T t) {
        if (this.tail == null) {
            //说明当前队列为空
            Node<T> data = new Node<>(t);
            this.head = data;
            this.tail = data;
        } else {
            //当前队列非空
            Node<T> data = new Node<>(t);
            this.tail.nextNode = data;
            this.tail = data;
        }
        this.size++;
    }

    /**
     * 出队
     *
     * @return
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("当前队列为空，无法出队");
        }
        //获取当前的head
        Node<T> returnNode = this.head;
        //设置下一个节点为最新的head
        this.head = returnNode.nextNode;
        returnNode.nextNode = null;
        if (this.head == null) {
            this.tail = null;
        }
        this.size--;
        return returnNode.data;
    }

    /**
     * 获取队首的值
     *
     * @return
     */
    @Override
    public T getFront() {
        if (this.isEmpty()) {
            throw new RuntimeException("当前队列为空，无法获取队首的值");
        }
        return this.head.data;
    }


    /**
     * 获取队列长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.size;
    }

    /**
     * 判断当前队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int getCapcity() {
        return this.getSize();
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("LinkedListQueue:{");

        Node<T> currNode = this.head;
        for (int i = 0; i < getSize(); i++) {
            message.append(currNode);
            if (i != getSize() - 1) {
                message.append("->");
            }
            currNode = currNode.nextNode;
        }
        message.append("}");
        return message.toString();
    }

    /**
     * 节点对象
     *
     * @param <T>
     */
    private class Node<T> {
        private T data;
        private Node<T> nextNode;

        public Node(T data, Node<T> nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }

        public Node(T data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return this.data.toString();
        }
    }
}
