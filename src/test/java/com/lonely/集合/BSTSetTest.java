package com.lonely.集合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/7/15 10:01
 * @Description
 */
public class BSTSetTest {

    private BSTSet<Integer> bstSet = new BSTSet<>();

    private void init() {
        List<String> randomNums = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int randomNum = (int) (Math.random() * 10);
            randomNums.add(String.valueOf(randomNum));
            bstSet.add(randomNum);
        }
        System.out.println("init data：" + String.join(",", randomNums));
    }


    /**
     * 测试基础功能
     */
    @Test
    public void testBaseOperation() {

        this.init();
        System.out.println(this.bstSet.toString());
    }
}