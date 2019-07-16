package com.lonely.集合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/7/15 11:14
 * @Description
 */
public class LinkedListSetTest {

    private LinkedListSet<Integer> linkedListSet = new LinkedListSet<>();

    private void init() {
        List<String> randomNums = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int random = (int) (Math.random() * 10);
            randomNums.add(String.valueOf(random));
            linkedListSet.add(random);
        }

        System.out.println("init data: " + String.join(",", randomNums));

    }

    /**
     * 测试基础功能
     */
    @Test
    public void testBaseOperation() {
        this.init();
        //测试删除功能
        this.linkedListSet.add(120);
        System.out.println(this.linkedListSet);
        this.linkedListSet.remove(120);
        System.out.println(this.linkedListSet);
    }
}