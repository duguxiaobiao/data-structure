package com.lonely.树.字典树;

import org.junit.Test;

import java.util.TreeMap;

/**
 * @author ztkj-hzb
 * @Date 2019/7/30 14:28
 * @Description 力扣211题
 */
public class TestLeetCode211_升级 {


    @Test
    public void test() {

        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); //-> false
        System.out.println(wordDictionary.search("bad")); //-> true
        System.out.println(wordDictionary.search(".ad")); //-> true
        System.out.println(wordDictionary.search("b..")); //-> true

    }


    class WordDictionary {

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            this.root = new Node();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            Node currNode = this.root;
            for (int i = 0; i < word.length(); i++) {
                Character curr = word.charAt(i);

                if(currNode.nexts[curr-'a'] == null){
                    currNode.nexts[curr-'a'] = new Node();
                }
                currNode = currNode.nexts[curr-'a'];
            }
            currNode.isWord = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return this.match(this.root, word, 0);
        }

        /**
         * 判断单词是否匹配
         *
         * @param currNode 指定当前节点
         * @param word     带判断的哦字符串
         * @param index    带判读的字符串的执行第n个字段的指针
         * @return
         */
        private boolean match(Node currNode, String word, int index) {

            if (word.length() == index) {
                //最后一次
                return currNode.isWord;
            }

            //判断当前待处理的字符是否是 .
            Character curr = word.charAt(index);
            if (curr != '.') {
                if (currNode.nexts[curr-'a'] == null) {
                    return false;
                }
                return this.match(currNode.nexts[curr-'a'], word, index + 1);
            } else {
                //是 .
                for (Node next : currNode.nexts) {
                    if(next != null && this.match(next,word,index+1)){
                        return true;
                    }
                }
                return false;
            }
        }


        class Node {
            public boolean isWord;

            public Node[] nexts;

            public Node(boolean isWord) {
                this.isWord = isWord;
                nexts = new Node[26];
            }

            public Node() {
                this(false);
            }
        }


    }


}
