package design.MagicDictionary.TireTree;

public class Solution {
    public static void main(String[] args) {
        MagicDictionary dict = new MagicDictionary();
        String[] dictionary = {"hello", "leetcode"};
        dict.buildDict(dictionary);
        System.out.println(dict.search("hallo"));
        System.out.println(dict.search("hello"));
        System.out.println(dict.search("leetcod"));
        System.out.println(dict.search("leetcodd"));

    }
}
