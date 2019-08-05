package com.lonely.映射;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/7/17 11:06
 * @Description
 */
public class LinkedListMapTest {

    LinkedListMap<String,String> linkedListMap = new LinkedListMap<>();

    @Test
    public void put() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);
    }

    @Test
    public void remove() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);

        this.linkedListMap.remove("bb");
        System.out.println(this.linkedListMap);

        this.linkedListMap.remove("aa");
        System.out.println(this.linkedListMap);
    }

    @Test
    public void container() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);

        System.out.println(linkedListMap.container("bb"));
        System.out.println(linkedListMap.container("aa"));

    }

    @Test
    public void get() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);

        System.out.println(this.linkedListMap.get("bb"));
        System.out.println(this.linkedListMap.get("aa"));
    }

    @Test
    public void set() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);

        //this.linkedListMap.set("bb","affda");
        this.linkedListMap.set("aa","dfafda");
        System.out.println(this.linkedListMap);
    }

    @Test
    public void isEmpty() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);

        System.out.println(linkedListMap.isEmpty());
    }

    @Test
    public void getSize() {
        this.linkedListMap.put("aa","fafda");
        System.out.println(this.linkedListMap);

        System.out.println(linkedListMap.getSize());
    }
}