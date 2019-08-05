package com.lonely.堆;

import com.lonely.数组.MyArray;

/**
 * @author ztkj-hzb
 * @Date 2019/7/18 15:48
 * @Description 最大值堆实现, 即根节点是最大值,注意这里因为第一个节点是最大值，所以只能去最大值，复杂度为O(logn)
 */
public class MaxHeap<T extends Comparable<T>> implements Heap<T> {

    private MyArray<T> data;

    public MaxHeap(int capacity) {
        this.data = new MyArray<>(capacity);
    }

    public MaxHeap() {
        this.data = new MyArray<>();
    }

    public MaxHeap(T[] arr) {
        //添加到数组中
        this.data = new MyArray<>(arr);
        //将数组中的元素根据二叉堆的特性进行重组
        //怎么重组：
        //1. 第一种方法，将数组的元素，依次使用 add方法添加到堆中，这种复杂度为 O(nlogn)
        //2. 第二种方法，依次从最后一个非叶子节点开始，向下重组，然后依次向上找，到第一个元素，该复杂度为 O(n),这里使用第二种方法

        //首先找到 最后一个非叶子节点的元素索引
        int startIndex = this.parent(this.data.getSize() - 1);

        //依次循环操作
        for (int i = startIndex; i >= 0; i--) {
            //每次执行下调操作
            this.siftDown(i);
        }


    }

    /**
     * 获取堆中节点数量
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.data.getSize();
    }


    /**
     * 判断堆中节点是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }


    /**
     * 添加元素
     *
     * @param t
     */
    @Override
    public void add(T t) {
        //先添加元素，在根据特性移动位置到合适的位置
        this.data.addLast(t);
        //移动元素
        this.siftUp(this.data.getSize() - 1);

    }

    /**
     * 获取最大值，即第一个节点的值
     */
    public T findMax() {
        return this.data.getFirst();
    }


    /**
     * 移出最大值
     */
    public T extraceMax() {
        if (this.getSize() == 0) {
            //没有元素，直接抛出异常
            throw new RuntimeException("当前堆中无元素，无法删除最大值");
        }

        T maxData = this.findMax();

        //处理删除头节点之后的操作
        //首先，将最后一个节点挪到待删除的头节点
        this.data.set(0, this.data.getLast());
        this.data.removeLast();

        //然后，从第一个开始依次向下判断处理
        this.siftDown(0);

        return maxData;

    }


    /**
     * 根据规则将值移动到合适的位置(向上浮动)
     *
     * @param index
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parent = this.parent(index);
            if (this.data.get(parent).compareTo(this.data.get(index)) < 0) {
                //父节点比当前节点还要小，需要再次移动
                this.data.swap(index, parent);
                index = parent;
            } else {
                //找到了合适的位置，直接退出即可
                break;
            }
        }
    }

    /**
     * 向下移动位置
     *
     * @param index
     */
    private void siftDown(int index) {
        while (index < this.getSize() && this.leftChild(index) < this.getSize()) {
            //获取左右索引的对象值
            T leftChildData = this.data.get(this.leftChild(index));

            //当前索引的值
            T currData = this.data.get(index);

            //没有右节点
            if (this.rightChild(index) == this.getSize()) {
                if (currData.compareTo(leftChildData) > 0) {
                    //目前节点 大于 其左节点的值，无序变动，直接返回即可
                    break;
                } else {
                    this.data.swap(index, this.leftChild(index));
                    index = this.leftChild(index);
                }


            } else {
                //存在右节点，右节点的值
                T rightChildData = this.data.get(this.rightChild(index));
                if (currData.compareTo(leftChildData) > 0 && currData.compareTo(rightChildData) > 0) {
                    //目前节点 大于 其左右节点的值，无序变动，直接返回即可
                    break;
                }

                //比较左右子节点最大的那个，与其交换位置
                if (leftChildData.compareTo(rightChildData) > 0) {
                    //左边节点值大，跟左侧节点交换位置
                    this.data.swap(index, this.leftChild(index));
                    index = this.leftChild(index);
                } else {
                    //右边节点值大， 跟右侧节点交换位置
                    this.data.swap(index, this.rightChild(index));
                    index = this.rightChild(index);
                }

            }


        }
    }


    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append("*************** MaxHeap:[");
        message.append(this.data.toString());
        message.append("]***************");
        return message.toString();
    }

    /**
     * 获取指定索引对应的父节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index <= 0) {
            throw new RuntimeException("index <= 0 exception!");
        }
        return (index - 1) / 2;
    }

    /**
     * 获取指定索引对应的节点的左孩子节点索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 获取指定索引对应的节点的右孩子节点索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }


    /**
     * 将最大值替换成指定值
     *
     * @param t
     * @return
     */
    public T replace(T t) {
        //先获取最大值
        T maxData = this.findMax();

        //替换第一个节点，然后进行向下调整
        this.data.set(0, t);
        this.siftDown(0);

        return maxData;
    }

}
