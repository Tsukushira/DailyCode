package design.MagicDictionary.TireTree;

/**
 * 676. 实现一个魔法字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 */
public class MagicDictionary {
    private Tire root;

    MagicDictionary() {
        root = new Tire();
    }

    /**
     * 构建字典。
     * 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同。
     *
     * @param dictionary 数组内字符串都是由小写字母组成
     */
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            Tire tire = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (tire.child[idx] == null) {
                    tire.child[idx] = new Tire();
                }
                tire = tire.child[idx];
            }
            tire.isEnd = true;
        }
    }

    /**
     * 搜索字符串。
     * 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
     * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true，否则返回 false 。
     *
     * @param searchWord
     * @return
     */
    public boolean search(String searchWord) {
        return dfs(searchWord, root, 0, false);
    }

    /**
     * 递归搜索字符串
     *
     * @param word
     * @param tire
     * @param idx
     * @param modified
     * @return
     */
    private boolean dfs(String word, Tire tire, int idx, boolean modified) {
        if (idx == word.length()) {
            return modified && tire.isEnd;
        }
        int pos = word.charAt(idx) - 'a';
        if (tire.child[pos] != null) {
            if (dfs(word, tire.child[pos], idx + 1, modified)) {
                return true;
            }
        }
        if (!modified) {
            for (int i = 0; i < 26; i++) {
                if (i != pos && tire.child[i] != null) {
                    if (dfs(word, tire.child[i], idx + 1, true)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

class Tire {
    Tire[] child;
    boolean isEnd;

    Tire() {
        child = new Tire[26];
        isEnd = false;
    }
}
