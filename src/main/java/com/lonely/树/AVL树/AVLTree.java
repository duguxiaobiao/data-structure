package com.lonely.树.AVL树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author ztkj-hzb
 * @Date 2019/6/25 16:43
 * @Description 自平衡树
 */
public class AVLTree<T extends Comparable<T>> {

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
        this.add(this.root, t);
    }


    /**
     * 递归添加元素
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

        //设置高度 = 左右子树最高的 + 1
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        //计算平衡因子 = 左叉树的高度 - 右叉树的高度 的绝对值 <= 1
        node.balanceFactor = this.getBalanceFactor(node);
        /*if (node.balanceFactor > 1) {
            //不再是avl树
            System.out.println("不满足平衡二叉树，此时平衡因子为:" + node.balanceFactor);
        }*/


        //LL
        if (node.balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            //这个 >=0 的意思是 左节点也是 左叉树>=右叉树，表明趋势是向左，因此需要右旋转
            return this.rightRotation(node);
        }

        //RR
        if (node.balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            //需要左旋转
            return this.leftRotation(node);
        }

        //LR
        if (node.balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            //1.先对左节点进行左旋转，然后对根节点做右旋转
            node.left = this.leftRotation(node.left);
            return this.rightRotation(node);
        }

        //RL
        if (node.balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = this.rightRotation(node.right);
            return this.leftRotation(node);
        }

        return node;
    }


    /**
     * 右旋转
     * 1.获取不平衡节点的左节点，设置左节点的右叉树为当前的不平衡节点
     * 2.将原左节点的右叉树设置为不平衡节点的左叉树
     *
     * @param node
     */
    private Node rightRotation(Node node) {

        if (node == null) {
            return null;
        }

        //不平衡节点的左节点
        Node leftNode = node.left;
        //左节点的右节点
        Node rightNodeByLeftNode = leftNode.right;

        //旋转
        leftNode.right = node;
        node.left = rightNodeByLeftNode;

        //重新计算高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        leftNode.height = Math.max(getHeight(leftNode.left), getHeight(leftNode.right)) + 1;

        return leftNode;
    }


    /**
     * 左旋转
     *
     * @param node
     * @return
     */
    private Node leftRotation(Node node) {

        if (node == null) {
            return null;
        }

        //新的根节点
        Node rightNode = node.right;
        //待处理的左叉树
        Node leftNodeByRightNode = rightNode.left;

        //左旋转
        rightNode.left = node;
        node.right = leftNodeByRightNode;

        //重新计算高度
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        rightNode.height = Math.max(getHeight(rightNode.left), getHeight(rightNode.right)) + 1;

        return rightNode;
    }


    /**
     * 判断当前树是否是二分搜索树
     *
     * @return
     */
    public boolean isBstTree() {
        return this.isBstTree(this.root);
    }


    /**
     * 校验是否是二分搜索树：中序遍历后，应该是升序，所以比较是否是升序
     *
     * @param node
     * @return
     */
    private boolean isBstTree(Node node) {
        List<T> values = new ArrayList<>();
        this.inOrder(node, values);

        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i).compareTo(values.get(i + 1)) > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * 中序排序，将值添加到集合中
     *
     * @param node
     * @param values
     */
    private void inOrder(Node node, List<T> values) {

        if (node == null) {
            return;
        }

        inOrder(node.left, values);
        values.add(node.data);
        inOrder(node.right, values);
    }


    /**
     * 判断当前树是否是AVL树
     *
     * @return
     */
    public boolean isAvlTree() {
        return this.isAvlTree(this.root);
    }

    /**
     * 判断指定节点对应的树是否是平衡二叉树:校验平衡因子应该小于等于1
     *
     * @param node
     * @return
     */
    private boolean isAvlTree(Node node) {
        if (node == null) {
            return true;
        }

        //查看当前节点的平衡因子，是否满足要求
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            //如果大于1，则直接返回，不满足平衡二叉树
            return false;
        }
        //当前节点满足，则查看当前的节点的左右节点
        return isAvlTree(node.left) && isAvlTree(node.right);
    }

    /**
     * 获取指定节点的高度
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }


    /**
     * 获取指定节点的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
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


        Node resNode = null;

        if (t.compareTo(node.data) < 0) {
            node.left = remove(node.left, t);
            resNode = node;
        } else if (t.compareTo(node.data) > 0) {
            node.right = remove(node.right, t);
            resNode = node;
        } else {
            //找到了元素
            //判断待删除的元素是否只存在左节点，右节点，还是左右都有
            if (node.left == null) {
                //最小值
                Node right = node.right;
                node.right = null;
                this.size--;
                resNode = right;
            } else if (node.right == null) {
                //最大值
                Node left = node.left;
                node.left = null;
                this.size--;
                resNode = left;
            } else {
                //存在左右节点的
                //第一步：找到右子树的最小值的节点
                Node successorNode = this.minData(node.right);

                //第二步：删除右子树最小值节点,返回的节点即最新的带替换的节点
                //Node removeMinNode = this.removeMinNode(node.right);
                Node removeMinNode = this.remove(node.right,successorNode.data);
                successorNode.left = node.left;
                successorNode.right = removeMinNode;

                node.left = node.right = null;

                resNode = successorNode;
            }
        }

        if(resNode == null){
            return null;
        }

        //设置高度 = 左右子树最高的 + 1
        resNode.height = Math.max(getHeight(resNode.left), getHeight(resNode.right)) + 1;

        //计算平衡因子 = 左叉树的高度 - 右叉树的高度 的绝对值 <= 1
        resNode.balanceFactor = this.getBalanceFactor(resNode);

        //LL
        if (resNode.balanceFactor > 1 && getBalanceFactor(resNode.left) >= 0) {
            //这个 >=0 的意思是 左节点也是 左叉树>=右叉树，表明趋势是向左，因此需要右旋转
            return this.rightRotation(resNode);
        }

        //RR
        if (resNode.balanceFactor < -1 && getBalanceFactor(resNode.right) <= 0) {
            //需要左旋转
            return this.leftRotation(resNode);
        }

        //LR
        if (resNode.balanceFactor > 1 && getBalanceFactor(resNode.left) < 0) {
            //1.先对左节点进行左旋转，然后对根节点做右旋转
            resNode.left = this.leftRotation(resNode.left);
            return this.rightRotation(resNode);
        }

        //RL
        if (resNode.balanceFactor < -1 && getBalanceFactor(resNode.right) > 0) {
            resNode.right = this.rightRotation(resNode.right);
            return this.leftRotation(resNode);
        }

        return resNode;

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

        /**
         * 高度,叶子节点高度为 1
         */
        public int height;

        /**
         * 平衡因子
         */
        public int balanceFactor;

        public Node(T data) {
            this.left = right = null;
            this.data = data;
            this.height = 1;
        }


    }

}
