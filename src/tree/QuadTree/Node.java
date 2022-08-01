package tree.QuadTree;

public class Node {
    boolean val;// 结点所代表的区域的值，当isLeaf为False时，可以把val赋值为True或者False。
    boolean isLeaf;// 节点是否为叶子节点。
    Node topLeft;
    Node topRight;
    Node bottomLeft;
    Node bottomRight;

    Node() {
        val = false;
        isLeaf = false;
        topLeft = null;
        topRight = null;
        bottomLeft = null;
        bottomRight = null;
    }

    Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        topLeft = null;
        topRight = null;
        bottomLeft = null;
        bottomRight = null;
    }

    Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
