package com.lonely.树.字典树;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ztkj-hzb
 * @Date 2019/7/30 10:13
 * @Description
 */
public class TrieTreeTest {

    @Test
    public void add() {

        TrieTree trieTree = new TrieTree();
        trieTree.add("hello");
        trieTree.add("helloworld");
        trieTree.add("hello1");
        trieTree.add("hello");

        System.out.println(trieTree.getSize());

    }



    @Test
    public void contains(){
        TrieTree trieTree = new TrieTree();
        trieTree.add("hello");
        trieTree.add("helloworld");
        trieTree.add("hello1");
        trieTree.add("hello");
        System.out.println(trieTree.getSize());

        System.out.println(trieTree.contains("hello"));
        System.out.println(trieTree.contains("hellow"));
        System.out.println(trieTree.contains("hello1"));

    }


    @Test
    public void isPrefix(){

        TrieTree trieTree = new TrieTree();
        trieTree.add("hello");
        trieTree.add("helloworld");
        trieTree.add("hello1");
        trieTree.add("hello");
        System.out.println(trieTree.getSize());

        System.out.println(trieTree.isPrefix("hello"));
        System.out.println(trieTree.isPrefix("hellow"));
        System.out.println(trieTree.isPrefix("hello1"));


    }
}