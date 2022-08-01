package design.TireTree.WordFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * 745. 前缀和后缀搜索
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * <p>
 * 实现 WordFilter 类：
 * <p>
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀prefix和后缀 suff的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 */
public class WordFilter {
    Tire tire;

    public WordFilter(String[] words) {
        tire = new Tire();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int n = word.length();
            Tire cur = tire;
            for (int j = 0; j < n; j++) {
                Tire tmp = cur;
                for (int k = j; k < n; k++) {
                    String s = word.charAt(k) + "#";
                    tmp.child.putIfAbsent(s, new Tire());
                    tmp = tmp.child.get(s);
                    tmp.idx = i;
                }
                tmp = cur;
                for (int k = j; k < n; k++) {
                    String s = "#" + word.charAt(n - k - 1);
                    tmp.child.putIfAbsent(s, new Tire());
                    tmp = tmp.child.get(s);
                    tmp.idx = i;
                }
                String s = String.valueOf(word.charAt(j)) + word.charAt(n - j - 1);
                cur.child.putIfAbsent(s, new Tire());
                cur = cur.child.get(s);
                cur.idx = i;
            }
        }
    }

    public int f(String pref, String suff) {
        Tire cur = tire;
        int n = Math.max(pref.length(), suff.length());
        for (int i = 0; i < n; i++) {
            char a = i < pref.length() ? pref.charAt(i) : '#';
            char b = i < suff.length() ? suff.charAt(suff.length() - i - 1) : '#';
            String s = String.valueOf(a) + b;
            if (!cur.child.containsKey(s)) {
                return -1;
            }
            cur = cur.child.get(s);
        }
        return cur.idx;
    }
}

class Tire {
    Map<String, Tire> child;
    int idx;

    Tire() {
        child = new HashMap<>();
    }
}
