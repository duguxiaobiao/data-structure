package com.lonely.树.线段树;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/7/25 13:51
 * @Description 力扣303题
 */
public class TestLeeCode303 {


    @Test
    public void test() {
        //int[] nums = {-2, 0, 3, -5, 2, -1};
        /*int[] nums = {};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 0));
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));*/


        int[] nums = new int[10000000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int)(Math.random()*100);
        }
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, nums.length-1));
    }


    private class NumArray {

        private int[] treeArray;

        private int[] numsArray;

        public NumArray(int[] nums) {
            this.numsArray = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                this.numsArray[i] = nums[i];
            }

            this.treeArray = new int[nums.length * 4];

            //构建线段树
            if (this.numsArray.length > 0) {
                this.segmentBuild(0, 0, this.numsArray.length - 1);
            }
        }

        //构建线段树
        private void segmentBuild(int startIndex, int left, int right) {

            if (left == right) {
                //叶子节点
                this.treeArray[startIndex] = this.numsArray[left];
                return;
            }

            //获取当前根节点的左叉树节点
            int leftChildIndex = 2 * startIndex + 1;
            int rightChildIndex = 2 * startIndex + 2;

            //区间中间点
            int midIndex = left + (right - left) / 2;

            //构建左叉树
            this.segmentBuild(leftChildIndex, left, midIndex);

            //获取当前节点的右叉树节点
            this.segmentBuild(rightChildIndex, midIndex + 1, right);

            //处理根节点
            this.treeArray[startIndex] = this.treeArray[leftChildIndex] + this.treeArray[rightChildIndex];

        }

        //将制定区间的值汇总
        public int sumRange(int i, int j) {
            if (i < 0 || i >= this.numsArray.length || j < 0 || j >= this.numsArray.length || i > j) {
                throw new RuntimeException("index error");
            }
            return this.sumRange(0, 0, this.numsArray.length - 1, i, j);
        }

        private int sumRange(int startIndex, int left, int right, int queryLeft, int queryRight) {

            if (left == right) {
                return this.treeArray[startIndex];
            }

            //获取当前根节点的左叉树节点
            int leftChildIndex = 2 * startIndex + 1;
            int rightChildIndex = 2 * startIndex + 2;

            //区间中间点
            int midIndex = left + (right - left) / 2;

            //判断区间和制定区间的范围关系
            if (queryLeft >= midIndex + 1) {
                //忽略左叉树
                return this.sumRange(rightChildIndex, midIndex + 1, right, queryLeft, queryRight);
            } else if (queryRight <= midIndex) {
                //忽略右叉树
                return this.sumRange(leftChildIndex, left, midIndex, queryLeft, queryRight);
            } else {
                //在区间之间

                //处理左子树
                int leftVal = this.sumRange(leftChildIndex, left, midIndex, queryLeft, midIndex);
                //处理右子树
                int rightVal = this.sumRange(rightChildIndex, midIndex + 1, right, midIndex + 1, queryRight);

                return leftVal + rightVal;

            }


        }
    }
}
