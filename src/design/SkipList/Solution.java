package design.SkipList;

public class Solution {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();

        skipList.add(1);
        skipList.add(2);
        skipList.add(3);

        System.out.println(skipList.search(0));
        System.out.println(skipList.search(1));
        System.out.println(skipList.search(2));
        System.out.println(skipList.search(3));
        System.out.println();
        System.out.println(skipList.erase(3));
        System.out.println(skipList.search(3));
        System.out.println(skipList.search(2));
    }
}
