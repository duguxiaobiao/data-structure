package com.lonely.哈希表;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/8/14 15:24
 * @Description 哈希表测试
 */
public class HashTableTest {

    @Test
    public void put() {

        int m = 10000;
        HashTable<Integer, Integer> hashTable = new HashTable<>(200);

        Random random = new Random();

        for (int i = 0; i < m; i++) {
            int nextInt = random.nextInt(m);
            hashTable.put(nextInt, nextInt);
        }

        for (int i = 0; i < m; i++) {
            int nextInt = random.nextInt(m);
            System.out.println(MessageFormat.format("指定key：{0}是否存在hashTable中:{1}", nextInt, hashTable.container(nextInt)));
        }



    }
}