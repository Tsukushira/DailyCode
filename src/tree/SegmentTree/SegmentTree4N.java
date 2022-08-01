package tree.SegmentTree;

/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个数组 nums ，请完成两类查询。 其中一类查询要求 更新 数组nums下标对应的值。
 * 另一类查询要求返回数组nums中索引left和索引right之间（包含）的nums元素的和，其中left <= right。
 */
public class SegmentTree4N {
    int n;
    int[] tree;
    int[] nums;

    SegmentTree4N(int[] nums) {
        n = nums.length;
        this.nums = nums;
        tree = new int[4 * n]; // 递归建树，需要4N的数组空间
        buildTree(0, 0, n - 1);
    }

    public void update(int idx, int val) {
        change(0, idx, val, 0, n - 1);
    }

    public int sumRange(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    /**
     * 递归建树
     */
    private void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        int mid = (start + end) >> 1;
        int left = node * 2 + 1;
        int right = node * 2 + 2;
        buildTree(left, start, mid);
        buildTree(right, mid + 1, end);
        tree[node] = tree[left] + tree[right];
    }

    /**
     * 更新线段树
     *
     * @param start,end
     * @param
     * @param node
     * @param idx       更新nums[idx] 为 val
     * @param val
     */
    private void change(int node, int idx, int val, int start, int end) {
        if (start > end) return;
        if (start == end) {
            tree[node] = val;
            // nums[idx] = val;// 可以不用改，题目不验证数组是否修改
        } else {
            int mid = (start + end) >> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            if (idx >= start && idx <= mid) {
                change(left, idx, val, start, mid);
            } else {
                change(right, idx, val, mid + 1, end);
            }
            tree[node] = tree[left] + tree[right];
        }
    }

    /**
     * 计算区间和
     * @param L,R       查询范围 L ~ R
     * @param node      当前节点
     * @param start,end 当前节点覆盖的范围 start ~ end
     * @return L ~ R 的区间和
     */
    private int query(int node, int start, int end, int L, int R) {
        if (L > end || R < start) return 0;
        if (start == end) return tree[node];
        if (L <= start && end <= R) {
            return tree[node];
        } else {
            int mid = (start + end) >> 1;
            int left = node * 2 + 1;
            int right = node * 2 + 2;
            return query(left, start, mid, L, R) + query(right, mid + 1, end, L, R);
        }
    }
}
