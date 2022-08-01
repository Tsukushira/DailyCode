package design.TireTree.WordFilter;

public class Solution {
    public static void main(String[] args) {
        String[] words = {"apple", "leetcode"};

        WordFilter wordFilter = new WordFilter(words);

        System.out.println(wordFilter.f("a", "le"));
        System.out.println(wordFilter.f("app", "le"));
        System.out.println(wordFilter.f("a", "el"));
        System.out.println(wordFilter.f("ac", "e"));

        System.out.println(wordFilter.f("le", "e"));
        System.out.println(wordFilter.f("l", "de"));
        System.out.println(wordFilter.f("el", "de"));


    }
}
