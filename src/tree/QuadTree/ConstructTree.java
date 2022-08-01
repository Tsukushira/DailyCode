package tree.QuadTree;

/**
 * 427. 建立四叉树
 * <p>
 * 按以下步骤为二维区域构建四叉树：
 * <p>
 * 1.如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，
 * 将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
 * 2.如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值。
 * 3.使用适当的子网格递归每个子节点。
 */
public class ConstructTree {
    int[][] grid;// 使用层序遍历后四叉树的序列化形式

    public Node construct(int[][] grid) {
        this.grid = grid;
        return dfs(0, 0, grid.length, grid.length);
    }

    private Node dfs(int a, int b, int c, int d) {
        int val = grid[a][b];
        boolean same = true;
        for (int i = a; i < c && same; i++) {
            for (int j = b; j < d && same; j++) {
                if (grid[i][j] != val) {
                    same = false;
                }
            }
        }
        if (same) {
            return new Node(val == 1, true);// 叶节点val赋相应的值
        }
        Node root = new Node(true, false);// 非叶节点val都赋值为true
        int dx = c - a, dy = d - b;
        root.topLeft = dfs(a, b, a + dx / 2, b + dy / 2);
        root.topRight = dfs(a, b + dy / 2, a + dx / 2, d);
        root.bottomLeft = dfs(a + dx / 2, b, c, b + dy / 2);
        root.bottomRight = dfs(a + dx / 2, b + dy / 2, c, d);
        return root;
    }

}
