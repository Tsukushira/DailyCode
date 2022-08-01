package tree.QuadTree;

import java.util.*;

public class Traverse {
    /**
     * 四叉树 的 层次遍历
     *
     * @param root
     * @return
     */
    public List<List<int[]>> traverse(Node root) {
        List<List<int[]>> res = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<int[]> tmp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                tmp.add(new int[]{node.val ? 1 : 0, node.isLeaf ? 1 : 0});
                if (node.topLeft != null) {
                    queue.offer(node.topLeft);
                }
                if (node.topRight != null) {
                    queue.offer(node.topRight);
                }
                if (node.bottomLeft != null) {
                    queue.offer(node.bottomLeft);
                }
                if (node.bottomRight != null) {
                    queue.offer(node.bottomRight);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    /**
     * 打印遍历结果
     *
     * @param tree
     */
    public void printTree(List<List<int[]>> tree) {
        for (List<int[]> arrs : tree) {
            for (int i = 0; i < arrs.size(); i++) {
                System.out.print(Arrays.toString(arrs.get(i)));
                if (i != arrs.size() - 1 && (i + 1) % 4 == 0) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
