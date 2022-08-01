package tree.QuadTree;

public class Solution {
    public static void main(String[] args) {
        int[][] grid = {{1, 0}, {0, 1}};
        Node tree1 = new ConstructTree().construct(grid);
        System.out.println(tree1.val + " " + tree1.isLeaf);
        System.out.println(tree1.topLeft.val + " " + tree1.topRight.val);
        System.out.println(tree1.bottomLeft.val + " " + tree1.bottomRight.val);
        new Traverse().printTree(new Traverse().traverse(tree1));
        System.out.println();

        grid = new int[][]{{0, 0}, {1, 1}};
        Node tree2 = new ConstructTree().construct(grid);
        System.out.println(tree2.val + " " + tree2.isLeaf);
        System.out.println(tree2.topLeft.val + " " + tree2.topRight.val);
        System.out.println(tree2.bottomLeft.val + " " + tree2.bottomRight.val);
        new Traverse().printTree(new Traverse().traverse(tree2));
        System.out.println();

        Node root = new IntersectTree().intersect(tree1, tree2);
        System.out.println(root.val + " " + root.isLeaf);
        System.out.println(root.topLeft.val + " " + root.topRight.val);
        System.out.println(root.bottomLeft.val + " " + root.bottomRight.val);
        new Traverse().printTree(new Traverse().traverse(root));
        System.out.println();

        grid = new int[][]{{1, 1, 0, 0}, {1, 1, 1, 1}, {1, 1, 1, 1,}, {1, 1, 0, 0}};
        Node tree = new ConstructTree().construct(grid);
        new Traverse().printTree(new Traverse().traverse(tree));

    }
}
