package tree.SegmentTree;

/**
 * 307. 区域和检索 - 数组可修改
 * 给定一个数组 nums ，请完成两类查询。 其中一类查询要求 更新 数组nums下标对应的值。
 * 另一类查询要求返回数组nums中索引left和索引right之间（包含）的nums元素的和，其中left <= right。
 */
public class SegmentTree2N {
    int len;
    int[] tree;

    SegmentTree2N(int[] nums) {
        len = nums.length;
        tree = new int[len << 1];// 迭代建树，只需要2N的数组空间

        // buildTree:
        for (int i = 0; i < len; i++) {
            tree[i + len] = nums[i];
        }
        for (int i = len - 1; i > 0; i--) {
            tree[i] = tree[i << 1] + tree[(i << 1) | 1];
        }
    }

    /**
     * 更新线段树
     * @param idx
     * @param val
     */
    public void update(int idx, int val) {
        idx += len;
        int diff = val - tree[idx];
        while(idx > 0) {
            tree[idx] += diff;
            idx >>= 1;
        }
    }

    /**
     * 计算 nums[left, right]的区间和
     * @param left
     * @param right
     * @return
     */
    public int sumRange(int left, int right) {
        left += len;
        right += len;
        int sum = 0;
        while(left <= right) {
            if((left & 1) == 1) {
                sum += tree[left];
                left++;
            }
            if((right & 1) == 0) {
                sum += tree[right];
                right--;
            }
            left >>= 1;
            right >>= 1;
        }
        return sum;
    }
}
