package tree.FenwickTree;

/**
 * 树状数组 的应用
 * <p>
 * 实现FenwickTree类：
 * FenwickTree(int[] nums) 用整数数组 nums 初始化对象。
 * void update(int idx, int val) 将nums[idx]的值更新为val。
 * int sumRange(int left, int right) 返回数组nums中索引left和索引right之间（包含）的nums元素的和，
 * 即，nums[left] + nums[left +1] + ,..., + nums[right]。
 */
public class FenwickTree {
    int[] tree;
    int len;
    int[] nums;

    FenwickTree(int[] nums) {
        len = nums.length;
        this.nums = nums;
        tree = new int[len + 1];
        for (int i = 0; i < len; i++) {
            add(i + 1, nums[i]);
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    /**
     * @param idx 更新树状数组，将 idx 这个位置 + 1
     */
    private void add(int idx, int val) {
        while (idx <= len) {
            tree[idx] += val;
            idx += lowbit(idx);
        }
    }

    /**
     * @param idx 数组索引idx
     * @return 索引 <= idx 的前缀和
     */
    private int query(int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= lowbit(idx);
        }
        return sum;
    }

    /**
     * 将nums[idx]的值更新为val
     *
     * @param idx
     * @param val
     */
    public void update(int idx, int val) {
        add(idx + 1, val - nums[idx]);
        nums[idx] = val;
    }

    /**
     * @param left
     * @param right
     * @return 返回数组nums中索引left和索引right之间（包含）的nums元素的和
     */
    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }
}
