package com.lonely.树.二分搜索树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author ztkj-hzb
 * @Date 2019/6/25 16:43
 * @Description 二分搜索树
 */
public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * 根节点
     */
    private Node root;
    /**
     * 元素集合长度
     */
    private int size;


    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }


    /**
     * 添加元素
     *
     * @param t
     */
    public void add(T t) {
        /*//判断当前树种是否存在节点
        if (this.root == null) {
            this.root = new Node(t);
            this.size++;
        } else {
            this.add(this.root, t);
        }*/
        this.add(this.root, t);
    }

    /**
     * 添加元素递归实现 第一种添加方式
     * @param node
     * @param t
     */
    /*private void add(Node node, T t) {
        if (t.equals(node.data)) {
            //不考虑元素相同情况，直接返回
            return;
        } else if (t.compareTo(node.data) < 0 && node.left == null) {
            node.left = new Node(t);
            this.size++;
            return;
        } else if (t.compareTo(node.data) > 0 && node.right == null) {
            node.right = new Node(t);
            this.size++;
            return;
        }

        if (t.compareTo(node.data) < 0) {
            this.add(node.left, t);
        } else {
            this.add(node.right, t);
        }
    }*/


    /**
     * 第二种添加方式
     *
     * @param node
     * @param t
     * @return
     */
    private Node add(Node node, T t) {
        //在这里将 null 也看作是一个二分搜索树
        if (node == null) {
            node = new Node(t);
            if (this.size == 0) {
                this.root = node;
            }
            this.size++;
            return node;
        }

        if (t.compareTo(node.data) < 0) {
            //像左叉树中添加节点
            node.left = this.add(node.left, t);
        } else if (t.compareTo(node.data) > 0) {
            node.right = this.add(node.right, t);
        }
        return node;
    }


    /**
     * 判断是否存在元素t
     *
     * @param t
     * @return
     */
    public boolean container(T t) {
        return this.container(root, t);
    }

    /**
     * 递归查询树中是否存在元素t
     *
     * @param node
     * @param t
     * @return
     */
    private boolean container(Node node, T t) {
        //终止条件
        if (node == null) {
            return false;
        }

        if (t.compareTo(node.data) == 0) {
            return true;
        } else if (t.compareTo(node.data) < 0) {
            return this.container(node.left, t);
        } else {
            return this.container(node.right, t);
        }
    }

    /**
     * 前置排序 根在前，然后再是左右
     */
    public void preOrder() {
        this.preOrder(this.root);
    }

    /**
     * 前置排序输出
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            //System.out.println("null");
            return;
        }

        System.out.println(node.data);

        //输出左叉树
        this.preOrder(node.left);
        //输出右叉树
        this.preOrder(node.right);
    }


    /**
     * 中序排序其实就是 正序排序，从结果上看
     */
    public void inOrder() {
        this.inOrder(this.root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        //先输出左子树
        inOrder(node.left);
        //在输出中间的值
        System.out.println(node.data);
        //在输出右子树
        inOrder(node.right);
    }

    /**
     * 后序排序 左、右、根
     */
    public void postOrder() {
        this.postOrder(this.root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        //先输出左叉树
        this.postOrder(node.left);
        //再输出右叉树
        this.postOrder(node.right);
        //输出当前节点的根值
        System.out.println(node.data);
    }


    /**
     * 后序排序从结果得知，就是倒序排序
     */
    /*public void afterOrder(){
        afterOrder(this.root);
    }*/

    /*private void afterOrder(Node node){
        if(node == null){
            return;
        }
        //先执行右叉树
        afterOrder(node.right);
        //输出本身的值
        System.out.println(node.data);
        //再执行左叉树
        afterOrder(node.left);
    }*/


    /**
     * 层序遍历(广度优先遍历),采用队列实现
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);

        while (!queue.isEmpty()) {
            Node currNode = queue.remove();
            System.out.println(currNode.data);
            if (currNode.left != null) {
                queue.add(currNode.left);
            }
            if (currNode.right != null) {
                queue.add(currNode.right);
            }
        }
    }


    /**
     * 获取当前树种最小值
     *
     * @return
     */
    public T minData() {
        if (this.size == 0) {
            throw new RuntimeException("当前树种没有节点，获取最小值失败");
        }
        return minData(this.root).data;
    }

    private Node minData(Node node) {
        if (node.left != null) {
            return minData(node.left);
        }
        return node;
    }


    /**
     * 获取当前树中的最大值
     *
     * @return
     */
    public T maxData() {
        if (this.size == 0) {
            throw new RuntimeException("当前树中没有节点，获取最大值失败");
        }
        return maxData(this.root).data;
    }

    private Node maxData(Node node) {
        if (node.right != null) {
            return maxData(node.right);
        }
        return node;
    }

    /**
     * 删除最小的节点，返回最小节点的值
     *
     * @return
     */
    public T removeMin() {
        //获取最小节点
        T minData = minData();
        //从root节点开始，递归检查，然后删除
        this.removeMinNode(this.root);
        return minData;
    }

    private Node removeMinNode(Node node) {
        //当前节点的左叉树节点为空，则直接返回右节点
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            this.size--;
            return rightNode;
        }
        node.left = removeMinNode(node.left);
        return node;
    }

    /**
     * 删除树中的最大值
     *
     * @return
     */
    public T removeMax() {
        T maxData = this.maxData();
        this.removeMaxNode(this.root);
        return maxData;
    }


    private Node removeMaxNode(Node node) {
        if (node.right == null) {
            //已经是最大值了
            this.size--;
            Node leftNode = node.left;
            node.left = null;
            return leftNode;
        }
        node.right = removeMaxNode(node.right);
        return node;
    }

    /**
     * 删除指定元素
     *
     * @param t
     */
    public void remove(T t) {
        this.root = this.remove(this.root, t);
    }

    private Node remove(Node node, T t) {
        if (node == null) {
            //表明没找到元素t
            return null;
        }
        if (t.compareTo(node.data) < 0) {
            node.left = remove(node.left, t);
            return node;
        } else if (t.compareTo(node.data) > 0) {
            node.right = remove(node.right, t);
            return node;
        } else {
            //找到了元素
            //判断待删除的元素是否只存在左节点，右节点，还是左右都有
            if (node.left == null) {
                //最小值
                Node right = node.right;
                node.right = null;
                this.size--;
                return right;
            } else if (node.right == null) {
                //最大值
                Node left = node.left;
                node.left = null;
                this.size--;
                return left;
            } else {
                //存在左右节点的
                //第一步：找到右子树的最小值的节点
                Node successorNode = this.minData(node.right);

                //第二步：删除右子树最小值节点,返回的节点即最新的带替换的节点
                Node removeMinNode = this.removeMinNode(node.right);
                successorNode.left = node.left;
                successorNode.right = removeMinNode;

                node.left = node.right = null;

                return successorNode;
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder outputStr = new StringBuilder();
        this.generatorOutputStr(root, 0, outputStr);
        return outputStr.toString();
    }

    /**
     * 构建前序遍历输出字符串
     *
     * @param node
     * @param depth
     * @param outputStr
     */
    private void generatorOutputStr(Node node, int depth, StringBuilder outputStr) {
        if (node == null) {
            outputStr.append(this.generatorDepthStr(depth).append("null").append("\n"));
            return;
        }

        //本次值打印
        StringBuilder res = outputStr.append(this.generatorDepthStr(depth)).append(node.data).append("\n");
        //打印左叉树
        this.generatorOutputStr(node.left, depth + 1, res);
        //打印右叉树
        this.generatorOutputStr(node.right, depth + 1, res);

    }

    /**
     * 构建根据层级对应的 --分割
     *
     * @param depth
     * @return
     */
    private StringBuilder generatorDepthStr(int depth) {
        StringBuilder depthStr = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            depthStr.append("--");
        }
        return depthStr;
    }

    /**
     * 树节点对象
     */
    private class Node {
        /**
         * 节点数据
         */
        public T data;
        /**
         * 左节点对象
         */
        public Node left;
        /**
         * 右节点对象
         */
        public Node right;

        public Node(T data) {
            this.left = right = null;
            this.data = data;
        }


    }

}
