package com.lonely.树.线段树;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/7/25 10:07
 * @Description
 */
public class SegmentTreeTest {

    @Test
    public void testBase() {

        Integer[] nums = {1, 2, 3, 4, 5, 6};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree.toString());

    }


    @Test
    public void testQuery(){
        Integer[] nums = {1, 2, 3, 4, 5, 6};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree.toString());


        System.out.println(segmentTree.query(0,1));
        System.out.println(segmentTree.query(0,5));
        System.out.println(segmentTree.query(2,3));
        System.out.println(segmentTree.query(0,0));

    }


    @Test
    public void testUpdate(){

        Integer[] nums = {1, 2, 3, 4, 5, 6};

        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree.toString());

        segmentTree.update(0,5);
        System.out.println(segmentTree.toString());

    }


}