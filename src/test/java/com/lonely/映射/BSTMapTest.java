package com.lonely.映射;

import org.junit.Test;

/**
 * @author ztkj-hzb
 * @Date 2019/7/17 17:33
 * @Description
 */
public class BSTMapTest {

    /**
     * 添加元素
     */
    @Test
    public void put() {
        BSTMap<String, String> bstMap = new BSTMap<>();
        bstMap.put("aa", "sfad");
        System.out.println(bstMap);
    }

    @Test
    public void remove() {
        BSTMap<String, String> bstMap = new BSTMap<>();
        bstMap.put("aa", "sfad");
        System.out.println(bstMap);

        System.out.println(bstMap.remove("bb"));
        System.out.println(bstMap);

        System.out.println(bstMap.remove("aa"));
        System.out.println(bstMap);

    }

    @Test
    public void container() {
        BSTMap<String, String> bstMap = new BSTMap<>();
        bstMap.put("aa", "sfad");

        System.out.println(bstMap.container("bb"));
        System.out.println(bstMap.container("aa"));
    }

    @Test
    public void get() {

        BSTMap<String, String> bstMap = new BSTMap<>();
        bstMap.put("aa", "sfad");

        System.out.println(bstMap.get("bb"));
        System.out.println(bstMap.get("aa"));

    }

    @Test
    public void set() {

        BSTMap<String, String> bstMap = new BSTMap<>();
        bstMap.put("aa", "sfad");
        System.out.println(bstMap);

        //bstMap.set("bb","fdafda");
        bstMap.set("aa", "fdafda");
        System.out.println(bstMap);

    }

    @Test
    public void isEmpty() {
        BSTMap<String, String> bstMap = new BSTMap<>();
        System.out.println(bstMap.isEmpty());

        bstMap.put("aa", "sfad");
        System.out.println(bstMap.isEmpty());

    }

    @Test
    public void getSize() {
        BSTMap<String, String> bstMap = new BSTMap<>();
        System.out.println(bstMap.getSize());

        bstMap.put("aa", "sfad");
        System.out.println(bstMap.getSize());
    }
}