package com.lonely.堆;

import com.lonely.数组.MyArray;

/**
 * @author ztkj-hzb
 * @Date 2019/7/19 16:49
 * @Description 最小堆, 跟最大堆实现类似，注意有时候不一定非要使用最小堆，可以重新最大堆中的对象的优先级比较方法
 */
public class MinHeap<T extends Comparable<T>> implements Heap<T> {

    private MyArray<T> myArray;

    public MinHeap() {
        this.myArray = new MyArray<>();
    }

    public MinHeap(int capacity) {
        this.myArray = new MyArray<>(capacity);
    }

    /**
     * 接收一个数组，然后重排序
     * @param arr
     */
    public MinHeap(T[] arr) {
        this.myArray = new MyArray<>(arr);

        //排序，生成满足二叉堆的特点

        //1.获取最后一个叶子节点的父节点，从该节点开始向下调整，调整完后再向上调整，依次到索引为0
        int lastParentIndex = this.parentIndex(this.getSize()-1);
        for (int i = lastParentIndex; i >=0 ; i--) {
            this.siftDown(i);
        }
    }


    /**
     * 获取堆中元素长度
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.myArray.getSize();
    }

    /**
     * 判断当前堆中是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return this.myArray.isEmpty();
    }

    /**
     * 添加元素
     *
     * @param t
     */
    @Override
    public void add(T t) {

        //1. 先添加到最后一个节点
        this.myArray.addLast(t);

        //2. 依次向上比较和移动
        this.siftUp(this.getSize() - 1);
    }


    /**
     * 获取最小值，即根节点的值
     *
     * @return
     */
    public T findMin() {
        if (this.isEmpty()) {
            throw new RuntimeException("当前最小堆中没有任何元素，无法获取最小值，请检查..");
        }
        return this.myArray.getFirst();
    }


    /**
     * 移出最小值，输出最小值
     *
     * @return
     */
    public T extractMin() {
        //1.获取最小值
        T minData = this.findMin();

        //2.将最后一个元素放入到根节点，然后向下调整
        this.myArray.set(0, this.myArray.getLast());
        this.myArray.removeLast();
        this.siftDown(0);

        //3. 相应获取的最小值
        return minData;
    }

    /**
     * 向下调整
     *
     * @param index
     */
    private void siftDown(int index) {


        while (index < this.getSize() && this.leftChildIndex(index) < this.getSize()) {

            //获取当前索引的值
            T currData = this.myArray.get(index);

            //获取当前索引的左子节点的值
            int leftIndex = this.leftChildIndex(index);
            T leftData = this.myArray.get(leftIndex);

            //判断是否存在右子节点
            if (this.rightChildIndex(index) >= this.getSize()) {
                //无右子节点,直接让父节点与左子节点比较
                if (currData.compareTo(leftData) < 0) {
                    //无需调整
                    break;
                }
                //调整位置
                this.myArray.swap(index, leftIndex);
                index = leftIndex;
            } else {
                //存在右子节点

                int rightIndex = this.rightChildIndex(index);
                T rightData = this.myArray.get(rightIndex);

                if (currData.compareTo(leftData) < 0 && currData.compareTo(rightData) < 0) {
                    //无需调整
                    break;
                }

                //判断左右子节点的最小值
                if (leftData.compareTo(rightData) < 0) {
                    //左边更小，跟父节点交换
                    this.myArray.swap(index, leftIndex);
                    index = leftIndex;
                } else {
                    //右边更小
                    this.myArray.swap(index, rightIndex);
                    index = rightIndex;
                }
            }

        }


    }


    /**
     * 向上调整
     *
     * @param index
     */
    private void siftUp(int index) {

        while (this.parentIndex(index) >= 0) {
            //当前索引 父节点的值
            T parentData = this.myArray.get(this.parentIndex(index));
            //与当前值比较大小
            if (parentData.compareTo(this.myArray.get(index)) < 0) {
                //满足二叉堆条件
                break;
            }

            //交换位置
            this.myArray.swap(index, this.parentIndex(index));

            //设置最新的index
            index = this.parentIndex(index);
        }
    }


    /**
     * 获取指定索引对应的父节点
     *
     * @param index
     * @return
     */
    private int parentIndex(int index) {
        if (index <= 0) {
            throw new RuntimeException("index<=0 无parentIndex,请检查");
        }

        return (index - 1) / 2;
    }

    /**
     * 获取指定索引对应的左子树节点索引
     *
     * @param index
     * @return
     */
    private int leftChildIndex(int index) {
        return index * 2 + 1;
    }

    /**
     * 获取指定索引对应的右子树节点索引
     *
     * @param index
     * @return
     */
    private int rightChildIndex(int index) {
        return index * 2 + 2;
    }
}
