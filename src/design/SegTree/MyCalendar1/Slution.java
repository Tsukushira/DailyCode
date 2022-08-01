package design.SegTree.MyCalendar1;

public class Slution {
    public static void main(String[] args) {
        MyCalendar1Set set = new MyCalendar1Set();
        System.out.println(set.book(10, 20));
        System.out.println(set.book(50, 60));
        System.out.println(set.book(10, 40));// false
        System.out.println(set.book(20, 40));

        System.out.println();

        MyCalendar1BST tree = new MyCalendar1BST();
        System.out.println(tree.book(10, 20));
        System.out.println(tree.book(50, 60));
        System.out.println(tree.book(10, 40));// false
        System.out.println(tree.book(20, 40));

        System.out.println();

        MyCalendar1SegTree segTree = new MyCalendar1SegTree();
        System.out.println(segTree.book(10, 20));
        System.out.println(segTree.traverse());
        System.out.println(segTree.book(50, 60));
        System.out.println(segTree.traverse());
        System.out.println(segTree.book(10, 40));// false
        System.out.println(segTree.traverse());
        System.out.println(segTree.book(20, 40));
        System.out.println(segTree.traverse());
    }
}
