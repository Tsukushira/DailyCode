package design.SegTree.MyCalendar2;

public class Solution {
    public static void main(String[] args) {
        MyCalendar2List list = new MyCalendar2List();
        System.out.println(list.book(10, 20));
        System.out.println(list.book(50, 60));
        System.out.println(list.book(10, 40));
        System.out.println(list.book(5, 15));// false
        System.out.println(list.book(5, 10));

        System.out.println();

        MyCalendar2Set set = new MyCalendar2Set();
        System.out.println(set.book(10, 20));
        System.out.println(set.book(50, 60));
        System.out.println(set.book(10, 40));
        System.out.println(set.book(5, 15));// false
        System.out.println(set.book(5, 10));

        System.out.println();

        MyCalendar2SegTree tree = new MyCalendar2SegTree();
        System.out.println(tree.book(10, 20));
        System.out.println(tree.traverse());
        System.out.println(tree.book(50, 60));
        System.out.println(tree.traverse());
        System.out.println(tree.book(10, 40));
        System.out.println(tree.traverse());
        System.out.println(tree.book(5, 15));// false
        System.out.println(tree.traverse());
        System.out.println(tree.book(5, 10));
        System.out.println(tree.book(15, 55));
    }
}
