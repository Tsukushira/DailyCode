package tree.QuadTree;

/**
 * 558. 四叉树交集
 * 对两个四叉树进行 或运算 ，返回运算后的四叉树的根节点
 */
public class IntersectTree {
    public Node intersect(Node tree1, Node tree2) {
        if (tree1.isLeaf) {
            if (tree1.val) {
                return new Node(true, true);
            }
            return new Node(tree2.val, tree2.isLeaf, tree2.topLeft, tree2.topRight, tree2.bottomLeft, tree2.bottomRight);
        }
        if (tree2.isLeaf) {
            return intersect(tree2, tree1);
        }
        Node o1 = intersect(tree1.topLeft, tree2.topLeft);
        Node o2 = intersect(tree1.topRight, tree2.topRight);
        Node o3 = intersect(tree1.bottomLeft, tree2.bottomLeft);
        Node o4 = intersect(tree1.bottomRight, tree2.bottomRight);
        if (o1.isLeaf && o2.isLeaf && o3.isLeaf && o4.isLeaf && o1.val == o2.val && o1.val == o3.val && o1.val == o4.val) {
            return new Node(o1.val, true);
        }
        return new Node(true, false, o1, o2, o3, o4);// 非叶节点val都赋值为true
    }
}
