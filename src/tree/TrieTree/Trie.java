package tree.TrieTree;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树
 */
public class Trie {
    private Map<Character, Trie> child;
    private boolean isEnd;

    Trie() {
        child = new HashMap<>();
    }

    public void insert(String word) {
        Trie trie = this;
        for(char ch : word.toCharArray()) {
            trie.child.putIfAbsent(ch, new Trie());
            trie = trie.child.get(ch);
        }
        trie.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = this;
        for(char ch : word.toCharArray()) {
            if(!trie.child.containsKey(ch)) {
                return false;
            }
            trie = trie.child.get(ch);
        }
        return trie.isEnd;
    }

    public boolean startWith(String str) {
        Trie trie = this;
        for(char ch : str.toCharArray()) {
            if(!trie.child.containsKey(ch)) {
                return false;
            }
            trie = trie.child.get(ch);
        }
        return true;
    }

}
