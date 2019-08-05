package com.lonely.树.线段树;

/**
 * @author ztkj-hzb
 * @Date 2019/7/26 17:37
 * @Description
 */
public class Test {

    public static void main(String[] args) {



    }






    private static class NumArray{

        private SegmentTree<Integer> segmentTree;



        public NumArray(int[] nums) {

            Integer[] datas = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                datas[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(datas,(a,b)->a+b);



        }

        //构建线段树
        private void segmentBuild(int startIndex, int left, int right) {


        }


    }






}
