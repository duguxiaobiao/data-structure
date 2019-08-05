package com.lonely.树.字典树;

import org.junit.Test;

import java.util.TreeMap;

/**
 * @author ztkj-hzb
 * @Date 2019/7/30 11:44
 * @Description 力扣208题
 */
public class TestLeeCode208 {


    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // 返回 true
        System.out.println(trie.search("app"));     // 返回 false
        System.out.println(trie.startsWith("app")); // 返回 true
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 true
    }


    private static class Trie {

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            this.root = new Node();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Node curerNode = this.root;
            for (int i = 0; i < word.length(); i++) {
                Character curr = word.charAt(i);
                if (!curerNode.next.containsKey(curr)) {
                    curerNode.next.put(curr, new Node());
                }
                curerNode = curerNode.next.get(curr);
            }
            if (!curerNode.isWord) {
                curerNode.isWord = true;
            }
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Node currNode = this.root;
            for (int i = 0; i < word.length(); i++) {
                Character curr = word.charAt(i);
                if (!currNode.next.containsKey(curr)) {
                    return false;
                }
                currNode = currNode.next.get(curr);
            }
            return currNode.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            Node currNode = this.root;
            for (int i = 0; i < prefix.length(); i++) {
                Character curr = prefix.charAt(i);
                if (!currNode.next.containsKey(curr)) {
                    return false;
                }
                currNode = currNode.next.get(curr);
            }
            return true;
        }

        private static class Node {

            public boolean isWord;

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


}
