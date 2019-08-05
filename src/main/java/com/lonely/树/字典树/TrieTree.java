package com.lonely.树.字典树;

import java.util.List;
import java.util.TreeMap;

/**
 * @author ztkj-hzb
 * @Date 2019/7/29 18:31
 * @Description 字典树, 多用于字符串统计，例如分词器等
 */
public class TrieTree {

    /**
     * 根节点
     */
    private Node root;
    /**
     * 当前字典树中一个有多少个单词
     */
    private int size;

    public TrieTree() {
        this.root = new Node();
        this.size = 0;
    }


    /**
     * 待添加的单词
     *
     * @param addWord
     */
    public void add(String addWord) {
        Node currNode = root;
        for (int i = 0; i < addWord.length(); i++) {
            Character curr = addWord.charAt(i);
            if (!currNode.next.containsKey(curr)) {
                currNode.next.put(curr, new Node());
            }
            currNode = currNode.next.get(curr);
        }

        //判断最后一个节点中是否已经是一个单词的末尾，如果是，说明改单词已存在，不添加
        if (!currNode.isWord) {
            currNode.isWord = true;
            this.size++;
        }
    }


    /**
     * 判断是否包含某个单词
     *
     * @param word
     * @return
     */
    public boolean contains(String word) {

        if (word == null) {
            throw new RuntimeException("入参word不能为空");
        }

        Node currNode = this.root;
        for (int i = 0; i < word.length(); i++) {
            Character curr = word.charAt(i);
            if (!currNode.next.containsKey(curr)) {
                return false;
            }
            currNode = currNode.next.get(curr);
        }

        //判断最后一个节点是否已标识是一个单词
        return currNode.isWord;

    }


    /**
     * 判断当前tree中是否存在以  preword为前缀的单词
     *
     * @param preWord
     * @return
     */
    public boolean isPrefix(String preWord) {
        if (preWord == null) {
            throw new RuntimeException("入参 preWord不能为空");
        }

        Node currNode = this.root;
        for (int i = 0; i < preWord.length(); i++) {
            Character curr = preWord.charAt(i);
            if (!currNode.next.containsKey(curr)) {
                return false;
            }
            currNode = currNode.next.get(curr);
        }
        return true;

    }


    public int getSize() {
        return this.size;
    }


    private static class Node {
        /**
         * 是否是一个单词的末尾
         */
        public boolean isWord;
        /**
         * 该节点下的n个子节点
         */
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

}
