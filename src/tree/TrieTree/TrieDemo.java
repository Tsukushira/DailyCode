package tree.TrieTree;

public class TrieDemo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] words = { "apple", "append", "applet", "bcdef" };

        for (String word : words) {
            trie.insert(word);
        }

        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("bcdef"));
        System.out.println(trie.startWith("abc"));
        System.out.println(trie.startWith("app"));
    }
}
