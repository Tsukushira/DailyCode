package tree.FenwickTree;

public class FenwickDemo {
    public static void main(String[] args) {
        int[] nums = { 1, 3, 5 };
        FenwickTree fenwickTree = new FenwickTree(nums);
        System.out.println(fenwickTree.sumRange(0, 2));
        fenwickTree.update(1, 2);
        System.out.println(fenwickTree.sumRange(0, 2));
        System.out.println(fenwickTree.sumRange(1, 2));
        fenwickTree.update(2, 6);
        System.out.println(fenwickTree.sumRange(2, 2));
    }
}
