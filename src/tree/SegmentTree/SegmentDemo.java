package tree.SegmentTree;

public class SegmentDemo {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        SegmentTree2N tree = new SegmentTree2N(nums);
        System.out.println(tree.sumRange(2, 4));
        System.out.println(tree.sumRange(3, 4));
        tree.update(2, 4);// [1,2,4,4,5]
        System.out.println(tree.sumRange(2, 4));
        System.out.println(tree.sumRange(3, 4));

        nums = new int[]{1, 2, 3, 4, 5, 6};
        SegmentTree4N tree2 = new SegmentTree4N(nums);
        System.out.println(tree2.sumRange(0, 2));
        tree2.update(1, 3);// [1,3,3,4,5,6]
        System.out.println(tree2.sumRange(0, 2));

    }
}
