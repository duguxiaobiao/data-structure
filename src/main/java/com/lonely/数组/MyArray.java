package com.lonely.数组;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author ztkj-hzb
 * @Date 2019/6/18 10:23
 * @Description 二次封装数组结构
 */
public class MyArray<T> {

    /**
     * 数组内容
     */
    private T[] data;

    /**
     * 数组实际长度
     */
    private int size;

    public MyArray() {
        this(10);
    }

    public MyArray(int capcity) {
        if (capcity >= 0) {
            this.data = (T[]) new Object[capcity];
        } else {
            throw new IllegalArgumentException(MessageFormat.format("数组构建函数中初始化容量：{0}不能小于0", capcity));
        }
    }


    public MyArray(T[] arr) {
        this.data = (T[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            this.data[i] = arr[i];
        }
        this.size = arr.length;
    }


    /**
     * 获取数组中实际空间大小
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组定义的空间大小
     *
     * @return
     */
    public int getCapcity() {
        return this.data.length;
    }


    /**
     * 当前数组实际长度是否为0
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * 在数组末尾添加元素
     *
     * @param t
     * @return
     */
    public boolean addLast(T t) {
        return this.add(getSize(), t);
    }

    /**
     * 在数组的的起始处插入元素
     *
     * @param t
     * @return
     */
    public boolean addFirst(T t) {
        return this.add(0, t);
    }

    /**
     * 添加元素到数组的指定索引位置
     *
     * @param index
     * @param t
     * @return
     */
    public boolean add(int index, T t) {

        //判断是否需要扩容
        if (whetherExpansionIsNeeded()) {
            //需要扩容
            resize(2 * getCapcity());
        }

        //判断索引是否合格
        if (index < 0 || index >= getCapcity()) {
            throw new IndexOutOfBoundsException(MessageFormat.format("下标:{0}越界，不在范围({1}~{2})中", index, 0, getSize()));
        }

        //插入指定位置，位置后面的元素需要往后移动
        for (int i = getSize(); i > index; i--) {
            this.data[i + 1] = this.data[i];
        }
        this.data[index] = t;
        this.size++;

        return true;
    }

    /**
     * 获取第一个值
     *
     * @return
     */
    public T getFirst() {
        return this.get(0);
    }

    /**
     * 获取最后一个有效值
     *
     * @return
     */
    public T getLast() {
        return this.get(getSize() - 1);
    }

    /**
     * 获取指定元素对应的数据信息
     *
     * @param index
     * @return
     */
    public T get(int index) {
        //校验下标是否越界
        whetherTheSubscriptCrossesTheBoundary(index);

        //获取对应下标的数据
        return this.data[index];
    }

    /**
     * 更新指定索引对应的值
     *
     * @param index
     * @param newData
     */
    public void set(int index, T newData) {
        //校验下标是否越界
        whetherTheSubscriptCrossesTheBoundary(index);

        //重新赋值
        this.data[index] = newData;
    }

    /**
     * 判断指定元素是否在当前的数组中存在
     *
     * @param t
     * @return
     */
    public boolean container(T t) {
        for (int i = 0; i < getSize(); i++) {
            if (this.data[i].equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取指定元素在当前数组中的第一个位置
     *
     * @param t
     * @return
     */
    public int find(T t) {
        for (int i = 0; i < getSize(); i++) {
            if (this.data[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 查询指定元素在当前数组的所有位置  分段分组多线程处理 然后按分组顺序合并,这里写的分组10，只是测试
     *
     * @param t
     * @return
     */
    public Integer[] findAll(T t) {
        //根据数组大小 分组分段处理

        //总分组大小
        final int groupSize = getSize() % 10 == 0 ? getSize() / 10 : getSize() / 10 + 1;

        final Map<Integer, List<T>> groupMap = new HashMap<>();
        for (int i = 0; i < getSize(); i++) {
            int currGroup = i / 10 + 1;
            if (groupMap.containsKey(currGroup)) {
                groupMap.get(currGroup).add(this.data[i]);
            } else {
                //不存在
                List<T> list = new ArrayList<>();
                list.add(this.data[i]);
                groupMap.put(currGroup, list);
            }
        }

        //多线程处理
        ExecutorService pool = new ThreadPoolExecutor(groupSize, 2 * groupSize, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        Map<Integer, List<Integer>> indexGroupMap = new HashMap<>();
        for (int i = 1; i <= groupSize; i++) {
            final int index = i;
            final T newT = t;
            try {
                indexGroupMap.put(i, pool.submit(() -> {
                    List<Integer> indexs = new ArrayList<>();
                    List<T> datas = groupMap.get(index);
                    for (int j = 0; j < datas.size(); j++) {
                        if (datas.get(j).equals(newT)) {
                            //第1组 就是  j  第2组 就是  j + 10
                            indexs.add(j + (index - 1) * 10);
                        }
                    }
                    return indexs;
                }).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            pool.shutdown();
            if (!pool.awaitTermination(5 * 1000, TimeUnit.MILLISECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            pool.shutdownNow();
        }

        List<Integer> indexList = new ArrayList<>();

        for (int i = 1; i <= groupSize; i++) {
            if (indexGroupMap.containsKey(i)) {
                List<Integer> integers = indexGroupMap.get(i);
                if (integers != null && integers.size() > 0) {
                    indexList.addAll(integers);
                }
            }
        }

        return indexList.toArray(new Integer[0]);
    }


    /**
     * 删除指定索引位置的元素
     *
     * @param index
     * @return
     */
    public T remove(int index) {

        //校验索引是否越界
        whetherTheSubscriptCrossesTheBoundary(index);

        T removeElement = this.data[index];

        //删除元素，指定索引后面的数据向前移动
        for (int i = index; i < getSize() - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        size--;

        //判断删除元素后的size是否小于等于 总容量的 1/2，如果是，则缩容
        if (size <= getCapcity() / 2) {
            resize(getCapcity() / 2);
        }
        return removeElement;
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
     * 删除指定元素第一个出现的位置
     *
     * @param t
     */
    public void removeElement(T t) {
        //查找 元素t出现的第一个位置
        int i = this.find(t);
        if (i != -1) {
            this.remove(i);
        }
    }

    /**
     * 批量删除，性能不行
     *
     * @param t
     */
    public void batchRemoveElement(T t) {
        Integer[] allIndexs = this.findAll(t);
        if (allIndexs != null && allIndexs.length > 0) {
            //找到了
            for (int i = 0; i < allIndexs.length; i++) {
                this.remove(allIndexs[i]);
            }
        }
    }

    /**
     * 格式化输出
     *
     * @return
     */
    @Override
    public String toString() {
        //输出格式:[xx,xxx,xx,xxx]
        StringBuilder message = new StringBuilder("[");
        for (int i = 0; i < getSize(); i++) {
            if (i == getSize() - 1) {
                message.append(this.data[i]);
            } else {
                message.append(this.data[i]).append(",");
            }
        }
        message.append("]");

        return message.toString();
    }

    /**
     * 判断是否扩容
     *
     * @return
     */
    private boolean whetherExpansionIsNeeded() {
        return getSize() == getCapcity();
    }

    /**
     * 扩容
     *
     * @param newCapcity
     */
    private void resize(int newCapcity) {
        T[] newData = (T[]) new Object[newCapcity];

        //将原来的数组的值 复制到 新数组中
        for (int i = 0; i < getSize(); i++) {
            newData[i] = this.data[i];
        }

        this.data = newData;
    }

    /**
     * 判断指定下标是否越界
     *
     * @param index
     * @return
     */
    private void whetherTheSubscriptCrossesTheBoundary(int index) {
        //判断索引是否合格
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException(MessageFormat.format("下标:{0}越界，不在范围({1}~{2})中", index, 0, getSize()));
        }
    }


    /**
     * 将两个索引位置的元素交换
     *
     * @param needSwapIndex
     * @param toSwapIndex
     */
    public void swap(int needSwapIndex, int toSwapIndex) {
        if (needSwapIndex < 0 || toSwapIndex < 0 || needSwapIndex > (this.size - 1) || toSwapIndex > (this.size - 1)) {
            throw new RuntimeException("交换索引异常，请检查");
        }
        T data = this.data[needSwapIndex];
        this.data[needSwapIndex] = this.data[toSwapIndex];
        this.data[toSwapIndex] = data;
    }
}
