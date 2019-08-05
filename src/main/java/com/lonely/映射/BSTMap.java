package com.lonely.映射;

import java.text.MessageFormat;

/**
 * @author ztkj-hzb
 * @Date 2019/7/17 15:29
 * @Description 基于二分搜索树实现的映射
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private int size;

    private Node root;


    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        this.add(key, value, this.root);
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     * @param node
     * @return
     */
    private Node add(K key, V value, Node node) {
        if (node == null) {
            node = new Node(key, value, null, null);
            if (this.size == 0) {
                this.root = node;
            }
            this.size++;
            return node;
        }
        if (node.key.compareTo(key) > 0) {
            //往左侧处理
            node.left = this.add(key, value, node.left);
        } else if (node.key.compareTo(key) > 0) {
            //往后侧处理
            node.right = this.add(key, value, node.right);
        } else {
            //已存在相同的key，覆盖操作
            node.value = value;
        }
        return node;
    }

    /**
     * 删除元素
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        //获取指定key对应的节点
        Node node = this.get(key, this.root);

        //删除元素
        this.root = this.removeNode(key, this.root);

        return node == null ? null : node.value;
    }

    /**
     * 删除指定开始节点后满足key的节点
     *
     * @param key
     * @param startNode
     * @return
     */
    private Node removeNode(K key, Node startNode) {

        if (startNode == null) {
            return null;
        }

        //去左侧处理
        if (startNode.key.compareTo(key) > 0) {
            startNode.left = this.removeNode(key, startNode.left);
        } else if (startNode.key.compareTo(key) < 0) {
            startNode.right = this.removeNode(key, startNode.right);
        } else {
            //找到了元素
            //判断当前元素左右叉树情况分析
            if (startNode.left == null) {
                //直接将右侧的节点 覆盖当前节点
                Node right = startNode.right;
                startNode.right = null;
                this.size--;
                return right;
            } else if (startNode.right == null) {
                Node left = startNode.left;
                left = null;
                this.size--;
                return left;
            } else {
                //普通情况，左右都有
                //选中右子树中的最小节点
                Node minNode = this.minNode(startNode.right);
                minNode.left = startNode.left;
                minNode.right = this.removeMinNode(startNode.right);
                return minNode;

            }
        }
        return startNode;

    }


    /**
     * 返回指定开始节点组成的二分搜索树的最大值节点
     *
     * @param startNode
     * @return
     */
    private Node minNode(Node startNode) {
        if (startNode == null) {
            throw new RuntimeException("startNode不能为空");
        }
        if (startNode.left == null) {
            return startNode;
        } else {
            return this.minNode(startNode.left);
        }
    }

    /**
     * 返回指定开始节点组成的二分搜索树的最大值节点
     *
     * @param startNode
     * @return
     */
    private Node maxNode(Node startNode) {
        if (startNode == null) {
            throw new RuntimeException("startNode不能为空");
        }
        if (startNode.right == null) {
            return startNode;
        } else {
            return this.minNode(startNode.right);
        }
    }

    /**
     * 删除指定根节点的最小值节点
     *
     * @param startNode
     * @return
     */
    private Node removeMinNode(Node startNode) {

        if (startNode == null) {
            throw new RuntimeException("startNode不能为空");
        }
        if (startNode.left == null) {
            //已经是最小节点
            startNode = startNode.right;
            this.size--;
            return startNode;
        }

        return this.removeMinNode(startNode.left);
    }

    private Node removeMaxNode(Node startNode) {
        if (startNode == null) {
            throw new RuntimeException("startNode不能为空");
        }

        if (startNode.right == null) {
            //已经是最大值了
            startNode = startNode.left;
            this.size--;
            return startNode;
        }
        return this.removeMaxNode(startNode.right);
    }


    /**
     * 判断指定key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean container(K key) {
        return this.get(key) != null;
    }

    /**
     * 获取指定key对应的value
     *
     * @param key
     * @return
     */
    @Override
    public V get(K key) {
        Node node = this.get(key, this.root);
        return node == null ? null : node.value;
    }

    /**
     * 根据key获取对应的节点信息
     *
     * @param key
     * @param node
     * @return
     */
    private Node get(K key, Node node) {
        if (node == null) {
            return null;
        }

        if (node.key.compareTo(key) > 0) {
            //往左侧处理
            return this.get(key, node.left);
        } else if (node.key.compareTo(key) < 0) {
            //往后侧处理
            return this.get(key, node.right);
        } else {
            return node;
        }
    }


    /**
     * 更新指定key的值
     *
     * @param key
     * @param value
     */
    @Override
    public void set(K key, V value) {
        Node node = this.get(key, this.root);
        if (node == null) {
            throw new RuntimeException(MessageFormat.format("指定key:{0} 对应的节点不存在! ", key));
        }
        node.value = value;
    }


    /**
     * 判断树中是否存在元素
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 获取树中节点大小
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
        message.append("***BSTMap:{");
        message.append(this.inOrder());
        message.append("}***");
        return message.toString();
    }

    /**
     * 中序排序输出
     *
     * @return
     */
    private String inOrder() {
        return this.inOrder(this.root);
    }

    private String inOrder(Node startNode) {
        StringBuilder message = new StringBuilder();
        if (startNode == null) {
            return message.toString();
        }
        //先输出左树值
        message.append(this.inOrder(startNode.left));
        //在输出本省得值
        message.append(startNode.toString());
        //在输出右树值
        message.append(this.inOrder(startNode.right));
        return message.toString();
    }

    private class Node {

        K key;
        V value;

        Node left;
        Node right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node() {
            this(null, null, null, null);
        }

        @Override
        public String toString() {
            return MessageFormat.format(" {0} : {1} ", key.toString(), value.toString());
        }
    }
}
